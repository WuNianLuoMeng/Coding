class Main44 {
    /*public String ReverseSentence(String str) {
        String flipStr = new StringBuilder(str).reverse().toString(); // 第一次翻转
        StringBuilder res = new StringBuilder(); // 用来遍历每一个单词
        StringBuilder ans = new StringBuilder(); // 用来保存结果
        for (int i = 0; i < flipStr.length(); i++) {
            if (flipStr.charAt(i) == ' ') {
                ans.append(res.reverse().toString()).append(" "); // 第二次翻转
                res = new StringBuilder();
            } else {
                res.append(flipStr.charAt(i));
            }
        }
        ans.append(res.reverse().toString()); // 最后那个单词的翻转结果保存到ans当中
        return ans.toString();
    }*/

    public String ReverseSentence(String str) {
        String flipStr = new StringBuilder(str.trim()).reverse().toString(); // 第一次翻转
        StringBuilder res = new StringBuilder(); // 用来遍历每一个单词
        StringBuilder ans = new StringBuilder(); // 用来保存结果
        for (int i = 0; i < flipStr.length(); i++) {
            if (flipStr.charAt(i) == ' ') {
                if (res.length() > 0) {
                    ans.append(ans.length() == 0 ? res.reverse() : " " + res.reverse());
                    res = new StringBuilder();
                }
            } else {
                res.append(flipStr.charAt(i));
            }
        }
        if (res.length() > 0) {
            ans.append(ans.length() == 0 ? res.reverse() : " " + res.reverse());
        }
        return ans.toString();
    }
}