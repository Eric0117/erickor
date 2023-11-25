package util

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

    companion object {
        @JvmStatic val arrChoSung: CharArray = charArrayOf(
            0x3131.toChar(),
            0x3132.toChar(),
            0x3134.toChar(),
            0x3137.toChar(),
            0x3138.toChar(),
            0x3139.toChar(),
            0x3141.toChar(),
            0x3142.toChar(),
            0x3143.toChar(),
            0x3145.toChar(),
            0x3146.toChar(),
            0x3147.toChar(),
            0x3148.toChar(),
            0x3149.toChar(),
            0x314a.toChar(),
            0x314b.toChar(),
            0x314c.toChar(),
            0x314d.toChar(),
            0x314e.toChar(),
        )

        @JvmStatic val arrChoSungEng: Array<String> = arrayOf(
            "r", "R", "s", "e", "E", "f",
            "a", "q", "Q", "t", "T", "d",
            "w", "W", "c", "z", "x", "v", "g"
        )

        @JvmStatic val arrJungSung: CharArray = charArrayOf(
            0x314f.toChar(), 0x3150.toChar(), 0x3151.toChar(), 0x3152.toChar(), 0x3153.toChar(), 0x3154.toChar(),
            0x3155.toChar(), 0x3156.toChar(), 0x3157.toChar(), 0x3158.toChar(), 0x3159.toChar(), 0x315a.toChar(),
            0x315b.toChar(), 0x315c.toChar(), 0x315d.toChar(), 0x315e.toChar(), 0x315f.toChar(), 0x3160.toChar(),
            0x3161.toChar(), 0x3162.toChar(), 0x3163.toChar(),
        )

        @JvmStatic val arrJungSungEng: Array<String> = arrayOf(
            "k", "o", "i", "O", "j", "p",
            "u", "P", "h", "hk", "ho", "hl",
            "y", "n", "nj", "np", "nl", "b",
            "m", "ml", "l",
        )

        @JvmStatic val arrJongSung: CharArray = charArrayOf(
            0.toChar(), 0x3131.toChar(), 0x3132.toChar(), 0x3133.toChar(), 0x3134.toChar(), 0x3135.toChar(),
            0x3136.toChar(), 0x3137.toChar(), 0x3139.toChar(), 0x313a.toChar(), 0x313b.toChar(), 0x313c.toChar(),
            0x313d.toChar(), 0x313e.toChar(), 0x313f.toChar(), 0x3140.toChar(), 0x3141.toChar(), 0x3142.toChar(),
            0x3144.toChar(), 0x3145.toChar(), 0x3146.toChar(), 0x3147.toChar(), 0x3148.toChar(), 0x314a.toChar(),
            0x314b.toChar(), 0x314c.toChar(), 0x314d.toChar(), 0x314e.toChar(),
        )

        @JvmStatic val arrJongSungEng: Array<String> = arrayOf(
            "", "r", "R", "rt", "s", "sw",
            "sg", "e", "f", "fr", "fa", "fq",
            "ft", "fx", "fv", "fg", "a", "q",
            "qt", "t", "T", "d", "w", "c",
            "z", "x", "v", "g",
        )
    }

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
