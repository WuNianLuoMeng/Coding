public class Main53 {
    public boolean isNumber(String s) {
        if (s.endsWith("f") || s.endsWith("d") || s.endsWith("F") || s.endsWith("D")) {
            return false;
        }
        try {
            Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
