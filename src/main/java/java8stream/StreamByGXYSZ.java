package java8stream;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author bys
 * @date 2020/8/17 11:00
 */
public class StreamByGXYSZ {

    public static void main(String[] args) {
        String[] words = new String[]{"Hello", "Hello", "World"};

        // distinct() 有状态的中间操作，元素的处理受到前面元素的影响

        // 流里面是数组，使用distinct会对 数组地址 进行去重，地址不同不会去重
        Stream<String[]> stream = Arrays
                .stream(words)
                .map(word -> word.split(""));
        stream
                .distinct()
                .flatMap(Arrays::stream)
                .collect(toList())
                .forEach(System.out::print);
        // HelloHelloWorld

        System.out.println();

        // 流里面是字符串，使用distinct会对字符串进行对比去重，只剩下一个Hello
        Stream<String> stream1 = Arrays
                .stream(words);
        stream1
                .distinct()
                .collect(toList())
                .forEach(System.out::print);
        // HelloWorld

        System.out.println();

        // 流里面是字符串，flatMap对所有流进行合并形成一个整合流，存一个字符串 HelloHelloWorld，再去重相当于String字符串去重
        Stream<String> stream2 = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream);
        stream2
                .distinct()
                .collect(toList())
                .forEach(System.out::print);
        // HeloWrd

        System.out.println();
    }
}
