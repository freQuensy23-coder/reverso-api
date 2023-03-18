package models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("list")
data class Translation (
	val frequency : Int,
	val term : String,
	val isFromDict : Boolean,
	val isPrecomputed : Boolean,
	val stags : List<String>,
	val article : String,
	val pos : String?,
	val sourcepos : List<String>?,
	val variant : String?,
	val domain : String?,
	val definition : String?,
	val vowels1 : String?,
	val transliteration1 : String?,
	val vowels2 : String?,
	val transliteration2 : String?,
	val alignFreq : Int,
	val reverseValidated : Boolean,
	val pos_group : Int,
	val isTranslation : Boolean,
	val isFromLOCD : Boolean,
//	val inflectedForms : List<String>, TODO fix this thing. It can be List<String> or List<InflectedForms>, (for example look word Hello)
	val isHiddenInFirstView : Boolean
)