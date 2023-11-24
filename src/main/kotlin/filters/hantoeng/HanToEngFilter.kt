package filters.hantoeng

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import java.io.IOException



class HanToEngFilter(input: TokenStream) : TokenFilter(input) {

    private val termAtt: CharTermAttribute = addAttribute(CharTermAttribute::class.java)

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parserdData: CharSequence = "테스트"
            termAtt.setEmpty()
            termAtt.append(parserdData)
            true
        } else {
            false
        }
    }
}