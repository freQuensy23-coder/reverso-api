package models.response

import kotlinx.serialization.Serializable

@Serializable
data class Suggestions (

	val lang : String,
	val suggestion : String,
	val weight : Int,
	val isFromDict : Boolean
)