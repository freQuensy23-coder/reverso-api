## Reverso context unofficial API wrapper
Official website - [reverso.net](https://www.reverso.net/text-translation)
### Usage example
```kotlin
fun main() {
    val translator = ReversoTranslatorAPI()
    val text = "Hello"
    val translationResponse = translator.translate(text, "en", "ru") 
    // Available languages are Arabic, Chinese, English, French, Hebrew, Spanish, Italian, Ukrainian and Russian.
    println("Translation of $text is: ${translationResponse.dictionary_entry_list[0].term}")
    println("This translation usage frequency is ${translationResponse.dictionary_entry_list[0].frequency}")
    println("Find ${translationResponse.usages.size} usages of this world")
    println("One of them is ${translationResponse.usages[0].s_text} -> ${translationResponse.usages[0].t_text}")
}
```


