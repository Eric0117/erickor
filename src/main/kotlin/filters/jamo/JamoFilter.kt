package filters.jamo

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import util.JamoUtil

class JamoFilter(input: TokenStream) : TokenFilter(input) {

    private val charTermAttr: CharTermAttribute = addAttribute(CharTermAttribute::class.java)
    private val jamoUtil: JamoUtil = JamoUtil()

    override fun incrementToken(): Boolean {

        return if (input.incrementToken()) {
            val parsedData: CharSequence = jamoUtil.convert(charTermAttr.toString())
            charTermAttr.setEmpty()
            charTermAttr.append(parsedData)
            true
        } else {
            false
        }
    }
}