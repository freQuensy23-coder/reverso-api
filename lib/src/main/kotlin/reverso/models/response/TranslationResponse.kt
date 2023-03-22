package models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse (
	@SerialName("list")
	val usages : List<Usage>,
	val nrows : Int,
	val nrows_exact : Int,
	val pagesize : Int,
	val npages : Int,
	val page : Int,
	val removed_superstrings : List<String>,
	val dictionary_entry_list : List<Translation>,
	val dictionary_other_frequency : Int,
	val dictionary_nrows : Int,
	val time_ms : Int,
	val request : Request,
	val suggestions : List<Suggestions>?,
	val dym_case : Int,
	val dym_list : List<String>,
	val dym_applied : String?,
	val dym_nonadapted_search : String?,
	val dym_pair_applied : String?,
	val dym_nonadapted_search_pair : String?,
	val dym_pair : String?,
	val extracted_phrases : List<String?>,
	val sourceTransliterations : List<String>
)