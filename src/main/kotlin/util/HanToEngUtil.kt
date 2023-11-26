package util

import constants.LangMap.arrChoSung
import constants.LangMap.arrChoSungEng
import constants.LangMap.arrJongSung
import constants.LangMap.arrJongSungEng
import constants.LangMap.arrJungSung
import constants.LangMap.arrJungSungEng

/**
 * @author eric
 * 유니코드 관련 https://www.unicode.org/charts/PDF/U3130.pdf
 */
class HanToEngUtil {

    /**
     * 초성 "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
     * 중성  "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
     * 종성  "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
     * 초성 : ( ( ( 한글유니코드 – 0xAC00 – 종성 ) / 28 ) – 중성 ) ) / 21
     * 중성 : ( ( 한글유니코드 – 0xAC00 – 종성 ) / 28 ) % 21
     * 종성 : (한글유니코드 – 0xAC00 ) % 28 종성은 한글 유니코드 값에서 0xAC00을 뺀 값에서 28로 나눈 나머지 값이 종성 순서 값
     */


    fun convertToEnglish(s: String): String {
        val sb = StringBuilder()

        for (element in s) {
            when {
                isCompleteHangul(element) -> {
                    // 가~힣 에 속한 글자일 경우
                    val idxBasic = element.code - 0xAC00
                    val idxChosung = idxBasic / (21 * 28)
                    val idxJungSung = (idxBasic % (21 * 28)) / 28
                    val idxJongSung = idxBasic % 28

                    sb.append(arrChoSungEng[idxChosung])
                        .append(arrJungSungEng[idxJungSung])
                        .append(arrJongSungEng[idxJongSung])
                }
                isJamo(element) -> {
                    // 홑 자모에 속한 글자일 경우
                    val temp = findSyllable(element, arrChoSung, arrChoSungEng)
                        ?: findSyllable(element, arrJungSung, arrJungSungEng)
                        ?: findSyllable(element, arrJongSung, arrJongSungEng)
                        ?: element.toString()
                    sb.append(temp)
                }
                else -> sb.append(element)
            }
        }

        return sb.toString()
    }

    private fun isCompleteHangul(c: Char): Boolean {
        // 가-힣에 속하는 완성형 한글
        return c.code in 0xAC00..0xD7A3
    }

    private fun isJamo(c: Char): Boolean {
        // 홑 자모
        return c.code in 0x3131..0x3163
    }

    private fun findSyllable(c: Char, targetArr: CharArray, targetEngArr: Array<String>): String? {
        val index = targetArr.indexOfFirst { it == c }
        return index.takeIf { it != -1 }?.let { targetEngArr.getOrNull(it) }
    }
}
