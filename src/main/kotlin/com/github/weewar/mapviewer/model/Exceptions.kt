package com.github.weewar.mapviewer.model

class ClassPathResourceException(message: String, cause: Throwable) : RuntimeException(message, cause)

class MapParseException(message: String, cause: Throwable) : RuntimeException(message, cause)

class ImageResizeException(cause: Throwable) : RuntimeException(cause)

class ImagePreloadException(cause: Throwable) : RuntimeException(cause)

class ImageNotFoundException(message: String) : RuntimeException(message)

class ImageIOException(cause: Throwable) : RuntimeException(cause)