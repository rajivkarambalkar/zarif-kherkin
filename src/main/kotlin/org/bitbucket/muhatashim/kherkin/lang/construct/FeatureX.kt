package org.bitbucket.muhatashim.kherkin.lang.construct

import kotlinx.coroutines.CoroutineScope
import mu.KotlinLogging
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta


/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/27/2019
 * Time: 8:57 PM
 */
private val logger = KotlinLogging.logger {}

data class FeatureX(
    val name: String,
    val description: String?,
    val background: BackgroundX?,
    val scenarios: MutableList<ScenarioX>,
    var meta: FeatureMeta
) {

    suspend operator fun invoke(hooks: Hooks = Hooks(), coroutineScope: CoroutineScope? = null) {
        val allHooks = GlobalHooks + hooks
        logger.debug { "- Feature: $name: $description" }
        scenarios.forEach {
            background?.invoke()
            it(allHooks, this, coroutineScope)
        }
    }
}
