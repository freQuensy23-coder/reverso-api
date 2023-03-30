package reverso.translators

import kotlinx.serialization.decodeFromString
import models.response.TranslationResponse
import reverso.LanguageCode

class ReversoTranslator : AbstractTranslator() {
    fun translate(text: String, fromLang: LanguageCode, toLang: LanguageCode): TranslationResponse {
        logger.debug { "Translating text $text from $fromLang to $toLang" }
        val request = requester.buildRequest(requester.createRequestBody(text, fromLang.code, toLang.code))
        val response = httpClient.newCall(request).execute()
        if (!response.isSuccessful) {
            logger.error { "Request failed with code ${response.code}" }
            throw Exception("Request failed with code ${response.code}")
        }
        val responseBody = response.body!!.string()
        logger.debug { "Request successful with code ${response.code}.Response body  $responseBody" }
        return jsonProcessor.decodeFromString(responseBody)
    }
}
