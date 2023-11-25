package filters.hantoeng

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

class HanToEngFilter(input: TokenStream) : TokenFilter(input) {

    private val charTermAttr: CharTermAttribute = addAttribute(CharTermAttribute::class.java)

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parsedData: CharSequence = "테스트"
            charTermAttr.setEmpty()
            charTermAttr.append(parsedData)
            true
        } else {
            false
        }
    }
}