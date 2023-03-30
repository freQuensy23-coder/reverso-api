package reverso

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.request.TranslationRequest
import models.response.TranslationResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class ReversoTranslatorAPI : AbstractTranslator() {
    fun translate(text: String, fromLang: LanguageCode, toLang: LanguageCode): TranslationResponse {
        logger.debug { "Translating text $text from $fromLang to $toLang" }
        val request = createRequest(createRequestBody(text, fromLang.code, toLang.code))
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            logger.error { "Request failed with code ${response.code}" }
            throw Exception("Request failed with code ${response.code}")
        }
        val responseBody = response.body!!.string()
        logger.debug { "Request successful with code ${response.code}.Response body  $responseBody" }
        println("Request successful with code ${response.code}.Response body  $responseBody")
        // Convert response.body to TranslationResponse using Json and skip any additional fields
        return jsonUnsafe.decodeFromString(responseBody)
    }

    private fun createRequestBody(text: String, fromLang: String, toLang: String): RequestBody{
        val serializeFormat = Json { encodeDefaults = true }
        val JSON = "application/json; charset=utf-8".toMediaType()
        val translationRequest = TranslationRequest(fromLang, toLang, sourceText=text)
        logger.debug { "Translation request: $translationRequest" }
        return serializeFormat
            .encodeToString<TranslationRequest>(translationRequest).toRequestBody(JSON)
    }

    private fun createRequest(requestBody: RequestBody): Request{
        val request =  Request.Builder()
            .url("https://context.reverso.net/bst-query-service")
            .addHeader("Origin", "https://context.reverso.net")
            .addHeader("Accept-Language", "en-US,en;q=0.5")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:77.0) Gecko/20100101 Firefox/77.0")
            .post(requestBody)
        logger.debug { "Created request: $request" }
        return request.build()
    }
}
