package constants

object LangMap {

    fun isCompleteHangul(c: Char): Boolean {
        // 가-힣에 속하는 완성형 한글
        return c.code in 0xAC00..0xD7A3
    }

    fun isJamo(c: Char): Boolean {
        // 홑 자모
        return c.code in 0x3131..0x3163
    }

    fun isEnglish(c: Char): Boolean {
        // A-Z, a-z 에 포함되는 영어 알파벳
        return c.code in 0x0041..0x005A || c.code in 0x0061..0x007A
    }

    val arrAlphabet: MutableMap<Char, Char> = mutableMapOf(
        0x0061.toChar() to 0x3141.toChar(), // 'a' = 'ㅁ'
        0x0041.toChar() to 0x3141.toChar(), // 'A' = 'ㅁ'
        0x0062.toChar() to 0x3160.toChar(), // 'b' = 'ㅠ'
        0x0042.toChar() to 0x3160.toChar(), // 'B' = 'ㅠ'
        0x0063.toChar() to 0x314a.toChar(), // 'c' = 'ㅊ'
        0x0043.toChar() to 0x314a.toChar(), // 'C' = 'ㅊ'
        0x0064.toChar() to 0x3147.toChar(), // 'd' = 'ㅇ'
        0x0044.toChar() to 0x3147.toChar(), // 'D' = 'ㅇ'
        0x0065.toChar() to 0x3137.toChar(), // 'e' = 'ㄷ'
        0x0045.toChar() to 0x3138.toChar(), // 'E' = 'ㄸ'
        0x0066.toChar() to 0x3139.toChar(), // 'f' = 'ㄹ'
        0x0046.toChar() to 0x3139.toChar(), // 'F' = 'ㄹ'
        0x0067.toChar() to 0x314e.toChar(), // 'g' = 'ㅎ'
        0x0047.toChar() to 0x314e.toChar(), // 'G' = 'ㅎ'
        0x0068.toChar() to 0x3157.toChar(), // 'h' = 'ㅗ'
        0x0048.toChar() to 0x3157.toChar(), // 'H' = 'ㅗ'
        0x0069.toChar() to 0x3151.toChar(), // 'i' = 'ㅑ'
        0x0049.toChar() to 0x3151.toChar(), // 'I' = 'ㅑ'
        0x006A.toChar() to 0x3153.toChar(), // 'j' = 'ㅓ'
        0x004A.toChar() to 0x3153.toChar(), // 'J' = 'ㅓ'
        0x006B.toChar() to 0x314f.toChar(), // 'k' = 'ㅏ'
        0x004B.toChar() to 0x314f.toChar(), // 'K' = 'ㅏ'
        0x006C.toChar() to 0x3163.toChar(), // 'l' = 'ㅣ'
        0x004C.toChar() to 0x3163.toChar(), // 'L' = 'ㅣ'
        0x006D.toChar() to 0x3161.toChar(), // 'm' = 'ㅡ'
        0x004D.toChar() to 0x3161.toChar(), // 'M' = 'ㅡ'
        0x006E.toChar() to 0x315c.toChar(), // 'n' = 'ㅜ'
        0x004E.toChar() to 0x315c.toChar(), // 'N' = 'ㅜ'
        0x006F.toChar() to 0x3150.toChar(), // 'o' = 'ㅐ'
        0x004F.toChar() to 0x3152.toChar(), // 'O' = 'ㅒ'
        0x0070.toChar() to 0x3154.toChar(), // 'p' = 'ㅔ'
        0x0050.toChar() to 0x3156.toChar(), // 'P' = 'ㅖ'
        0x0071.toChar() to 0x3142.toChar(), // 'q' = 'ㅂ'
        0x0051.toChar() to 0x3143.toChar(), // 'Q' = 'ㅃ'
        0x0072.toChar() to 0x3131.toChar(), // 'r' = 'ㄱ'
        0x0052.toChar() to 0x3132.toChar(), // 'R' = 'ㄲ'
        0x0073.toChar() to 0x3134.toChar(), // 's' = 'ㄴ'
        0x0053.toChar() to 0x3134.toChar(), // 'S' = 'ㄴ'
        0x0074.toChar() to 0x3145.toChar(), // 't' = 'ㅅ'
        0x0054.toChar() to 0x3146.toChar(), // 'T' = 'ㅆ'
        0x0075.toChar() to 0x3155.toChar(), // 'u' = 'ㅕ'
        0x0055.toChar() to 0x3155.toChar(), // 'U' = 'ㅕ'
        0x0076.toChar() to 0x314d.toChar(), // 'v' = 'ㅍ'
        0x0056.toChar() to 0x314d.toChar(), // 'V' = 'ㅍ'
        0x0077.toChar() to 0x3148.toChar(), // 'w' = 'ㅈ'
        0x0057.toChar() to 0x3149.toChar(), // 'W' = 'ㅉ'
        0x0078.toChar() to 0x314c.toChar(), // 'x' = 'ㅌ'
        0x0058.toChar() to 0x314c.toChar(), // 'X' = 'ㅌ'
        0x0079.toChar() to 0x315b.toChar(), // 'y' = 'ㅛ'
        0x0059.toChar() to 0x315b.toChar(), // 'Y' = 'ㅛ'
        0x007A.toChar() to 0x314b.toChar(), // 'z' = 'ㅋ'
        0x005A.toChar() to 0x314b.toChar(), // 'Z' = 'ㅋ'
    )

