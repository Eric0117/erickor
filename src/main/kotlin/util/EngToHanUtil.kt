package util

import constants.LangMap.arrAlphabet
import constants.LangMap.arrChoSung
import constants.LangMap.arrDoubleJongSung
import constants.LangMap.arrDoubleJungSung
import constants.LangMap.arrJongSung
import constants.LangMap.arrJungSung

/**
 * @author eric
 */
class EngToHanUtil {

    fun convertToKorean(s: String): String {
        val sb = StringBuilder()

        for (element in s) {
            when {
                isEnglish(element) -> {
                    sb.append(arrAlphabet[element])
                }

                else -> sb.append(element)
            }
        }
        return assemble(sb.toString())
    }

    private fun isEnglish(c: Char): Boolean {
        // A-Z, a-z 에 포함되는 영어 알파벳
        return c.code in 0x0041..0x005A || c.code in 0x0061..0x007A
    }

    private fun assemble(str: String): String {
        val sb = StringBuilder()
        var curIdx = 0

        while (curIdx < str.length) {
            var charBuilder = 0xAC00

            val currentChar = str[curIdx]

            // 초성 체크
            if (arrChoSung.contains(currentChar)) {
                val idxChosung = arrChoSung.indexOf(currentChar)
                charBuilder += idxChosung * arrJungSung.size * arrJongSung.size
                curIdx++

                // 중성 체크
                if (curIdx < str.length && arrJungSung.contains(str[curIdx])) {
                    val idxJungSung = arrJungSung.indexOf(str[curIdx])
                    charBuilder += arrJongSung.size * idxJungSung
                    curIdx++

                    // 종성 체크
                    if (curIdx < str.length && arrJongSung.contains(str[curIdx]) &&
                        !(curIdx + 1 < str.length && arrJungSung.contains(str[curIdx + 1]))
                    ) {
                        val idxJongSung = arrJongSung.indexOf(str[curIdx])
                        charBuilder += idxJongSung

                        if (str[curIdx] != ' ') {
                            curIdx++
                            // 종성 겹받침 체크
                            if (curIdx < str.length && arrJongSung.contains(str[curIdx]) &&
                                !(curIdx + 1 < str.length && arrJungSung.contains(str[curIdx + 1]))
                            ) {
                                val doubleJongSung = Pair(str[curIdx - 1], str[curIdx])
                                if (arrDoubleJongSung.contains(doubleJongSung)) {
                                    charBuilder -= idxJongSung
                                    val newJongsung = arrDoubleJongSung[doubleJongSung]
                                    val idxNewJongsung = arrJongSung.indexOf(newJongsung ?: ' ')
                                    charBuilder += idxNewJongsung
                                    curIdx++
                                }
                            }
                        }
                    }
                    // 중성 이중모음 체크
                    else if (curIdx < str.length && arrJungSung.contains(str[curIdx])) {
                        val doubleJungSung = Pair(str[curIdx - 1], str[curIdx])
                        if (arrDoubleJungSung.contains(doubleJungSung)) {
                            val newJungsung = arrDoubleJungSung[doubleJungSung]
                            val idxNewJungsung = arrJungSung.indexOf(newJungsung ?: ' ')
                            charBuilder -= arrJongSung.size * idxJungSung
                            charBuilder += arrJongSung.size * idxNewJungsung
                            curIdx++

                            // 이중모음 이후 종성이 있는지 체크
                            if (curIdx < str.length && arrJongSung.contains(str[curIdx]) &&
                                !(curIdx + 1 < str.length && arrJungSung.contains(str[curIdx + 1]))
                            ) {
                                val idxJongSung = arrJongSung.indexOf(str[curIdx])
                                charBuilder += idxJongSung
                                curIdx++

                                if (curIdx < str.length
                                    && arrJongSung.contains(str[curIdx])
                                    && !(curIdx + 1 < str.length && arrJungSung.contains(str[curIdx + 1]))
                                ) {
                                    val doubleJongSung = Pair(str[curIdx - 1], str[curIdx])
                                    if (arrDoubleJongSung.contains(doubleJongSung)) {
                                        charBuilder -= idxJongSung
                                        val newJongsung = arrDoubleJongSung[doubleJongSung]
                                        val idxNewJongsung = arrJongSung.indexOf(newJongsung ?: ' ')
                                        charBuilder += idxNewJongsung
                                        curIdx++
                                    }
                                }
                            }
                        }
                    }

                    sb.append(charBuilder.toChar())
                } else {
                    sb.append(currentChar)
                }
            } else if (
                arrJungSung.contains(str[curIdx])
                && (curIdx + 1 < str.length && arrJungSung.contains(str[curIdx + 1]))
            ) {
                val doubleJungSung = Pair(currentChar, str[curIdx + 1])
                if (arrDoubleJungSung.contains(doubleJungSung)) {
                    sb.append(arrDoubleJungSung[doubleJungSung])
                    curIdx += 2
                } else {
                    sb.append(currentChar)
                    curIdx++
                }
            } else {
                sb.append(currentChar)
                curIdx++
            }
        }

        return sb.toString()
    }
}