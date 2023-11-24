package filters.hantoeng

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream

class HanToEngFilter(input: TokenStream) : TokenFilter(input) {

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parserdData: CharSequence = "테스트"
            true
        } else {
            false
        }
    }
}