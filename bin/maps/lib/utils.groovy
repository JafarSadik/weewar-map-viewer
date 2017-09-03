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
    boolean classExists;
    try {
        Class.forName(args.name)
        classExists = true
    } catch (Throwable ignore) {
        classExists = false
    }

    if (classExists && args.loaded) {
        return args.loaded()
    } else if (!classExists && args.otherwise) {
        return args.otherwise()
    }
    return classExists
}

// Declares a default bindings if not defined
defaultBindings = { Map<String, Object> defaults ->
    defaults.each {
        if (!binding.hasVariable(it.key)) {
            binding.setVariable(it.key, it.value)
        }
    }
}