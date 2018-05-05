package cn.mcg.datastruct;

/**
 * @author maocg
 * 创建时间：2018/3/13
 * 描述：TODO
 */
public class ArrayTest {

    public static void main(String[] args) {
        String test = "4 5 7 -1";
        HighAndLow(test);

    }

    public static String HighAndLow(String numbers) {
        // Code here or
        if (numbers.length() == 1) {
            return numbers + " " + numbers;
        }
        String[] arr = numbers.split(" ");
        int min = Integer.parseInt(arr[0]);
        int max = Integer.parseInt(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int value = Integer.parseInt(arr[i]);
            if (min > value) {
                min = value;
            }
            if (max < value) {
                max = value;
            }
        }
        return max + " " + min;
    }
}
