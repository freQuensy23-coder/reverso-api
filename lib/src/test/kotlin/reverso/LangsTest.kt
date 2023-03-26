package reverso

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class LanguageCodeTest {

    @Test
    fun `valid language code should not throw exception`() {
        assertDoesNotThrow {
            LanguageCode("en")
        }
    }

    @Test
    fun `invalid language code should throw exception`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            LanguageCode("xyz")
        }

        assertEquals("Invalid language code: xyz", exception.message)
    }

    @Test
    fun `get available languages codes`(){
        assertInstanceOf(List::class.java, LanguageCode.AVAILABLE_CODES)
        assertContains(LanguageCode.AVAILABLE_CODES, "en")
        assertContains(LanguageCode.AVAILABLE_CODES, "ru")
    }

    @Test
    fun getLanguageCode() {
        val languageCode = LanguageCode("en")
        assertEquals("en", languageCode.code)
    }

    @Test
    fun testGetLangName() {
        val languageCodeEn = LanguageCode("en")
        assertEquals("English", languageCodeEn.languageName)
        val languageCodeRu = LanguageCode("ru")
        assertEquals("Russian", languageCodeRu.languageName)
    }

    @Test
    fun testLangEmoji() {
        val languageCodeEn = LanguageCode("en")
        assertEquals("🇬🇧", languageCodeEn.languageEmoji)
        val languageCodeRu = LanguageCode("ru")
        assertEquals("🇷🇺", languageCodeRu.languageEmoji)
        LanguageCode.AVAILABLE_CODES.forEach{
            val languageCode = LanguageCode(it)
            val emoji = languageCode.languageEmoji
            assertInstanceOf(String::class.java, emoji)
        }
    }
}