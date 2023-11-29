package util

import constants.LangMap.arrChoSung
import constants.LangMap.arrDoubleJongSungReverse
import constants.LangMap.arrDoubleJungSungReverse
import constants.LangMap.arrJongSung
import constants.LangMap.arrJungSung
import constants.LangMap.isCompleteHangul
import constants.LangMap.isJamo

class JamoUtil {
    fun convert(s: String): String {
        val sb = StringBuilder()

        for (element in s) {
            when {
                isCompleteHangul(element) -> {
                    val idxBasic = element.code - 0xAC00
                    val idxChosung = idxBasic / (21 * 28)
                    val idxJungSung = (idxBasic % (21 * 28)) / 28
                    val idxJongSung = idxBasic % 28

                    val choSungValue = arrChoSung[idxChosung]
                    sb.append(choSungValue)

                    val jungSungValue = arrJungSung[idxJungSung]
                    val jungSung = arrDoubleJungSungReverse[jungSungValue]?.let {
                        it.first.toString() + it.second.toString()
                    } ?: jungSungValue.toString()

                    sb.append(jungSung)

                    if (idxJongSung != 0) {
                        val jongSungValue = arrJongSung[idxJongSung]
                        val jongSung = arrDoubleJongSungReverse[jongSungValue]?.let {
                            it.first.toString() + it.second.toString()
                        } ?: jongSungValue.toString()
                        sb.append(jongSung)
                    }
                }
                else -> {
                    sb.append(element)
                }
            }
        }
        return sb.toString()
    }
}