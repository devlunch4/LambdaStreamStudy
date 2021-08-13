import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx {
    public void stream_test() {
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        System.out.println(evenStream);
    }

    public static void main(String[] args) {
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
        IntStream intStream = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
        intStream.distinct().forEach(System.out::println);

        System.out.println();
        //filter
        IntStream intStream2 = IntStream.rangeClosed(1, 10);
        intStream2.filter(i -> i % 2 == 0).forEach(System.out::println);


    }
}
