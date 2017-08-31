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
        args.loaded()
    } catch (Throwable t) {
        args.otherwise()
        return false
    }
    return true
}
