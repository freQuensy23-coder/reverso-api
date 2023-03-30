package reverso

import kotlinx.serialization.json.Json
import mu.KotlinLogging

abstract class AbstractTranslator(protected val jsonUnsafe : Json = Json { ignoreUnknownKeys = true },
                                  protected val client : okhttp3.OkHttpClient = okhttp3.OkHttpClient(),
                                  protected val logger : mu.KLogger = KotlinLogging.logger {}) {
}