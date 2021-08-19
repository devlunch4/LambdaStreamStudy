import java.util.Arrays;
import java.util.stream.Stream;

public class StreamEx {
    public void stream_test() {
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        System.out.println(evenStream);


    }

    private static void allMatch_anyMatch_noneMatch_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        int[] intArr = {2, 4, 6};
        System.out.println("intArr: " + Arrays.toString(intArr));
        boolean result = Arrays.stream(intArr).allMatch(a -> a % 2 == 0);
        System.out.println("allMatch>>>2의 배수? " + result);
        result = Arrays.stream(intArr).anyMatch(a -> a % 3 == 0);
        System.out.println("anyMatch>>>3의 배수가 하나라도 있나? " + result);
        result = Arrays.stream(intArr).noneMatch(a -> a % 3 == 0);
        System.out.println("noneMatch>>>3의 배수가 없나? " + result);
        System.out.println();
    }


    private static void builder_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        Stream<String> builderStream =
                Stream.<String>builder()
                        .add("Eric")
                        .add("Elena")
                        .add("Java")
                        .build(); // [Eric, Elena, Java]
        builderStream.forEach(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {
        allMatch_anyMatch_noneMatch_test();
        builder_test();
        //iterate
        // Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        // evenStream.forEach(n -> System.out.println(n));

        //generate
        // Stream<Double> randomStream = Stream.generate(Math::random);
        // System.out.println(randomStream);
        // randomStream.forEach(n -> System.out.println(n));

        // skip
        // IntStream intStream = IntStream. rangeClosed(1, 10);
        // intStream.skip(3).limit(5).forEach(System.out::print);

        // distinct
        //  IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
        // intStream.distinct().forEach(System.out::println);

        System.out.println();
        //filter
        //IntStream intStream2 = IntStream.rangeClosed(1, 10);
        //intStream2.filter(i -> i % 2 == 0).forEach(System.out::println);


    }


}
