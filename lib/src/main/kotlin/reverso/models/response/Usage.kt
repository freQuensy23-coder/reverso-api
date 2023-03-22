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