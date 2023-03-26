package models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("list")
data class Usage (
	val s_text : String,
	val t_text : String,
	val ref : String,
	val cname : String,
	val url : String,
	val ctags : String,
	val pba : Boolean
)

fun Usage.prettifyUsage(prefix: String="<", postfix: String=">"): Usage{
	// Replace <em> tags to prefix and </em> for postfix. For example:
	// Take <em>square</em> root -> Take <square> root
	return Usage(s_text = this.s_text.replace("<em>", prefix).replace("</em>", postfix),
		t_text = this.t_text.replace("<em>", prefix).replace("</em>", postfix),
		ref = this.ref,
		cname = this.cname,
		url = this.url,
		ctags = this.ctags,
		pba = this.pba)
}
fun Usage.prettifyUsage(symbol: String="*"): Usage{
	// Replace <em> tags near source world to symbol. For example:
	// Take <em>square</em> root -> Take *square* root
	return this.prettifyUsage(symbol, symbol)
}

