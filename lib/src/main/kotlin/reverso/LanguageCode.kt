package reverso

class LanguageCode(languageCodeString: String) {
    companion object {
        val AVAILABLE_CODES: List<String> get() = listOf("ru", "en", "de", "ar", "es", "fr", "he", "it", "ja", "ko", "nl", "pl", "pt", "ro", "sv", "tr", "zh", "uk")
    }
    init {
        if (!AVAILABLE_CODES.contains(languageCodeString)) {
            throw IllegalArgumentException("Invalid language code: $languageCodeString")
        }
    }
    val code = languageCodeString

    val languageName: String
        get() = when (code) {
            "ru" -> "Russian"
            "en" -> "English"
            "de" -> "German"
            "ar" -> "Arabic"
            "es" -> "Spanish"
            "fr" -> "French"
            "he" -> "Hebrew"
            "it" -> "Italian"
            "ja" -> "Japanese"
            "ko" -> "Korean"
            "nl" -> "Dutch"
            "pl" -> "Polish"
            "pt" -> "Portuguese"
            "ro" -> "Romanian"
            "sv" -> "Swedish"
            "tr" -> "Turkish"
            "zh" -> "Chinese"
            "uk" -> "Ukrainian"
            else -> throw IllegalArgumentException("Invalid language code: $code")
        }
    override fun toString(): String = "LanguageCode(code='$code', languageName='$languageName')"
}

val LanguageCode.languageEmoji: String
    get() = when (code) {
        "ru" -> "🇷🇺"
        "en" -> "🇬🇧"
        "de" -> "🇩🇪"
        "ar" -> "🇸🇦"
        "es" -> "🇪🇸"
        "fr" -> "🇫🇷"
        "he" -> "🇮🇱"
        "it" -> "🇮🇹"
        "ja" -> "🇯🇵"
        "ko" -> "🇰🇷"
        "nl" -> "🇳🇱"
        "pl" -> "🇵🇱"
        "pt" -> "🇵🇹"
        "ro" -> "🇷🇴"
        "sv" -> "🇸🇪"
        "tr" -> "🇹🇷"
        "zh" -> "🇨🇳"
        "uk" -> "🇺🇦"
        else -> throw IllegalArgumentException("Invalid language code: $code")
    }
