package filters.hantoeng

import org.apache.lucene.analysis.TokenStream
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.env.Environment
import org.elasticsearch.index.IndexSettings
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory

class HanToEngFilterFactory(
    indexSettings: IndexSettings,
    environment: Environment,
    name: String,
    settings: Settings
) : AbstractTokenFilterFactory(name, settings) {

    override fun create(tokenStream: TokenStream): TokenStream {
        return HanToEngFilter(tokenStream)
    }

}
