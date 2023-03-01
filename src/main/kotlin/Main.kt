import okhttp3.OkHttpClient

val client = OkHttpClient()



fun main() {
    println("Hello World!")
    val translator = ReversoTranslatorAPI()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    val text = "Square root"
    val translation = translator.translate(text, "en", "ru")
    println("Translation of $text is: $translation")
}