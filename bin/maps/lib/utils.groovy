// Extend parser to handle parse exceptions
XmlParser.metaClass.tryParse = { inputStream, errorHandler ->
    try {
        return Optional.of(delegate.parse(inputStream))
    } catch (Throwable e) {
        errorHandler(e)
    }
    Optional.empty()
}

// Check if a class is loaded
Class.metaClass.isLoaded = { args ->
    try {
        Class.forName(args.name)
        if (args.loaded) args.loaded()
    } catch (Throwable t) {
        if (args.otherwise) args.otherwise()
        return false
    }
    return true
}