    val arrDoubleJongSung: MutableMap<Pair<Char, Char>, Char> = mutableMapOf(
        Pair(0x3131.toChar(), 0x3145.toChar()) to 0x3133.toChar(), // "ㄱㅅ" to "ㄳ",
        Pair(0x3134.toChar(), 0x3148.toChar()) to 0x3135.toChar(), // "ㄴㅈ" to "ㄵ",
        Pair(0x3134.toChar(), 0x314e.toChar()) to 0x3136.toChar(), // "ㄴㅎ" to "ㄶ",
        Pair(0x3139.toChar(), 0x3131.toChar()) to 0x313A.toChar(), // "ㄹㄱ" to "ㄺ",
        Pair(0x3139.toChar(), 0x3141.toChar()) to 0x313B.toChar(), // "ㄹㅁ" to "ㄻ",
        Pair(0x3139.toChar(), 0x314e.toChar()) to 0x3140.toChar(), // "ㄹㅎ" to "ㅀ",
        Pair(0x3139.toChar(), 0x3142.toChar()) to 0x313C.toChar(), // "ㄹㅂ" to "ㄼ",
        Pair(0x3139.toChar(), 0x314c.toChar()) to 0x313E.toChar(), // "ㄹㅌ" to "ㄾ",
        Pair(0x3142.toChar(), 0x3145.toChar()) to 0x3144.toChar(), // "ㅂㅅ" to "ㅄ",
    )

    val arrDoubleJungSung: MutableMap<Pair<Char, Char>, Char> = mutableMapOf(
        Pair(0x315c.toChar(), 0x3163.toChar()) to 0x315F.toChar(), // "ㅜㅣ" to "ㅟ",
        Pair(0x3161.toChar(), 0x3163.toChar()) to 0x3162.toChar(), // "ㅡㅣ" to "ㅢ",
        Pair(0x3157.toChar(), 0x314f.toChar()) to 0x3158.toChar(), // "ㅗㅏ" to "ㅘ",
        Pair(0x315c.toChar(), 0x3153.toChar()) to 0x315D.toChar(), // "ㅜㅓ" to "ㅝ",
        Pair(0x315c.toChar(), 0x3154.toChar()) to 0x315E.toChar(), // "ㅜㅔ" to "ㅞ",
        Pair(0x3157.toChar(), 0x3163.toChar()) to 0x315A.toChar(), // "ㅗㅣ" to "ㅚ",
        Pair(0x3157.toChar(), 0x3150.toChar()) to 0x3159.toChar(), // "ㅗㅐ" to "ㅙ",
    )

    val arrDoubleJongSungReverse: MutableMap<Char, Pair<Char, Char>> = mutableMapOf(
        0x3133.toChar() to Pair(0x3131.toChar(), 0x3145.toChar()), // "ㄳ" to "ㄱㅅ"
        0x3135.toChar() to Pair(0x3134.toChar(), 0x3148.toChar()), // "ㄵ" to "ㄴㅈ"
        0x3136.toChar() to Pair(0x3134.toChar(), 0x314e.toChar()), // "ㄶ" to "ㄴㅎ"
        0x313A.toChar() to Pair(0x3139.toChar(), 0x3131.toChar()), // "ㄺ" to "ㄹㄱ"
        0x313B.toChar() to Pair(0x3139.toChar(), 0x3141.toChar()), // "ㄻ" to "ㄹㅁ"
        0x3140.toChar() to Pair(0x3139.toChar(), 0x314e.toChar()), // "ㅀ" to "ㄹㅎ"
        0x313C.toChar() to Pair(0x3139.toChar(), 0x3142.toChar()), // "ㄼ" to "ㄹㅂ"
        0x313E.toChar() to Pair(0x3139.toChar(), 0x314c.toChar()), // "ㄾ" to "ㄹㅌ"
        0x3144.toChar() to Pair(0x3142.toChar(), 0x3145.toChar()), // "ㅄ" to "ㅂㅅ"
    )

