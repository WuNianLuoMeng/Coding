public class Main43 {
    public String LeftRotateString(String str,int n) {
        if (str.length() == 0) {
            return "";
        }
        StringBuilder flipStr = new StringBuilder(str).reverse(); // 第一次翻转
        String str1 = flipStr.substring(0, flipStr.length() - n); // 取出第一个子序列
        String str2 = flipStr.substring(flipStr.length() - n); // 取出第二个子序列
        return new StringBuilder(str1).reverse().append(new StringBuilder(str2).reverse()).toString(); // 第二次翻转
    }
}
