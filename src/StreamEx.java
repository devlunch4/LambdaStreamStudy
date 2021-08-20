import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;


public class StreamEx {

    public static void main(String[] args) {
        stream_test();
        allMatch_anyMatch_noneMatch_test();
        builder_test();
        collect_test();
        concat_test();
        count_test();
        distinct_test();
        empty_test();
        filter_test();
        findAny_findFirst_test();
        flatMap_test();
        flatMapToDouble_test();
        flatMapToInt_test();
        flatMapToLong_test();
        forEach_test();
        forEachOrdered_test();
        generate_test();
        iterate_test();
        limit_test();
        map_test();
        mapToDouble_test();
        mapToInt_test();
        mapToLong_test();
        max_min_test();
        noneMatch_test();

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

        // filter
        // IntStream intStream2 = IntStream.rangeClosed(1, 10);
        // intStream2.filter(i -> i % 2 == 0).forEach(System.out::println);
    }

    //1
    public static void stream_test() {
        Stream<Integer> evenStream = Stream.iterate(0, n -> n + 2);
        System.out.println(evenStream);
    }

    //2
    private static void allMatch_anyMatch_noneMatch_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        int[] intArr = {2, 4, 6};
        System.out.println("//intArr: " + Arrays.toString(intArr));
        // 이 스트림의 모든 요소가 제공된 술어와 일치하는지 여부를 리턴합니다.
        boolean result = Arrays.stream(intArr).allMatch(a -> a % 2 == 0);
        System.out.println("//allMatch>>>2의 배수? " + result);
        //이 스트림의 요소가 제공된 술어와 일치하는지 여부를 리턴합니다.
        result = Arrays.stream(intArr).anyMatch(a -> a % 3 == 0);
        System.out.println("//anyMatch>>>3의 배수가 하나라도 있나? " + result);
        //이 스트림의 요소가 제공된 술어와 일치하지 않는지 여부를 반환합니다.
        result = Arrays.stream(intArr).noneMatch(a -> a % 3 == 0);
        System.out.println("//noneMatch>>>3의 배수가 없나? " + result);
        System.out.println();
    }

    //3
    private static void builder_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        // 스트림에 대한 빌더를 반환합니다.
        System.out.println("//builder add 'A', 'B', 'C'");
        Stream<String> builderStream =
                Stream.<String>builder()
                        .add("A")
                        .add("B")
                        .add("C")
                        .build(); // [Eric, Elena, Java]
        builderStream.forEach(System.out::println);
        System.out.println();
    }

    //4
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
        System.out.println("// Collectors로 평균 값 구하기");
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

    //5
    private static void concat_test() {
        //첫 번째 스트림의 모든 요소와 두 번째 스트림의 모든 요소가 뒤따르는 지연 연결된 스트림을 만듭니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("//스트림 연결");
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

    //xx
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

    //6
    private static void count_test() {
        //이 스트림의 요소 수를 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("//\"o\"가 포함된 Stream의 개수를 찾습니다.");
        List<String> words = Arrays.asList("book", "desk", "keyboard", "mouse", "cup");
        System.out.println("words: " + words);
        int count = (int) words.stream().filter(w -> w.contains("o")).count();
        System.out.println("count >" + count);
        System.out.println();
    }

    //7
    private static void distinct_test() {
        //이 스트림의 고유한 요소(Object.equals(Object)에 따라)로 구성된 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("//중복 제거");
        List<String> strings =
                Arrays.asList("google", "apple", "google", "apple", "samsung");
        System.out.println("strings: " + strings);
        Stream<String> stream1 = strings.stream();
        Stream<String> stream2 = stream1.distinct();
        stream2.forEach(System.out::println);
        System.out.println();
    }

    //8
    private static void empty_test() {
        //Stream.empty()는 비어있는 스트림을 생성합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("//Stream.empty()는 비어있는 스트림을 생성합니다.");
        Stream<String> stream = Stream.empty();
        System.out.println("stream");
        stream.forEach(System.out::println);
        System.out.println();
    }

    //9
    private static void filter_test() {
        //주어진 술어와 일치하는 이 스트림의 요소로 구성된 스트림을 리턴합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("//c로 시작하는 문자열만 필터링하도록 조건");
        List<String> list =
                Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
        System.out.println("list: " + list);
        Stream<String> stream1 = list.stream();
        Stream<String> filtered = stream1.filter(s -> s.startsWith("c"));
        filtered.forEach(System.out::println);
        System.out.println();
    }

    //10
    private static void findAny_findFirst_test() {
        //스트림의 일부 요소를 설명하는 Optional을 반환하거나 스트림이 비어 있으면 빈 Optional을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> elements = Arrays.asList("a", "a1", "b", "b1", "b2", "c", "c1");
        System.out.println("elements: " + elements);
        System.out.println("//스트림의 처리가 싱글스레드에서 동작하기 때문에 스트림의 첫번째 아이템부터 탐색");

        //findAny
        Optional<String> anyElement = elements.stream()
                .filter(s -> s.startsWith("b")).findAny();
        System.out.println("findAny: " + anyElement.get());

        //findFirst
        Optional<String> firstElement = elements.stream()
                .filter(s -> s.startsWith("b")).findFirst();
        System.out.println("findFirst: " + firstElement.get());

        //findFirst() vs findAny()
        //Stream을 직렬로 처리할 때 findFirst()와 findAny()는 동일한 요소를 리턴하며, 차이점이 없습니다.
        //하지만 Stream을 병렬로 처리할 때는 차이가 있습니다.
        //findFirst()는 여러 요소가 조건에 부합해도 Stream의 순서를 고려하여 가장 앞에 있는 요소를 리턴합니다.
        //반면에 findAny()는 Multi thread에서 Stream을 처리할 때 가장 먼저 찾은 요소를 리턴합니다. 따라서 Stream의 뒤쪽에 있는 element가 리턴될 수 있습니다.

        //아래 코드는 Stream을 병렬(parallel())로 처리할 때, findFirst()를 사용하는 예제입니다. 여기서 findFirst()는 항상 b를 리턴합니다.
        //findFirst-parallel()
        System.out.println("//stream()에 .parallel()을 붙이면 스트림의 처리가 병렬로 동작");
        Optional<String> firstElement2 = elements.stream().parallel()
                .filter(s -> s.startsWith("b")).findFirst();
        System.out.println("findFirst2: parallel(): " + firstElement2.get());

        //아래 코드는 Stream을 병렬로 처리할 때, findAny()를 사용하는 예제입니다. 여기서 findAny()는 실행할 때마다 리턴 값이 달라지며, b1 또는 b를 리턴합니다.
        //findAny-parallel()
        Optional<String> anyElement2 = elements.stream().parallel()
                .filter(s -> s.startsWith("b")).findAny();
        System.out.println("findAny2: parallel(): " + anyElement2.get());
        System.out.println();
    }

    //11
    private static void flatMap_test() {
        //이 스트림의 각 요소를 각 요소에 제공된 매핑 기능을 적용하여 생성된 매핑된 스트림의 내용으로 대체한 결과로 구성된 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        String[][] arrays = new String[][]{{"a1", "a2"}, {"b1", "b2"}, {"c1", "c2", "c3"}};
        System.out.println("//스트림의 각 요소를 각 요소에 제공된 매핑 기능을 적용하여 생성된 매핑된 스트림의 내용으로 대체");
        Stream<String[]> stream4 = Arrays.stream(arrays);
        Stream<String> stream5 = stream4.flatMap(s -> Arrays.stream(s));
        stream5.forEach(System.out::println);
        System.out.println();
    }

    //12
    private static void flatMapToDouble_test() {
        //이 스트림의 각 요소를 각 요소에 제공된 매핑 함수를 적용하여 생성된 매핑된 스트림의 내용으로 대체한 결과로 구성된 DoubleStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //ex1
        System.out.println("ex1 flatMapToDouble_test");
        List<String> list = Arrays.asList("1.5", "2.7", "3", "4", "5.6");
        System.out.println("list: " + list);
        list.stream().flatMapToDouble(num
                -> DoubleStream.of(Double.parseDouble(num)))
                .forEach(System.out::println);
        //ex2
        System.out.println("ex2 flatMapToDouble_test");
        List<String> list2 = Arrays.asList("Geeks", "GFG", "GeeksforGeeks", "gfg");
        System.out.println("list2: " + list2);
        list2.stream().flatMapToDouble(str
                -> DoubleStream.of(str.length()))
                .forEach(System.out::println);
        System.out.println();
    }

    //13
    private static void flatMapToInt_test() {
        //이 스트림의 각 요소를 각 요소에 제공된 매핑 함수를 적용하여 생성된 매핑된 스트림의 내용으로 대체한 결과로 구성된 IntStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //ex1
        System.out.println("ex1 flatMapToInt_test");
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println("list: " + list);
        list.stream().flatMapToInt(num
                -> IntStream.of(Integer.parseInt(num)))
                .forEach(System.out::println);
        //ex2
        System.out.println("ex2 flatMapToInt_test");
        List<String> list2 = Arrays.asList("Geeks", "GFG", "GeeksforGeeks", "gfg");
        System.out.println("list2: " + list2);
        list2.stream().flatMapToInt(str
                -> IntStream.of(str.length()))
                .forEach(System.out::println);
        System.out.println();
    }

    //14
    private static void flatMapToLong_test() {
        //이 스트림의 각 요소를 각 요소에 제공된 매핑 기능을 적용하여 생성된 매핑된 스트림의 내용으로 대체한 결과로 구성된 LongStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //ex1
        System.out.println("ex1 flatMapToLong_test");
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println("list: " + list);
        list.stream().flatMapToLong(num ->
                LongStream.of(Long.parseLong(num))).
                forEach(System.out::println);
        //ex2
        System.out.println("ex2 flatMapToLong_test");
        List<String> list2 = Arrays.asList("Geeks", "GFG",
                "GeeksforGeeks", "gfg");
        System.out.println("list2: " + list2);
        list2.stream().flatMapToLong(str ->
                LongStream.of(str.length())).
                forEach(System.out::println);
        System.out.println();
    }

    //15
    private static void forEach_test() {
        //이 스트림의 각 요소에 대해 작업을 수행합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        String[] arr = {"1", "2", "3"};
        System.out.println("//arr: " + Arrays.toString(arr));
        System.out.print("for: ");
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();

        int count = 0;
        String[] arr1 = {"Geeks", "For", "Geeks"};
        System.out.println("//arr1: " + Arrays.toString(arr1));
        System.out.print("for: ");
        for (String str : arr) {
            System.out.print(arr1[count++]);
        }
        System.out.println();

        List<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println("//list: " + list);
        System.out.print("stream: ");
        list.forEach(System.out::print);
        System.out.println();

        System.out.print("parallelStream: ");
        list.parallelStream().forEach(System.out::print);
        System.out.println();
        System.out.println();
    }

    //16
    private static void forEachOrdered_test() {
        //스트림에 정의된 조우 순서가 있는 경우 스트림의 조우 순서대로 이 스트림의 각 요소에 대해 작업을 수행합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //ex1
        System.out.println("ex1 forEachOrdered_test");
        List<Integer> list = Arrays.asList(10, 19, 20, 1, 2);
        list.stream().forEachOrdered(System.out::println);
        System.out.println();

        //ex2
        System.out.println("ex2 forEachOrdered_test");
        Stream<String> stream = Stream.of("GFG", "Geeks", "for", "GeeksforGeeks");
        stream.flatMap(str -> Stream.of(str.charAt(2)))
                .forEachOrdered(System.out::println);
        System.out.println();

    }

    //17
    private static void generate_test() {
        //제공된 공급자에 의해 각 요소가 생성되는 무한 순차 무순 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("ex1 Random()::nextInt ");
        Stream.generate(new Random()::nextInt)
                .limit(5).forEach(System.out::println);
        System.out.println("ex2 Random()::nextDouble ");
        Stream.generate(new Random()::nextDouble)
                .limit(8).forEach(System.out::println);
        System.out.println();
    }

    //18
    private static void iterate_test() {
        //초기 요소 시드에 함수 f를 반복적으로 적용하여 시드, f(시드), f(f(시드)) 등으로 구성된 스트림을 생성하여 생성된 무한 순차 순서 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        // Stream of 0 – 9
        System.out.println("ex1 Stream of 0 – 9");
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("ex2 Stream of odd numbers only.");
        Stream.iterate(0, n -> n + 1)
                .filter(x -> x % 2 != 0) //odd
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("ex3 A classic Fibonacci example.");
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .map(n -> n[0])
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        System.out.println("ex4 Sum all the Fibonacci values.");
        int sum = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(10)
                .map(n -> n[0]) // Stream<Integer>
                .mapToInt(n -> n)
                .sum();
        System.out.println("Fibonacci 10 sum : " + sum);
        System.out.println();
////
        int result = 0;
        int n = 11;
        if (n == 0) result = 0; // 제 0항은 0을 반환한다.
        else if (n == 1) result = 1; // 제 1항은 1을 반환한다.
        else {
            int iterA = 0;
            int iterB = 1;
            for (int i = 2; i <= n; i++) {
                result = iterA + iterB;
                iterA = iterB;
                iterB = result;
            } // n항의 값을 구한다.
            System.out.println("original fibonacci result: " + result);
            System.out.println();
        }
    }

    //19
    private static void limit_test() {
        //이 스트림의 요소로 구성된 스트림을 반환하고 길이가 maxSize보다 길지 않도록 잘립니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        Stream<Integer> evenNumInfiniteStream = Stream.iterate(0, n -> n + 2);
        List<Integer> newList = evenNumInfiniteStream.limit(10)
                .collect(Collectors.toList());
        System.out.println(newList);
        System.out.println();
    }

    //20
    private static void map_test() {
        //지정된 함수를 이 스트림의 요소에 적용한 결과로 구성된 스트림을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("ex1 map_test");
        List<Integer> list = Arrays.asList(3, 6, 9, 12, 15);
        List<Integer> listx = list.stream().map(number -> number * 3)
                .collect(Collectors.toList()); //collect 추가
        //.forEach(System.out::println);
        System.out.println(listx);

        System.out.println("ex2 map_test");
        List<String> list2 = Arrays.asList("geeks", "gfg", "g",
                "e", "e", "k", "s");
        List<String> answer = list2.stream().map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(answer);
        System.out.println();
    }

    //21
    private static void mapToDouble_test() {
        //이 스트림의 요소에 지정된 함수를 적용한 결과로 구성된 DoubleStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("ex1 mapToDouble_test");
        List<String> list = Arrays.asList("10", "6.548", "9.12", "11", "15");
        System.out.println("list: " + list);
        list.stream().mapToDouble(num -> Double.parseDouble(num))
                .filter(num -> (num * num) * 2 == 450)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("ex2 mapToDouble_test");
        List<String> list2 = Arrays.asList("CSE", "JAVA", "gfg", "C++", "C");
        System.out.println("list2: " + list2);
        list2.stream().mapToDouble(str -> str.length() * str.length())
                .forEach(System.out::println);
        System.out.println();
    }

    //21
    private static void mapToInt_test() {
        //주어진 함수를 이 스트림의 요소에 적용한 결과로 구성된 IntStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> list = Arrays.asList("3", "6", "8", "14", "15");
        System.out.println("list: " + list);
        list.stream().mapToInt(num -> Integer.parseInt(num))
                .filter(num -> num % 3 == 0)
                .forEach(System.out::println);
        System.out.println();
    }

    //22
    private static void mapToLong_test() {
        //주어진 함수를 이 스트림의 요소에 적용한 결과로 구성된 LongStream을 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> list = Arrays.asList("25", "225", "1000", "20", "15");
        System.out.println("list: " + list);
        list.stream().mapToLong(num -> Long.parseLong(num))
                .filter(num -> Math.sqrt(num) / 5 == 3)
                .forEach(System.out::println);
        System.out.println();
    }

    //23
    private static void max_min_test() {
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //max
        //제공된 Comparator에 따라 이 스트림의 최대 요소를 반환합니다.
        List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4);
        System.out.println("list: " + list);
        System.out.print("The maximum value is : ");
        Integer var = list.stream().max(Integer::compare).get();
        System.out.print(var);
        System.out.println();
        //min
        //제공된 Comparator에 따라 이 스트림의 최소 요소를 반환합니다.
        System.out.print("The minimum value is : ");
        Integer var2 = list.stream().min(Integer::compare).get();
        System.out.print(var2);
        System.out.println();
    }

    //24
    private static void noneMatch_test() {
        //이 스트림의 요소가 제공된 술어와 일치하지 않는지 여부를 반환합니다.
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("ex1 noneMatch_test: To check that there is no string of length 4.");
        Stream<String> stream = Stream.of("CSE", "C++", "Jav", "DS");
        boolean answer = stream.noneMatch(str -> (str.length() == 4));
        System.out.println(answer);
        System.out.println();

        System.out.println("ex2 noneMatch_test: To check that there is no element with required characters at required position.");
        Stream<String> stream2 = Stream.of("Geeks", "fOr", "GEEKSQUIZ", "GeeksforGeeks", "CSe");
        boolean answer2 = stream2.noneMatch
                (str -> Character.isUpperCase(str.charAt(1))
                        && Character.isLowerCase(str.charAt(2))
                        && str.charAt(0) == 'f');
        System.out.println(answer2);
        System.out.println();
    }

    private static void test() {
        //
        System.out.println("***" + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println();
    }

}
