import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.request.TranslationRequest
import models.response.TranslationResponse
import models.response.Usage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class ReversoTranslatorAPI {
    private val jsonUnsafe = Json { ignoreUnknownKeys = true }
    private val client = okhttp3.OkHttpClient()

    fun translate(text: String, fromLang: String, toLang: String): TranslationResponse {
        val request = createRequest(createRequestBody(text, fromLang, toLang))
        val response = client.newCall(request).execute()
        // Convert response.body to TranslationResponse using Json and skip any additional fields
        return jsonUnsafe.decodeFromString(response.body!!.string())
    }

    private fun createRequestBody(text: String, fromLang: String, toLang: String): RequestBody{
        val serializeFormat = Json { encodeDefaults = true }
        val JSON = "application/json; charset=utf-8".toMediaType()
        return serializeFormat
            .encodeToString<TranslationRequest>(TranslationRequest(fromLang, toLang, sourceText=text)).toRequestBody(JSON)
    }

    private fun createRequest(requestBody: RequestBody): Request{
        return Request.Builder()
            .url("https://context.reverso.net/bst-query-service")
            .addHeader("Origin", "https://context.reverso.net")
            .addHeader("Accept-Language", "en-US,en;q=0.5")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:77.0) Gecko/20100101 Firefox/77.0")
            .post(requestBody)
            .build()
    }

    companion object DataPostProcessor{
        fun prettifyUsage(usage: Usage, symbol: String="*"): Usage{
            // Replace <em> tags near source world to symbol. For example:
            // Take <em>square</em> root -> Take *square* root
            return Usage(s_text = usage.s_text.replace("<em>", symbol).replace("</em>", symbol),
                t_text = usage.t_text.replace("<em>", symbol).replace("</em>", symbol),
                ref = usage.ref,
                cname = usage.cname,
                url = usage.url,
                ctags = usage.ctags,
                pba = usage.pba)
        }
    }

}
