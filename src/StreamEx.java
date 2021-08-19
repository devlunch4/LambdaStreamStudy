import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;


public class StreamEx {

    public static void main(String[] args) {
        //allMatch_anyMatch_noneMatch_test();
        // builder_test();
        collect_test();
        concat_test();
        count_test();
        distinct_test();
        empty_test();

        //>>>>>>
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

        //filter
        //IntStream intStream2 = IntStream.rangeClosed(1, 10);
        //intStream2.filter(i -> i % 2 == 0).forEach(System.out::println);
    }

    public void stream_test() {
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        System.out.println(evenStream);


    }

    private static void allMatch_anyMatch_noneMatch_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        int[] intArr = {2, 4, 6};
        System.out.println("intArr: " + Arrays.toString(intArr));
        // 이 스트림의 모든 요소가 제공된 술어와 일치하는지 여부를 리턴합니다.
        boolean result = Arrays.stream(intArr).allMatch(a -> a % 2 == 0);
        System.out.println("allMatch>>>2의 배수? " + result);
        //이 스트림의 요소가 제공된 술어와 일치하는지 여부를 리턴합니다.
        result = Arrays.stream(intArr).anyMatch(a -> a % 3 == 0);
        System.out.println("anyMatch>>>3의 배수가 하나라도 있나? " + result);
        //이 스트림의 요소가 제공된 술어와 일치하지 않는지 여부를 반환합니다.
        result = Arrays.stream(intArr).noneMatch(a -> a % 3 == 0);
        System.out.println("noneMatch>>>3의 배수가 없나? " + result);
        System.out.println();
    }


    private static void builder_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        // 스트림에 대한 빌더를 반환합니다.
        Stream<String> builderStream =
                Stream.<String>builder()
                        .add("A")
                        .add("B")
                        .add("C")
                        .build(); // [Eric, Elena, Java]
        builderStream.forEach(System.out::println);
        System.out.println();
    }

    private static void collect_test() { //https://www.baeldung.com/java-8-collectors
        //Collector를 사용하여 이 스트림의 요소에 대해 변경 가능한 축소 작업을 수행합니다.


        //Collect는 다음과 같은 기능들을 제공합니다.

        //-스트림의 아이템들을 List 또는 Set 자료형으로 변환
        //-스트림의 아이템들을 joining
        //-스트림의 아이템들을 Sorting하여 가장 큰 객체 리턴
        //-스트림의 아이템들의 평균 값을 리턴

        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //Stream의 아이템들을 HashSet으로 리턴
        System.out.println("//Stream의 아이템들을 HashSet으로 리턴");
        Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        HashSet<String> fruitHashSet = fruits.collect(HashSet::new, HashSet::add, HashSet::addAll);
        for (String s : fruitHashSet) {
            System.out.println(s);
        }
        //위보다 더 단순하게  Collectors와 함께 collect로 HashSet 객체를 만드는 예제입니다. 인자에 toSet()
        System.out.println("//Stream의 아이템들을 Collectors와 함께 collect로 HashSet 리턴");
        Stream<String> fruits2 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        Set<String> fruitSet = fruits2.collect(Collectors.toSet());
        for (String s : fruitSet) {
            System.out.println(s);
        }
        //Stream의 아이템들을 List 객체로 리턴
        System.out.println("//Stream의 아이템들을 List 객체로 리턴");
        Stream<String> fruits3 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        List<String> fruitList = fruits3.collect(Collectors.toList());
        for (String s : fruitList) {
            System.out.println(s);
        }
        //스트림 아이템을 1개의 String 객체로 만들기 >> joining() 사용
        System.out.println("//스트림 아이템을 1개의 String 객체로 만들기 >> joining() 사용");
        Stream<String> fruits4 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        String result4 = fruits4.collect(Collectors.joining());
        System.out.println(result4);
        //  string 객체 간에 구분자를 넣어주고 싶다면 Collectors.joining(", ")처럼 param으로 구분자를 전달하면 됩니다.
        System.out.println("//스트림 아이템을 1개의 String 객체로 만들기 >> joining() 사용 >> string 객체 간에 구분자");
        Stream<String> fruits5 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        String result5 = fruits5.map(Object::toString).collect(Collectors.joining(", "));
        System.out.println(result5);

        // 가장 큰 객체 1개만 리턴하기(길이)
        System.out.println("// 가장 큰 객체 1개만 리턴하기(길이)");
        Stream<String> fruits6 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        Function<String, Integer> getCount = fruit -> fruit.length();
        Optional<String> result6 = fruits6.map(Object::toString).collect(maxBy(comparing(getCount)));
        System.out.println("result: " + result6.orElse("no item"));

        //Collectors로 평균 값 구하기
        System.out.println("//Collectors로 평균 값 구하기");
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Double avg_result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
        System.out.println("Average: " + avg_result);

        //Custom 객체에 Collect 적용하기
        System.out.println("//Custom 객체에 Collect 적용하기");
        Stream<Fruit> fruits7 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
                new Fruit("3", "mango"), new Fruit("4", "kiwi"),
                new Fruit("5", "peach"), new Fruit("6", "cherry"),
                new Fruit("7", "lemon"));
        Map<String, String> map = fruits7.collect(Collectors.toMap(Fruit::getId, Fruit::getName));
        for (String key : map.keySet()) {
            System.out.println("key: " + key + ", value: " + map.get(key));
        }

        //toMap 수행시, 동일한 Key에 대한 예외처리
        //아래 코드는 기존에 등록된 값을 사용하도록 (existingValue, newValue) -> existingValue)로 정의 했습니다.
        //(코드를 보면 key 5를 갖고 있는 데이터가 두개 있습니다.)
        System.out.println("//toMap 수행시, 동일한 Key에 대한 예외처리 1. 기존값 사용");
        Stream<Fruit> fruits8 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
                new Fruit("3", "mango"), new Fruit("4", "kiwi"),
                new Fruit("5", "peach"), new Fruit("6", "cherry"),
                new Fruit("5", "lemon"));
        Map<String, String> map2 = fruits8.collect(
                Collectors.toMap(item -> item.getId(), item -> item.getName(),
                        (existingValue, newValue) -> existingValue));
        for (String key : map2.keySet()) {
            System.out.println("key: " + key + ", value: " + map2.get(key));
        }

        //이번에는 동일 key를 갖고 있는 데이터가 있을 때, 두개의 값을 합하여 저장하도록 정의할 수 있습니다.
        // 3번째 param으로, 두개의 값을 더한 값을 리턴하는 함수를 정의하면 됩니다.
        System.out.println("//toMap 수행시, 동일한 Key에 대한 예외처리 2. 두개 더한값 사용");
        Stream<Fruit> fruits9 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
                new Fruit("3", "mango"), new Fruit("4", "kiwi"),
                new Fruit("5", "peach"), new Fruit("6", "cherry"),
                new Fruit("5", "lemon"));
        Map<String, String> map3 = fruits9.collect(
                Collectors.toMap(item -> item.getId(), item -> item.getName(),
                        (existingValue, newValue) -> {
                            String concat = existingValue + ", " + newValue;
                            return concat;
                        }));
        for (String key : map3.keySet()) {
            System.out.println("key: " + key + ", value: " + map3.get(key));
        }
        System.out.println();
    }

    private static void concat_test() {
        //첫 번째 스트림의 모든 요소와 두 번째 스트림의 모든 요소가 뒤따르는 지연 연결된 스트림을 만듭니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> numbers =
                Arrays.asList("1", "2", "3", "4", "5");
        List<String> chars =
                Arrays.asList("a", "b", "c", "d", "e");
        Stream<String> stream1 = numbers.stream();
        Stream<String> stream2 = chars.stream();
        Stream<String> stream3 = Stream.concat(stream1, stream2);
        stream3.forEach(System.out::println);
        System.out.println();
    }

    private static void count_test() {
        //이 스트림의 요소 수를 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> words = Arrays.asList("book", "desk", "keyboard", "mouse", "cup");
        int count = (int) words.stream().filter(w -> w.contains("o")).count();
        System.out.println("count >" + count);
        System.out.println();
    }

    private static void distinct_test() {
        //이 스트림의 고유한 요소(Object.equals(Object)에 따라)로 구성된 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> strings =
                Arrays.asList("google", "apple", "google", "apple", "samsung");
        Stream<String> stream1 = strings.stream();
        Stream<String> stream2 = stream1.distinct();
        stream2.forEach(System.out::println);
        System.out.println();
    }

    private static void empty_test() {
        //Stream.empty()는 비어있는 스트림을 생성합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        Stream<String> stream = Stream.empty();
        System.out.println("stream");
        stream.forEach(s -> System.out.println(s));
    }


    static class Fruit {
        public String id;
        public String name;

        Fruit(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


}
