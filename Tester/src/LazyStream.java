import java.util.stream.Stream;

public class LazyStream {
    public static void main(String[] args) {
        Stream.iterate(0, i -> i + 1)
              .flatMap(i -> Stream.of(i, i, i, i))
              .map(i -> i + 1)
              .peek(i -> System.out.println("Map: " + i))
              .limit(5)
              .forEach(i -> {});
  
        System.out.println("----------------------------");
        System.out.println("----------------------------");
  
        Stream.iterate(0, i -> i + 1)
              .flatMap(i -> Stream.of(i, i, i, i))
              .limit(5)
              .map(i -> i + 1)
              .peek(i -> System.out.println("Map: " + i))
              .forEach(i -> {});
    }
}