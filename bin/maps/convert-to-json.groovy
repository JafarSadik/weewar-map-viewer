import groovy.json.JsonBuilder

// -------------------------------------------------------------
// Script converts original weewar maps from XML to JSON format
// -------------------------------------------------------------

// Locations of source and target map directories.
String srcMapsDirPath = "weewar-maps"
String dstMapsDirPath = "weewar-maps-json"

// Make sure that source directory exists
def srcMapsDir = new File(srcMapsDirPath)
if (!srcMapsDir.exists()) {
    println "Source maps directory not found: ${srcMapsDir.getAbsolutePath()}"
    System.exit(-1)
}

// Create target directory or ask to override
def dstMapsDir = new File(dstMapsDirPath)
if (dstMapsDir.exists()) {
    println "Target maps directory exists: ${dstMapsDir.getAbsolutePath()}"
    println "Do you wish to continue? Y / N"
    def choice = (char) System.in.read()
    if (choice != 'y' && choice != 'Y') System.exit(0)
} else {
    dstMapsDir.mkdir()
    println "Created directory: ${dstMapsDir.getAbsolutePath()}"
}

// Extend parser to handle parse exceptions
XmlParser.metaClass.parse = { path, errorHandler ->
    try {
        return Optional.of(delegate.parse(path))
    } catch (Throwable e) {
        errorHandler(path, e)
    }
    Optional.empty()
}

// Converting all maps to JSON format
def xmlParser = new XmlParser()
srcMapsDir.eachFile { srcMapPath ->
    // Parse XML map
    def errorHandler = { path, e -> println "Failed to parse map file: ${path}" }
    xmlParser.parse(srcMapPath, errorHandler).ifPresent(
            { srcMap ->
                def dstMapPath = dstMapsDir.getPath() + "/" + srcMap.@id

                println "Converting map file ${srcMapPath} -> ${dstMapPath}"

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

                /**
                 Convert terrain to a list of map tiles:
                 x, y, type, [startFaction], [direction], [startUnit, startUnitOwner]

                 x, y            coordinates
                 type            terrain or building (Water, Plains, Swamp,  Mountains, Woods, Desert, Bridge, Base, Harbor, ...)
                 startFaction    when set specifies building owner 0..5, otherwise no owner; applicable only to buildings (Base, Harbor, Airfield)
                 direction       direction of Bridge (nesw, nwse, nwse, ew, ..)
                 startUnit       unit type (Trooper, Jet, Tank, Heavy Tank, Destroyer, Battleship, ...)
                 startUnitOwner  unit owner 0..5

                 Example:
                 terrain: [{x:1,y:0,type:'Desert'},{x:1,y:1,type:'Swamp'},{x:1,y:2,type:'Water'},{x:1,y:3,type:'Base'},{x:1,y:4,type:'Base', startFaction: 0, startUnit: 'Trooper', startUnitOwner: '0'},{x:1,y:5,type:'Base', startFaction: 0, startUnit: 'Trooper', startUnitOwner: '0'},{x:1,y:6,type:'Bridge', startUnit: 'Trooper', startUnitOwner: '0', direction: 'we'},{x:1,y:7,type:'Woods'},
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
                def json = new JsonBuilder(dstMap).toString()//toPrettyString();
                new File(dstMapPath).text = json
            }
    )
}

