package java8stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {

        //---------------------------------------类型推断-----------------------------------------------
        //http://www.imooc.com/article/details/id/22660
        //最简单直白的理解，就是不用显示地指明类型，编译器会进行类型推断。名称比类型更加重要。

//        //------------------------------------流的创建----------------------------------------------
////        创建Stream
////        1.可以通过Collection系列集合提供的stream()获取串行流或parallelStream()获取并行流
//        //内部迭代
//        System.out.println("------------内部迭代---------------------");
//        List<String> list = new ArrayList<>();
//        list.add("lalalal1");
//        list.add("xixixiix2");
//        list.add("wanna outttt and play3");
//        Stream<String> stream1 = list.stream().filter((e) -> {
//            System.out.println("Stream API的中间操作:"+e);
//            return true;
//        });
//        stream1.forEach(System.out::println);
//
//        System.out.println("------------外部迭代---------------------");
//        //外部迭代
//        Stream<String> stream = list.stream();
//        Iterator<String> iterator = stream.iterator();
//        while (iterator.hasNext()){
//            System.out.println("iterator.next(): "+iterator.next());
//        }
//        System.out.println(stream1);
//
//        //2.通过Arrays中的静态方法stream()获取数组流
//        Employee[] emps = new Employee[10];
//        Stream<Employee> stream2 = Arrays.stream(emps);
//
//        //3.通过Stream类中的静态方法of()
//        Stream<String> stream3 = Stream.of("aa", "bb", "cc");
//
//        System.out.println("-------------------中间操作和终止操作--------------");
//        //4.创建无限流
//        //通过迭代创建无限流
//        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
//        //中间操作和终止操作
//        stream4.limit(10).forEach(System.out::println);
//
//        System.out.println("---------------通过生成随机数创建无限流------------------");
//        //通过生成随机数创建无限流
//        Stream.generate(() -> Math.random())
//                .limit(5)
//                .forEach(System.out::println);


//        //---------------------------------------------筛选与切片---------------------------------------
//
//        List<Integer> list = new ArrayList<>();
//        list.add(5022);
//        list.add(5055);
//        list.add(5055);
//        list.add(5055);
//        list.add(50554);
//        list.add(1);
//
//
//        list.stream()
//            .filter((e) -> {
//                System.out.println("只选前两个");
//                return true;
//            })
//            .limit(2)
//            .forEach(System.out::println);
//
//        System.out.println("--------------------------------");
//
//
//            list.stream()
//                    .filter((e) -> e> 5000)
//                    .skip(2)
//                    .forEach(System.out::println);
//
//        System.out.println("--------------------------------");
//
//            list.stream()
//                    .filter((e) -> e > 5000)
//                    .skip(2)
//                    .distinct()
//                    .forEach(System.out::println);


//        //-------------------------------惰性求值和及早求值----------------------------------
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//
//        //根本不会执行
//        //实际下面的一段代码是没有任何输出的；
//        //而原因则是filter只刻画了stream，但是并没有产生新的集合，而像这种没有实际功能，只是描述stream的操作，就叫做惰性求值；
//        list.stream().filter(x -> {
//            System.out.println(x);
//            return true;
//        });
//
//        //如果在filter后加上方法，比如count()，则会执行print语句，代码如下：
//        //这种操作则是及早求值。
//        //想要判断是惰性求值与及早求值的方法也很简单，只要看返回值就好，如果是stream则是惰性求值，而如果是另一个值或是空值则是及早求值。
//        //整个过程也和建造者模式有共通之处，建造者模式使用一系列操作设置属性和配置，最后使用build方法，这时对象才会被创建。
//        long count = list.stream().filter(x -> {
//            System.out.println(x);
//            return true;
//        }).count();
//
//        System.out.println(count);

//        //-------------------------------------map和flatMap-----------------------------------------
//        //-------------------------------------例1-----------------------------------------
//        //* map(Function f) 接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，
//                                      该参数会被应用到每个元素上，并将其映射成一个新的元素。
//        //* mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。
//        //* mapToInt(ToIntFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。
//        //* mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。
//        //* flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
//        List<String> list = Arrays.asList("zhangsan","lisi","wangwu","zhaoliu");
//        list.stream().map(e->e.toUpperCase()).forEach(System.out::println);
//        list.stream().flatMap(e->{
//            List list1 = new ArrayList();
//            for(char x : e.toCharArray()){
//                list1.add(x);
//            }
//            return list1.stream();}).forEach(System.out::print);

//        //----------------------------------例2--------------------------------------
//        //todo 我没搞懂为什么是字符串却输出地址
        String[] words = new String[]{"Hello", "World"};
        List<String[]> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println();
//                .forEach(System.out::println);

//
//
//        System.out.println("-------------------------------------------");
//
//        //    使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容，
//        //    所有使用flatMap(Array::stream)时生成的单个流被合并起来，即扁平化为一个流。
//        Arrays.stream(words)
//                .map(word -> word.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .collect(toList())
//                .forEach(System.out::print);

//        //----------------------------------------排序-------------------------------------------------------------
//        //Stream<T> sorted();产生一个新的流，按照默认顺序排序
//        //Stream<T> sorted(Comparator<? super T> comparator);产生一个新流，其中按比较器顺序排序
//        List<Employee> emps = Arrays.asList(
//                new Employee("李四", 59, 6666.66),
//                new Employee("张三", 18, 9999.99),
//                new Employee("王五", 28, 3333.33),
//                new Employee("赵六", 8, 7777.77),
//                new Employee("赵六1", 8, 8777.77),
//                new Employee("赵六2", 8, 7777.77),
//                new Employee("田七", 38, 5555.55)
//        );
//
//        emps.stream()
//                .map(Employee::getAge)
//                .sorted()
//                .forEach(System.out::println).;
//
//        System.out.println("------------------------------------");
//
//        emps.stream()
//                .sorted((x, y) -> {
//                    if(x.getAge() == y.getAge()){
//                        return Double.compare(x.getPrice(),y.getPrice());
//                    }else{
//                        return Integer.compare(x.getAge(), y.getAge());
//                    }
//                }).forEach(e->System.out.println(e.getName()));

        //------------------------------------------串行流与并行流----------------------------------------------------
        //通过parallelStream方法可以获得一个并行流，那么什么是并行流呢？并行流就是把内容分割成多个数据块，
        // 每个数据块对应一个流，然后用多个线程分别处理每个数据块中的流。

        //Fork/Join框架与传统线程池的区别？
        //ForkJoin框架采用的是“工作窃取模式”，传统线程在处理任务时，
        // 假设有一个大任务被分解成了20个小任务，并由四个线程A,B,C,D处理，理论上来讲一个线程处理5个任务，
        // 每个线程的任务都放在一个队列中，当B,C,D的任务都处理完了，而A因为某些原因阻塞在了第二个小任务上，
        // 那么B,C,D都需要等待A处理完成，此时A处理完第二个任务后还有三个任务需要处理，可想而知，这样CPU的利用率很低。
        // 而ForkJoin采取的模式是，当B,C,D都处理完了，而A还阻塞在第二个任务时，
        // B会从A的任务队列的末尾偷取一个任务过来自己处，C和D也会从A的任务队列的末尾偷一个任务，
        // 这样就相当于B,C,D额外帮A分担了一些任务，提高了CPU的利用率。


//        long start = System.currentTimeMillis();
//        //1.ForkJoin框架也需要一个ForkJoin池来启动
//        ForkJoinPool pool = new ForkJoinPool();
//        //2.创建一个ForkJoinTask，RecursiveTask也是继承自ForkJoinTask，所以我们new自己写的那个计算类
//        ForkJoinTask<Long> task = new ForkJoinTest(0L, 100000000000L);
//        //3.执行计算
//        long sum = pool.invoke(task);
//        System.out.println(sum);
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("耗费的时间为: " + (end - start)); //5463

        /**
         * 测试用for循环计算0到1一亿的和
         */


//        long start1 = System.currentTimeMillis();
//        long sum1 = 0L;
//        for (long i = 0L; i <= 10000000000L; i++) {
//            sum1 += i;
//        }
//        System.out.println(sum1);
//        long end1 = System.currentTimeMillis();
//        System.out.println("耗费的时间为: " + (end1 - start1)); //7610

        /**
         * 测试用并行流计算0到1一亿的和
         */

//
//        long start2 = System.currentTimeMillis();
//        Long sum2 = LongStream.rangeClosed(0L, 10000000000L).parallel().sum();
//        System.out.println(sum2);
//        long end2 = System.currentTimeMillis();
//        System.out.println("耗费的时间为: " + (end2 - start2)); //2813
//

        /**
         * 总结：
         *  并行流的处理效率是比较高的，不过并行流底层也是使用的forkjoin框架，只是java8底层已经实现好了，forkjoin拆分合并任务也是需要时间的，对于计算量比较小的任务，拆分合并所花费的时间可能会大于计算时间，这时候用forkjoin拆分任务就会有点得不偿失了。
         * 1、使用parallelStream方法可以得到一个并行流，并行流底层使用的是forkjoin框架，对于一些计算量比较大的任务，使用并行流可能极大的提升效率。
         *
         * 2、ForkJoin框架的使用方式，
         *
         * 编写计算类继承RecursiveTask<T>接口并重写T compute方法；
         * 使用fork方法拆分任务，join合并计算结果；
         * 使用ForkJoinPool调用invoke方法来执行一个任务。
         * ————————————————
         * 版权声明：本文为CSDN博主「caishi13202」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/caishi13202/article/details/82667230
         */
    }

}
