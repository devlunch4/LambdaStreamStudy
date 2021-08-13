public class Lambda {
    //expression original
    public void exp_original() {
        int[] arr = new int[5];
        Arrays.setAll(arr, (i) -> (int) Math.randon() * 5) + 1;
    }

    //expresssion lambda method
    public int method() {
        return (int) (Math.random() * 5) + 1;
    }
}
