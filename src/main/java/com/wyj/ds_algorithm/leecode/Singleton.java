package com.wyj.ds_algorithm.leecode;

/**
 * @author wuyingjie <13693653307@163.com>
 * Created on 2021-05-12
 */
public class Singleton {

    /**
     * 双重校验锁
     */
    public static class Singleton1 {
        // 限制指令重排序的
        private static volatile Singleton1 singleton;

        private Singleton1() {
        }

        public static Singleton1 get() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton1();
                    }
                }
            }
            return singleton;
        }
    }

    // 静态内部类
    // 关于多线程下 是怎么保证线程安全 看 https://blog.csdn.net/ZytheMoon/article/details/89929524
    public static class Singleton2 {

        private Singleton2() {};

        public static Singleton2 get() {
            return Singleton2Holder.instance;
        }

        private static class Singleton2Holder {
            private static final Singleton2 instance = new Singleton2();
        }
    }

    // 如果涉及到 反射和序列化了，那就用枚举
    public enum Singleton3 {
        INSTANCE
        ;

        public static Singleton3 get() {
            return INSTANCE;
        }

    }


}
