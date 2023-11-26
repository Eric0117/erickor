package plugin

import filters.chosung.ChosungFilterFactory
import filters.engtohan.EngToHanFilterFactory
import filters.hantoeng.HanToEngFilterFactory
import filters.jamo.JamoFilterFactory
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.env.Environment
import org.elasticsearch.index.IndexSettings
import org.elasticsearch.index.analysis.TokenFilterFactory
import org.elasticsearch.plugins.AnalysisPlugin
import org.elasticsearch.plugins.Plugin
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider
import java.util.Collections.singletonMap


class EricKorPlugin: Plugin(), AnalysisPlugin {

    @Override
    override fun getTokenFilters(): MutableMap<String, AnalysisProvider<TokenFilterFactory>> {
        val extra = mutableMapOf<String, AnalysisProvider<TokenFilterFactory>>()

        // 자모 분석
        extra["erickor_jamo"] = AnalysisProvider {
                indexSettings: IndexSettings,
                environment: Environment,
                s: String,
                settings: Settings ->
            JamoFilterFactory(
                indexSettings = indexSettings,
                environment = environment,
                name = s,
                settings = settings
            )
        }

        // 초성 분석
        extra["erickor_chosung"] = AnalysisProvider {
                indexSettings: IndexSettings,
                environment: Environment,
                s: String,
                settings: Settings ->
            ChosungFilterFactory(
                indexSettings = indexSettings,
                environment = environment,
                name = s,
                settings = settings
            )
        }

        // 한->영
        extra["erickor_hantoeng"] = AnalysisProvider {
            indexSettings: IndexSettings,
            environment: Environment,
            s: String,
            settings: Settings ->
                HanToEngFilterFactory(
                    indexSettings = indexSettings,
                    environment = environment,
                    name = s,
                    settings = settings
                )
        }

        // 영->한
        extra["erickor_engtohan"] = AnalysisProvider {
                indexSettings: IndexSettings,
                environment: Environment,
                s: String,
                settings: Settings ->
            EngToHanFilterFactory(
                indexSettings = indexSettings,
                environment = environment,
                name = s,
                settings = settings
            )
        }

        return extra
    }
}