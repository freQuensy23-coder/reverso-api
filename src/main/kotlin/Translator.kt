import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.request.TranslationRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

val JSON = "application/json; charset=utf-8".toMediaType()
class ReversoTranslatorAPI {
    fun translate(text: String, fromLang: String, toLang: String):TranslationResponse{
        val serializeFormat = Json { encodeDefaults = true }
        val requestBodyJson = serializeFormat
            .encodeToString<TranslationRequest>(TranslationRequest(fromLang, toLang, sourceText=text))
        val requestBody = requestBodyJson.toRequestBody(JSON)
        val request = Request.Builder()
            .url("https://context.reverso.net/bst-query-service")
            .addHeader("Origin", "https://context.reverso.net")
            .addHeader("Accept-Language", "en-US,en;q=0.5")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:77.0) Gecko/20100101 Firefox/77.0")
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        return Json.decodeFromString<TranslationResponse>(response.body!!.string())
    }
}
