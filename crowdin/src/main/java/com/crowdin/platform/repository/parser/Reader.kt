package com.crowdin.platform.repository.parser

import com.crowdin.platform.repository.remote.api.LanguageData
import java.io.InputStream

internal interface Reader {

    /**
     * Converts input stream to language data object
     *
     * @param byteStream    stream to be parsed
     */
    fun parseInput(byteStream: InputStream): LanguageData
}