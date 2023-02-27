import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import okhttp3.RequestBody.Companion.toRequestBody

val client = OkHttpClient()
val JSON = "application/json; charset=utf-8".toMediaType()


fun translate(fromLang: String, toLang: String, text: String): String {
    val requestBody = """{
"source_lang": "$fromLang",
"target_lang": "$toLang",
"mode": 0,
"npage": 1,
"source_text": "$text",
"target_text": ""
}""".trimIndent().toRequestBody(JSON)
    val request = Request.Builder()
        .url("https://context.reverso.net/bst-query-service")
        .addHeader("Origin", "https://context.reverso.net")
        .addHeader("Accept-Language", "en-US,en;q=0.5")
        .addHeader("X-Requested-With", "XMLHttpRequest")
        .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:77.0) Gecko/20100101 Firefox/77.0")
        .post(requestBody)
        .build()
    val response = client.newCall(request).execute()
    return response.body!!.string()
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}. Trs: ${Json.decodeFromString<TranslationResponse>(translate("en", "ru", "square root"))}")
}