import groovy.json.JsonBuilder

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

// -------------------------------------------------------------
// Script converts original weewar maps from XML to JSON format
// -------------------------------------------------------------

// Parse command line arguments and options
def cli = new CliBuilder(usage: 'convert-maps.groovy [options] [output_dir]', header: 'Options:')
cli.s(longOpt: 'silent', 'silent mode, no output to console', required: false)
cli.h(longOpt: 'help', 'shows the help', required: false)
cli.p(longOpt: 'pretty', 'convert maps to formatted json', required: false)
cli.f(longOpt: 'force', 'force override target directory', required: false)
def options = cli.parse(this.args), args = options.arguments()
if (options.help || args.size() > 1) {
    cli.usage()
    System.exit(-1)
}

// Source map archive and target directory
def mapsArchive = new ZipFile("weewar-maps.zip")
def targetDir = new File(args ? args[0] : "weewar-maps-json")

// Modify 'println' to support silent mode
def println = { text -> if (!options.silent) println text }

// Create target directory or ask to override
if (!options.force && targetDir.exists()) {
    println "Target maps directory exists: ${targetDir.getAbsolutePath()}"
    println "Do you wish to continue? Y / N"
    def choice = (char) System.in.read()
    if (choice != 'y' && choice != 'Y') System.exit(1)
}
targetDir.mkdir()

// Extend parser to handle parse exceptions
XmlParser.metaClass.tryParse = { inputStream, errorHandler ->
    try {
        return Optional.of(delegate.parse(inputStream))
    } catch (Throwable e) {
        errorHandler(e)
    }
    Optional.empty()
}

// Converting all maps to JSON format
def xmlParser = new XmlParser()
println "Converting map files..."
def startTime = System.currentTimeMillis()
mapsArchive.entries().findAll { !it.isDirectory() }.each { ZipEntry zipEntry ->
    mapsArchive.getInputStream(zipEntry).withCloseable { inputStream ->
        // Parse XML map
        def errorHandler = { e -> println "Failed to parse map file: ${zipEntry.name}" }
        Optional<Node> xml = xmlParser.tryParse(inputStream, errorHandler)
        xml.ifPresent { Node srcMap ->
            def mapFile = new File(targetDir.getPath() + "/" + srcMap.@id)
            println "${zipEntry.name} -> ${mapFile.absolutePath}"

            // Copy map details: id, name, creator, size, ...
            def dstMap = [
                    id            : srcMap.@id,
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

            srcMap.terrains.terrain.each {
                //add tile, omit null values
                dstMap.terrain << [
                        x           : it.@x,
                        y           : it.@y,
                        type        : it.@type,
                        startFaction: it.@startFaction,
                        direction   : it.@direction,
                        unit        : it.@startUnit,
                        unitOwner   : it.@startUnitOwner
                ].findAll { it.value != null }
            }

            //Convert groovy object to json and write it to file
            def json = new JsonBuilder(dstMap)
            mapFile.text = options.pretty ? json.toPrettyString() : json.toString()
        }
    }
}
mapsArchive.close()
println "Map conversion completed in ${(System.currentTimeMillis() - startTime) / 1000} seconds"