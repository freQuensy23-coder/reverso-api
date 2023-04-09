package reverso

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.request.TranslationRequest
import mu.KotlinLogging
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


object Requester {
    private val logger: mu.KLogger = KotlinLogging.logger {}
    internal fun createRequestBody(text: String, fromLang: String, toLang: String): RequestBody {
        val serializeFormat = Json { encodeDefaults = true }
        val JSON = "application/json; charset=utf-8".toMediaType()
        val translationRequest = TranslationRequest(fromLang, toLang, sourceText=text)
        logger.debug { "Translation request: $translationRequest" }
        return serializeFormat
            .encodeToString<TranslationRequest>(translationRequest).toRequestBody(JSON)
    }

    internal fun buildRequest(requestBody: RequestBody): Request {
        val request =  Request.Builder()
            .url("https://context.reverso.net/bst-query-service") // TODO DO not hardcode this values
            .addHeader("Origin", "https://context.reverso.net")
            .addHeader("Accept-Language", "en-US,en;q=0.5")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:77.0) Gecko/20100101 Firefox/77.0")
            .post(requestBody)
        logger.debug { "Created request: $request" }
        return request.build()
    }
}