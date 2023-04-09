## Reverso context API
Simple Kotlin API for Reverso Context. This project is used in the university telegram bot project of an English teacher. More information in the [wiki](https://landaulev.notion.site/langlearnbot-081dff1ddcb94a84b5f6d472e8cdca6c)

![ReversoAPIlogo](https://github.com/s0ftik3/reverso-api/raw/master/assets/reversoapi-logo.png)

### Installation
In `build.gradle.kts`:
* Add jitpack repo
```kotlin
repositories {
    maven("https://jitpack.io")
}
```
* then (replace tag with version)
```kotlin
implementation("com.github.freQuensy23-coder:reverso-api:Tag")
```
### Usage example

```kotlin
val text = "Hello"
val translator = reversoTranslatorAPI() // Init translator
val translationResponse: TranslationResponse = reversoTranslatorAPI.translate(text, "en", "fr")
val translations: List<Translation> = translationResponse.dictionary_entry_list
translations.forEach {
    println("$text -> ${it.term} (usage frequency = ${it.frequency}). - ${it.defenition}")
}
println("Usage examples")
translationResponse.usages.forEach {
    println("${it.s_text} -> ${it.t_text}")
}
```
