import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main2 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Stream<Integer> integerStream = List.of(1, 2, 4, -4, 6).stream().filter(i -> i > 3).map(k -> k);
        integerStream.forEach(s-> System.out.println(s));
        //integerStream.forEach(s-> System.out.println(s));
        List.of(1, 2, 4, -4, 6).stream().collect(Collectors.toCollection(LinkedList::new));
        /*
        Collectors.partitioningBy()
        Collectors.groupingBy()
        Collectors.toMap()
         */
        System.out.println("variant 1");
        Map<Integer, String> map1 = Stream.of("a", "aa", "bb","bbb").collect(Collectors.toMap(s -> s.length(), s -> s,(s1,s2)-> s1 +"/" + s2));
        System.out.println(map1);
        System.out.println("variant 2");
        Map<Boolean, List<String>> map2 = Stream.of("a", "aa", "bb", "bbb").collect(Collectors.partitioningBy(s -> s.length() > 2));
        System.out.println(map2);
        System.out.println("variant 3");
        Map<Integer, List<String>> map3 = Stream.of("a", "aa","aa", "bb", "bbb").collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(map3);
        System.out.println("variant 4");
        Map<Integer, Set<String>> map4 = Stream.of("a", "aa","aa", "bb", "bbb").collect(Collectors.groupingBy(s -> s.length(), Collectors.toSet() ));
        System.out.println(map4);
        System.out.println("variant 5");
        Map<Integer, Long> map5 = Stream.of("a", "aa","aa", "bb", "bbb").collect(Collectors.groupingBy(s -> s.length(), Collectors.counting()));
        System.out.println(map5);
        System.out.println("variant 6");
        Map<Integer, List<String>> map6 = Stream.of("a", "aa","aa", "bb", "bbb").collect(Collectors.groupingBy(s -> s.length(), Collectors.mapping(s->"{"+s+"}",Collectors.toList())));
        System.out.println(map6);
    }
}
