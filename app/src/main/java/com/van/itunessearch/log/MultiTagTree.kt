package com.van.itunessearch.log

import timber.log.Timber

class MultiTagTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement) =
        "Vanson-(${element.fileName}:${element.lineNumber})#${element.methodName}"
}