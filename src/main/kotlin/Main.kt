import okhttp3.OkHttpClient

val client = OkHttpClient()



fun main() {
    println("Hello World!")
    val translator = ReversoTranslatorAPI()
    val text = "Square root"
    val translation = translator.translate(text, "en", "ru")
    println("Translation of $text is: $translation")
}