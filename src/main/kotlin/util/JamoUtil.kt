package util

import constants.LangMap.arrChoSung
import constants.LangMap.arrJongSung
import constants.LangMap.arrJungSung
import constants.LangMap.isCompleteHangul

class JamoUtil {
    fun convert(s: String): String {
        val sb = StringBuilder()

        for (element in s) {
            if (isCompleteHangul(element)) {
                val idxBasic = element.code - 0xAC00
                val idxChosung = idxBasic / (21 * 28)
                val idxJungSung = (idxBasic % (21 * 28)) / 28
                val idxJongSung = idxBasic % 28

                sb.append(arrChoSung[idxChosung]).append(arrJungSung[idxJungSung])
                if (idxJongSung != 0) {
                    sb.append(arrJongSung[idxJongSung])
                }
            } else {
                sb.append(element)
            }
        }

        return sb.toString()
    }


}