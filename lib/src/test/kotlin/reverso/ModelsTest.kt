import models.response.Usage
import models.response.prettifyUsage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class UsageTest {
    @Test
    fun testPrettifyUsageWithPrefixAndPostfix() {
        val usage = Usage(s_text = "Take <em>square</em> root",
            t_text = "Взять <em>квадратный</em> корень",
            ref = "math",
            cname = "math",
            url = "https://example.com/math",
            ctags = "math",
            pba = false)
        val result = usage.prettifyUsage("<", ">")
        assertEquals("Take <square> root", result.s_text)
        assertEquals("Взять <квадратный> корень", result.t_text)
        assertEquals("math", result.ref)
        assertEquals("math", result.cname)
        assertEquals("https://example.com/math", result.url)
        assertEquals("math", result.ctags)
        assertFalse(result.pba)
    }

    @Test
    fun testPrettifyUsageWithSymbol() {
        val usage = Usage(s_text = "Take <em>square</em> root",
            t_text = "Взять <em>квадратный</em> корень",
            ref = "math",
            cname = "math",
            url = "https://example.com/math",
            ctags = "math",
            pba = false)
        val result = usage.prettifyUsage("*")
        assertEquals("Take *square* root", result.s_text)
        assertEquals("Взять *квадратный* корень", result.t_text)
        assertEquals("math", result.ref)
        assertEquals("math", result.cname)
        assertEquals("https://example.com/math", result.url)
        assertEquals("math", result.ctags)
        assertFalse(result.pba)
    }
}
