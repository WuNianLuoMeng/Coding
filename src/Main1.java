import java.util.Scanner;

public class Main1 {
//    public boolean Find(int target, int [][] array) {
//        for (int i = 0; i < array.length; i++)
//        {
//            for (int j = 0; j < array[0].length; j++)
//            {
//                if (array[i][j] == target)
//                    return true;
//            }
//        }
//        return false;
//    }

//    private static boolean Find(int target, int[][] array) {
//        int i = array.length - 1;
//        int j = 0;
//        while(i >= 0 && i < array.length && j >= 0 && j < array[0].length)
//        {
//            // array[i][j]
//            if (array[i][j] == target)
//                return true;
//            else if (array[i][j] > target)
//                i--;
//            else
//                j++;
//        }
//        return false;
//    }

    private static boolean Find(int target, int[][] array) {
        int i = 0;
        int j = array[0].length - 1;
        while(i >= 0 && i < array.length && j >= 0 && j < array[0].length)
        {
            // array[i][j]
            if (array[i][j] == target)
                return true;
            else if (array[i][j] > target)
                j--;
            else
                i++;
        }
        return false;
    }

    public static void main(String[] args) {
        int n, m;
        Scanner cin = new Scanner(System.in);
        n = cin.nextInt();
        m = cin.nextInt();
        int[][] array = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                array[i][j] = cin.nextInt();
        for (int i = 1; i <= n*m+1; i++)
        {
            int target = cin.nextInt();
            System.out.println(Find(target, array));
        }
    }
}

//i, j, target = 2
//3 3
//1 2 3
//4 5 6
//7 8 9
