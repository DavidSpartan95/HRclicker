package com.example.hrclicker.functions

fun containsEmoticons(input: String): Boolean {
    val emoticonRegex = Regex("[\\uD800-\\uDBFF][\\uDC00-\\uDFFF]")
    return emoticonRegex.containsMatchIn(input)
}

fun hasMoreThan16Characters(input: String): Boolean {
    return input.length > 16
}