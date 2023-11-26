package util

import constants.LangMap
import constants.LangMap.arrChoSung

class ChosungUtil {
    fun convert(s: String): String {
        val sb = StringBuilder()

        for (element in s) {
            when {
                LangMap.isCompleteHangul(element) -> {
                    // 가~힣 에 속한 글자일 경우
                    val idxBasic = element.code - 0xAC00
                    val idxChosung = idxBasic / (21 * 28)

                    sb.append(arrChoSung[idxChosung])
                }
                LangMap.isJamo(element) -> {
                    // 홑 자모에 속한 글자일 경우
                    val idxChosung = arrChoSung.indexOfFirst { it == element }
                    sb.append(arrChoSung[idxChosung])
                }
                else -> sb.append(element)
            }
        }

        return sb.toString()
    }

}