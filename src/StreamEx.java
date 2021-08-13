import java.util.stream.Stream;

public class StreamEx {
    public void stream_test() {
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        System.out.println(evenStream);
    }

    public static void main(String[] args) {
        // Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        // evenStream.forEach(n -> System.out.println(n));

        // Stream<Double> randomStream = Stream.generate(Math::random);
        // System.out.println(randomStream);
        // randomStream.forEach(n -> System.out.println(n));
    }
}
