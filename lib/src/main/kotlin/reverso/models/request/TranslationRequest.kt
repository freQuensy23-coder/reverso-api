package models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationRequest(@SerialName("source_lang")
                              val sourceLang: String,
                              @SerialName("target_lang")
                              val targetLang: String,
                              @SerialName("mode")
                              val mode: Int = 0,
                              @SerialName("npage")
                              val pageNumber: Int=1,
                              @SerialName("source_text")
                              val sourceText: String,
                              @SerialName("target_text")
                              val targetText: String ="")
