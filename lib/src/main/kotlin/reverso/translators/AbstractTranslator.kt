package reverso.translators

import kotlinx.serialization.json.Json
import mu.KotlinLogging
import reverso.Requester

abstract class AbstractTranslator(
    protected val jsonProcessor : Json = Json { ignoreUnknownKeys = true },
    protected val httpClient : okhttp3.OkHttpClient = okhttp3.OkHttpClient(),
    protected val logger : mu.KLogger = KotlinLogging.logger {},
    protected val requester: Requester = Requester(logger)
)