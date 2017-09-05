// Mappings between names used in the original weewar map files and this project
def mapItems = [
        'Anti Aircraft': 'aa',
        'Assault Artillery': 'assault-artillery',
        'Battleship': 'battleship',
        'Berserker': 'berseker',
        'Bomber': 'bomber',
        'Destroyer': 'destroyer',
        'DFA': 'dfa',
        'Heavy Artillery': 'heavy-artillery',
        'Heavy Tank': 'heavy-tank',
        'Heavy Trooper': 'heavy-trooper',
        'Helicopter': 'helicopter',
        'Hovercraft': 'hovercraft',
        'Jet': 'jet',
        'Light Artillery': 'light-artillery',
        'Raider': 'raider',
        'Speedboat': 'speedboat',
        'Submarine': 'sub',
        'Tank': 'tank',
        'Trooper': 'trooper',
        'Airfield': 'airfield',
        'Bridge': 'bridge',
        'Base': 'city',
        'Desert': 'desert',
        'Woods': 'foreset',
        'Harbor': 'harbor',
        'Mountains': 'mountain',
        'Plains': 'plain',
        'Repairshop': 'repairshop',
        'Swamp': 'swamp',
        'Water': 'water'
]

// Converts weewar-specific name of a single map item (terrain, building or unit) to name used in this project
convertMapItem = { String type ->
    String result = mapItems[type]
    assert !type || result //expecting non-null result for non-null type
    return result
}

// Mappings between numeric and string teams identifiers
def teams = [
        0 : 'blue',
        1 : 'red',
        2 : 'purple',
        3 : 'yellow',
        4 : 'green',
        5 : 'white'
]

// Converts team name from a number to string
convertTeam = { Integer team ->
    String result = teams[team]
    assert !team || result //expecting non-null result for non-null type
    return result
}
