package java8stream;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Long> {
    private long first;
    private long last;

    public ForkJoinTest(long first, long last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Long compute() {
        long result = 0;
        long threshold = 10;
        if (last - first <= threshold) {
            for (long i = first; i <= last; i++) {
                result += i;
            }
        } else {
            long middle = (first + last) / 2;
            ForkJoinTest l = new ForkJoinTest(first, middle);
            ForkJoinTest r = new ForkJoinTest(middle + 1, last);
            l.fork();
            r.fork();
            result = l.join() + r.join();
        }
        return result;
    }
}