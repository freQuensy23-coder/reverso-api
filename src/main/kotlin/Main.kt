import okhttp3.OkHttpClient

val client = OkHttpClient()



fun main() {
    val translator = ReversoTranslatorAPI()
    val text = "Hello"
    val translationResponse = translator.translate(text, "en", "ru")
    println("Translation of $text is: ${translationResponse.dictionary_entry_list[0].term}")
}