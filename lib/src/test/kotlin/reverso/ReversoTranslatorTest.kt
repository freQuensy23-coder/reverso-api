package reverso

import okhttp3.RequestBody
import okio.Buffer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.Request
import org.junit.jupiter.api.BeforeEach
import reverso.translators.ReversoTranslator


fun RequestBody.toMap(): Map<String, String> {
    fun jsonToMap(jsonString: String): Map<String, String> {
        val mapper = jacksonObjectMapper()
        val jsonMap = mapper.readValue(jsonString, Map::class.java)
        val stringMap = mutableMapOf<String, String>()

        jsonMap.forEach { (key, value) ->
            if (value is Int) {
                stringMap[key.toString()] = value.toString()
            } else {
                stringMap[key.toString()] = value.toString()
            }
        }

        return stringMap
    }
    val buffer = Buffer()
    writeTo(buffer)
    return jsonToMap(buffer.readUtf8())
}



internal class ReversoTranslatorTest{
    private val reversoTranslatorAPI = ReversoTranslator()
    private val requester = Requester()
    private val translatingText = "Square root"
    private val expectedTranslation = "квадратный корень"
    private val expectedRequestBodyMap = mapOf(
        "source_lang" to "en",
        "target_lang" to "ru",
        "source_text" to "Square root",
        "target_text" to "",
        "mode" to "0",
        "npage" to "1"
    )
    private val requestBodyCreator = requester.javaClass.getDeclaredMethod("createRequestBody", String::class.java, String::class.java, String::class.java)
    private val requestCreator = requester.javaClass.getDeclaredMethod("buildRequest", RequestBody::class.java)
    @BeforeEach
    fun setup(){
        // TODO make it works with beforeAll prefix
        requestBodyCreator.isAccessible = true
        requestCreator.isAccessible = true
    }

    @Test
    fun testRequestBodyGenerator(){
        val requestBody = requestBodyCreator.invoke(requester, translatingText, "en", "ru") // TODO Something wrong with invoke method here
        assert(requestBody is RequestBody)
        if (requestBody !is RequestBody){
            return
        }
        val requestBodyMap = requestBody.toMap()
        assertEquals(expectedRequestBodyMap, requestBodyMap)
    }

    @Test
    fun testRequestCreator(){
        val requestBody = requestBodyCreator.invoke(requester, translatingText, "en", "ru")
        val request = requestCreator.invoke(reversoTranslatorAPI, requestBody)
        assert(request is Request)
        if (request !is Request){
            return
        }
        assertEquals("https://context.reverso.net/bst-query-service", request.url.toString())
        assertEquals("POST", request.method)
        assertEquals("application/json; charset=utf-8", request.body!!.contentType().toString())
        val requestBodyMap = request.body!!.toMap()
        assertEquals(expectedRequestBodyMap, requestBodyMap)
    }

    @Test
    fun testTranslation(){
        val translationResponse = reversoTranslatorAPI.translate(translatingText, LanguageCode("en"), LanguageCode("ru"))
        assertEquals(expectedTranslation, translationResponse.dictionary_entry_list[0].term)
    }

    @Test
    fun testHardTranslation(){
        println("Start hard translation test")
        val textsToTranslate = listOf("hello", "be", "government", "goverment", "go")
        val rightTranslation = listOf("привет", "быть", "правительство", "правительство", "пойти")
        textsToTranslate.forEachIndexed { index, text ->
            val translationResponse = reversoTranslatorAPI.translate(text, LanguageCode("en"), LanguageCode("ru"))
            assertEquals(rightTranslation[index], translationResponse.dictionary_entry_list[0].term)
            println("$text -> ${translationResponse.dictionary_entry_list[0].term} ok")
        }
    }

    @Test
    fun testSentenceTranslation(){
        val translationResponse = reversoTranslatorAPI.translate("I am a student", LanguageCode("en"), LanguageCode("ru"))
        assertEquals("я студент", translationResponse.dictionary_entry_list[0].term)
    }

}
