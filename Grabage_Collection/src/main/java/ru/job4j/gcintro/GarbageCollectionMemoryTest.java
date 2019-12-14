package ru.job4j.gcintro;

import com.ibm.icu.math.BigDecimal;

public class GarbageCollectionMemoryTest {

    public static class User {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Objects destroyed");
        }


        public static void info() {
            int m = 1024 * 1024;
            Runtime runtime = Runtime.getRuntime();
            System.out.println();
            System.out.println("Heap memory usage[KB]");
            System.out.println("Total memory: " + runtime.totalMemory() / m);
            System.out.println("Free memory: " + runtime.freeMemory() / m);
            System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / m);
            System.out.println("Maximum memory: " + runtime.maxMemory() / m);
            System.out.println();
        }
    }



    public static void main(String[] args) {
        User.info();
        BigDecimal[] num = new BigDecimal[10];
        num = num;
        System.gc();
        User.info();
    }
}
