public class Main48 {
    public int Add(int num1,int num2) {
        int sum1, sum2;
        do{
            sum1 = num1 ^ num2;
            sum2 = (num1 & num2) << 1;
            num1 = sum1;
            num2 = sum2;
        }while (num2 != 0);
        return num1;
    }
}
