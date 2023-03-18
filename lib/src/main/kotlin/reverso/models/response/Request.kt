package models.response

import kotlinx.serialization.Serializable

@Serializable
data class Request (

	val source_text : String,
	val target_text : String,
	val source_lang : String,
	val target_lang : String,
	val npage : Int,
	val corpus : String?,
	val nrows : Int,
	val adapted : Boolean,
	val nonadapted_text : String,
	val rude_words : Boolean,
	val colloquialisms : Boolean,
	val risky_words : Boolean,
	val mode : Int,
	val expr_sug : Int,
	val dym_apply : Boolean,
	val pos_reorder : Int,
	val device : Int,
	val split_long : Boolean,
	val has_locd : Boolean,
	val avoidLOCD : Boolean,
	val source_pos : String?,
	val toolwordRequest : Boolean
)