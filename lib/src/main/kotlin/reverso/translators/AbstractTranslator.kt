package reverso.translators

import kotlinx.serialization.json.Json
import mu.KotlinLogging

abstract class AbstractTranslator(
    protected val jsonProcessor : Json = Json { ignoreUnknownKeys = true },
    protected val httpClient : okhttp3.OkHttpClient = okhttp3.OkHttpClient(),
    protected val logger : mu.KLogger = KotlinLogging.logger {}
)