package filters.engtohan

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import util.EngToHanUtil

class EngToHanFilter(input: TokenStream) : TokenFilter(input) {

    private val charTermAttr: CharTermAttribute = addAttribute(CharTermAttribute::class.java)
    private val engToHanUtil: EngToHanUtil = EngToHanUtil()

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parsedData: CharSequence = engToHanUtil.convertToKorean(charTermAttr.toString())
            charTermAttr.setEmpty()
            charTermAttr.append(parsedData)
            true
        } else {
            false
        }
    }
}