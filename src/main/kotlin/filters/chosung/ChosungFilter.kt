package filters.chosung

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import util.ChosungUtil

class ChosungFilter(input: TokenStream) : TokenFilter(input) {

    private val charTermAttr: CharTermAttribute = addAttribute(CharTermAttribute::class.java)
    private val chosungUtil: ChosungUtil = ChosungUtil()

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parsedData: CharSequence = chosungUtil.convert(charTermAttr.toString())
            charTermAttr.setEmpty()
            charTermAttr.append(parsedData)
            true
        } else {
            false
        }
    }
}