    val arrDoubleJungSungReverse: MutableMap<Char, Pair<Char, Char>> = mutableMapOf(
        0x315F.toChar() to Pair(0x315c.toChar(), 0x3163.toChar()), // "ㅟ" to "ㅜㅣ"
        0x3162.toChar() to Pair(0x3161.toChar(), 0x3163.toChar()), // "ㅢ" to "ㅡㅣ"
        0x3158.toChar() to Pair(0x3157.toChar(), 0x314f.toChar()), // "ㅘ" to "ㅗㅏ"
        0x315D.toChar() to Pair(0x315c.toChar(), 0x3153.toChar()), // "ㅝ" to "ㅜㅓ"
        0x315E.toChar() to Pair(0x315c.toChar(), 0x3154.toChar()), // "ㅞ" to "ㅜㅔ"
        0x315A.toChar() to Pair(0x3157.toChar(), 0x3163.toChar()), // "ㅚ" to "ㅗㅣ"
        0x3159.toChar() to Pair(0x3157.toChar(), 0x3150.toChar()), // "ㅙ" to "ㅗㅐ"
    )

    val arrChoSung: CharArray = charArrayOf(
        0x3131.toChar(), // ㄱ
        0x3132.toChar(), // ㄲ
        0x3134.toChar(), // ㄴ
        0x3137.toChar(), // ㄷ
        0x3138.toChar(), // ㄸ
        0x3139.toChar(), // ㄹ
        0x3141.toChar(), // ㅁ
        0x3142.toChar(), // ㅂ
        0x3143.toChar(), // ㅃ
        0x3145.toChar(), // ㅅ
        0x3146.toChar(), // ㅆ
        0x3147.toChar(), // ㅇ
        0x3148.toChar(), // ㅈ
        0x3149.toChar(), // ㅉ
        0x314a.toChar(), // ㅊ
        0x314b.toChar(), // ㅋ
        0x314c.toChar(), // ㅌ
        0x314d.toChar(), // ㅍ
        0x314e.toChar(), // ㅎ
    )

    val arrChoSungEng: Array<String> = arrayOf(
        "r", "R", "s", "e", "E", "f",
        "a", "q", "Q", "t", "T", "d",
        "w", "W", "c", "z", "x", "v", "g"
    )

    val arrJungSung: CharArray = charArrayOf(
        0x314f.toChar(), // ㅏ
        0x3150.toChar(), // ㅐ
        0x3151.toChar(), // ㅑ
        0x3152.toChar(), // ㅒ
        0x3153.toChar(), // ㅓ
        0x3154.toChar(), // ㅔ
        0x3155.toChar(), // ㅕ
        0x3156.toChar(), // ㅖ
        0x3157.toChar(), // ㅗ
        0x3158.toChar(), // ㅘ
        0x3159.toChar(), // ㅙ
        0x315a.toChar(), // ㅚ
        0x315b.toChar(), // ㅛ
        0x315c.toChar(), // ㅜ
        0x315d.toChar(), // ㅝ
        0x315e.toChar(), // ㅞ
        0x315f.toChar(), // ㅟ
        0x3160.toChar(), // ㅠ
        0x3161.toChar(), // ㅡ
        0x3162.toChar(), // ㅢ
        0x3163.toChar(), // ㅣ
    )

    val arrJungSungEng: Array<String> = arrayOf(
        "k", "o", "i", "O", "j", "p",
        "u", "P", "h", "hk", "ho", "hl",
        "y", "n", "nj", "np", "nl", "b",
        "m", "ml", "l",
    )

    val arrJongSung: CharArray = charArrayOf(
        0.toChar(), // ""
        0x3131.toChar(), // ㄱ
        0x3132.toChar(), // ㄲ
        0x3133.toChar(), // ㄳ
        0x3134.toChar(), // ㄴ
        0x3135.toChar(), // ㄵ
        0x3136.toChar(), // ㄶ
        0x3137.toChar(), // ㄷ
        0x3139.toChar(), // ㄹ
        0x313a.toChar(), // ㄺ
        0x313b.toChar(), // ㄻ
        0x313c.toChar(), // ㄼ
        0x313d.toChar(), // ㄽ
        0x313e.toChar(), // ㄾ
        0x313f.toChar(), // ㄿ
        0x3140.toChar(), // ㅀ
        0x3141.toChar(), // ㅁ
        0x3142.toChar(), // ㅂ
        0x3144.toChar(), // ㅄ
        0x3145.toChar(), // ㅅ
        0x3146.toChar(), // ㅆ
        0x3147.toChar(), // ㅇ
        0x3148.toChar(), // ㅈ
        0x314a.toChar(), // ㅊ
        0x314b.toChar(), // ㅋ
        0x314c.toChar(), // ㅌ
        0x314d.toChar(), // ㅍ
        0x314e.toChar(), // ㅎ
    )

    val arrJongSungEng: Array<String> = arrayOf(
        "", "r", "R", "rt", "s", "sw",
        "sg", "e", "f", "fr", "fa", "fq",
        "ft", "fx", "fv", "fg", "a", "q",
        "qt", "t", "T", "d", "w", "c",
        "z", "x", "v", "g",
    )
}