package com.example.doei.domain.utils

import java.text.Normalizer
import java.util.regex.Pattern

object StringUtils {
    private fun removeCaps(str: String?): String? {
        return str?.lowercase()
    }

    private fun removeAccents(str: String?): String? {
        val nfdNormalizedString: String = Normalizer.normalize(str, Normalizer.Form.NFD)
        val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }
    fun removeAccentsAndCaps(str: String?): String? {
        removeAccents(str).also {
            return removeCaps(it)
        }
    }
}