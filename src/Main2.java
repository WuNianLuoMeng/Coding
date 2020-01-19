import java.util.Scanner;

public class Main2 {
//    private static String replaceSpace(StringBuffer str) {
//        int len = str.length();
//        String res = "%20";
//        StringBuffer ans = new StringBuffer();
//        for (int i = 0; i < len; i++) {
//            if (str.charAt(i) == ' ') {
//                ans.append(res);
//            } else {
//                ans.append(str.charAt(i));
//            }
//        }
//        return ans.toString();
//    }

    private static String replaceSpace(StringBuffer str) {
        int len = str.length();
        String res = "%20";
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < len; i++) {
            ans.append(str.charAt(i) == ' ' ? res : str.charAt(i));   /// 判断当前字符是否为空格
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        StringBuffer str = new StringBuffer();
        str.append(cin.nextLine());
        System.out.println(replaceSpace(str));
    }
}
