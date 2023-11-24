package plugin

import filters.hantoeng.HanToEngFilterFactory
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

        // 초성 분석

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

        return extra
    }
}