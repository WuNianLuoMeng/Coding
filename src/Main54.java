import java.util.HashMap;
import java.util.Map;

public class Main54 {
    private Map<Character, Integer> map = new HashMap<>(); // 保存每个字符出现的次数
    private StringBuilder str = new StringBuilder(); // 保存字符流
    private int index = 0; // 用来保存字符只出现一次的第一个位置

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        str.append(ch);
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        while (index < str.length()) {
            if (map.get(str.charAt(index)) == 1) {
                return str.charAt(index);
            }
            index++;
        }
        return '#';
    }
}
