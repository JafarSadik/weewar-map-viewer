// -------------------------------------------------------------
// Script converts original weewar maps from XML to JSON format
// -------------------------------------------------------------

import groovy.json.JsonBuilder

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

// Evaluate base path in order to make script execution independent from working directory
def script = getClass().protectionDomain.codeSource.location.path
def baseDir = script.replaceAll("\\\\", "/").substring(0, script.lastIndexOf("/"))

//Import utilities
def importScript = { evaluate(new File(it)) }
importScript("$baseDir/lib/utils.groovy")

// Script arguments and options
options = Class.isLoaded name: 'groovy.util.CliBuilder',
        loaded: { /*obtain arguments & options from command line*/
            importScript("$baseDir/lib/cli.groovy")
            parseCli(this.args)
        },
        otherwise: { /*obtain arguments & options from groovy bindings*/
            defaultBindings([force: false, silent: false, help: false, pretty: false])
            [force: force, silent: silent, help: help, pretty: pretty, arguments: { [targetMapsDir] }]
        }

// Display help if required
arguments = options.arguments()
if (options.help || arguments.size() > 1) {
    cli.usage()
    System.exit(-1)
}

// Modify 'println' to support silent mode
def println = { text -> if (!options.silent) println text }

// Source map archive and target directory
def mapsArchive = new ZipFile("$baseDir/weewar-maps.zip")
def targetDir = new File(arguments ? arguments[0] : "$baseDir/weewar-maps-json")

// Create target directory or ask to override
if (!options.force && targetDir.exists()) {
    println "Target maps directory exists: ${targetDir.getAbsolutePath()}"
    println "Do you wish to continue? Y / N"
    def choice = (char) System.in.read()
    if (choice != 'y' && choice != 'Y') System.exit(1)
}
targetDir.mkdirs()

// Converting all maps to JSON format
def xmlParser = new XmlParser()
println "Converting map files..."
mapsArchive.entries().findAll { !it.isDirectory() }.each { ZipEntry zipEntry ->
    mapsArchive.getInputStream(zipEntry).withCloseable { inputStream ->
        // Parse XML map
        def errorHandler = { e -> println "Failed to parse map file: $zipEntry.name" }
        Optional<Node> xml = xmlParser.tryParse(inputStream, errorHandler)
        xml.ifPresent { Node srcMap ->
            def mapFile = new File(targetDir.getPath() + "/" + srcMap.@id)
            println "$mapsArchive.name/$zipEntry.name -> $mapFile.absolutePath"

            // Copy map details: id, name, creator, size, ...
            def dstMap = [
                    id            : srcMap.@id as Integer,
                    name          : srcMap.name.text(),
                    creator       : srcMap.creator.text(),
                    initialCredits: srcMap.initialCredits.text() as Integer,
                    perBaseCredits: srcMap.perBaseCredits.text() as Integer,
                    width         : srcMap.width.text() as Integer,
                    height        : srcMap.height.text() as Integer,
                    players       : srcMap.maxPlayers.text() as Integer,
                    revision      : srcMap.revision.text() as Integer,
                    terrain       : []
            ]

            /*
            Convert terrain to a list of map tiles:
            x, y, type, [startFaction], [direction], [startUnit, startUnitOwner]

            x, y            coordinates
            type            terrain or building (Water, Plains, Swamp,  Mountains, Woods, Desert, Bridge, Base, Harbor, ...)
            startFaction    when set specifies building owner 0..5, otherwise no owner; applicable only to buildings (Base, Harbor, Airfield)
            direction       direction of Bridge (nesw, nwse, nwse, ew, ..)
            startUnit       unit type (Trooper, Jet, Tank, Heavy Tank, Destroyer, Battleship, ...)
            startUnitOwner  unit owner 0..5

            Example:
            terrain: [
               {x:1,y:0,type:'Desert'},
               {x:1,y:1,type:'Swamp'},
               {x:1,y:2,type:'Water'},
               {x:1,y:3,type:'Base'},
               {x:1,y:4,type:'Base', startFaction: 0, startUnit: 'Trooper', startUnitOwner: '0'},
               {x:1,y:5,type:'Base', startFaction: 0, startUnit: 'Trooper', startUnitOwner: '0'},
               {x:1,y:6,type:'Bridge', startUnit: 'Trooper', startUnitOwner: '0', direction: 'we'},
               {x:1,y:7,type:'Woods'}
               ]
            */
            srcMap.terrains.terrain.each { tile ->
                //add tile, omit null values
                dstMap.terrain << [
                        x           : tile.@x as Integer,
                        y           : tile.@y as Integer,
                        type        : tile.@type,
                        startFaction: tile.@startFaction as Integer,
                        direction   : tile.@direction,
                        unit        : tile.@startUnit,
                        unitOwner   : tile.@startUnitOwner as Integer
                ].findAll { it.value != null }
            }

            //Convert groovy object to json and write it to file
            def json = new JsonBuilder(dstMap)
            mapFile.text = options.pretty ? json.toPrettyString() : json.toString()
        }
    }
}
mapsArchive.close()
