import java.util.Arrays;

public class LambdaEx {
    //expression original
    public void exp_original() {
        int[] arr = new int[5];
        Arrays.setAll(arr, (i) -> (int) (Math.random() * 5) + 1);
    }

    //expresssion lambda method
    public int method() {
        return (int) (Math.random() * 5) + 1;
    }
}
