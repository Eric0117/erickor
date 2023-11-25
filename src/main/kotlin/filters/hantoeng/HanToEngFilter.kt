package filters.hantoeng

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import util.HanToEngUtil

class HanToEngFilter(input: TokenStream) : TokenFilter(input) {

    private val charTermAttr: CharTermAttribute = addAttribute(CharTermAttribute::class.java)
    private val hanToEngUtil: HanToEngUtil = HanToEngUtil()

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parsedData: CharSequence = hanToEngUtil.convertToEnglish(charTermAttr.toString())
            charTermAttr.setEmpty()
            charTermAttr.append(parsedData)
            true
        } else {
            false
        }
    }
}