package redis.HyperLogLog;

import java.util.concurrent.ThreadLocalRandom;

/*
HyperLogLog Counting 的原理：N = 2 ^ Kmax
 */
public class HyperLogLogCountingDemo {

    /**
     * 一个桶。用于模拟输入，并求出这个输入对应的Kmax
     */
    static class BitKeeper {
        /*
           这组桶中的Kmax
            因为value的值 < 2*2^32，即2的长度最大为32bit，
            所以K的最大值 < 32

            即每个桶大小为5bit即可。
         */
        private int maxbits;

        public void random() {
            //入参，（统计的ip地址）
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }

        /**
         * 返回value的二进制流中第一个1的位置K
         * @param value
         * @return
         */
        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    /**
     * HyperLogLog数据结构
     * 1个桶
     */
    static class Experiment {
        private int n;  //数据量
        private BitKeeper keeper;

        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }

        /**
         * 所有输入的Kmax的值（keeper.maxbit）
         */
        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }

        public void debug() {
            System.out.printf("集合的基数N = %d,\t 精确的Kmax(log2(N)) = %.2f,\t 只有1个桶的HLL算法求出的Kmax =  %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbits);
        }
    }

    public static void main(String[] args) {
        for (int i = 1000; i < 100000; i += 100) {
            Experiment exp = new Experiment(i);
            exp.work();
            exp.debug();
        }
    }
}