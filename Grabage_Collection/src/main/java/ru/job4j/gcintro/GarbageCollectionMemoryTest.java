package ru.job4j.gcintro;

public class GarbageCollectionMemoryTest {

    public static class User {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Objects destroyed");
        }


        public static void info() {
            int b = 1;
            Runtime runtime = Runtime.getRuntime();
            System.out.println();
            System.out.println("Heap memory usage[KB]");
            System.out.println("Total memory: " + runtime.totalMemory() / b);
            System.out.println("Free memory: " + runtime.freeMemory() / b);
            System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / b);
            System.out.println("Maximum memory: " + runtime.maxMemory() / b);
            System.out.println();
        }
    }



    public static void main(String[] args) {
        User.info();
        User user1 = new User();
        System.out.println(user1);
        user1 = null;
//        System.gc();
        User.info();
    }
}
