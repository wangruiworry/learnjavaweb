
package cn.how2j.javamid.thread;


import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeroTest {

    @Test
    public void singleThread() {

        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        //盖伦攻击提莫
        while (!teemo.isDead()) {
            gareen.attackHero(teemo);
        }

        //赏金猎人攻击盲僧
        while (!leesin.isDead()) {
            bh.attackHero(leesin);
        }
    }

    @Test
    public void multiThread() {

        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        KillThread killThread1 = new KillThread(gareen, teemo);
        killThread1.start();
        KillThread killThread2 = new KillThread(bh, leesin);
        killThread2.start();
    }

    @Test
    public void runnable() {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;
        Hero timo = new Hero();
        timo.name = "提莫";
        timo.hp = 300;
        timo.damage = 30;
        Hero shangjin = new Hero();
        timo.name = "赏金猎人";
        timo.hp = 500;
        timo.damage = 65;
        Hero lessin = new Hero();
        timo.name = "盲僧";
        timo.hp = 455;
        timo.damage = 80;
        new Thread(new Battle(gareen, timo)).start();
        new Thread(new Battle(shangjin, lessin)).start();
    }

    @Test
    public void anonymity() {
        final Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;
        final Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;
        final Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;
        final Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (!teemo.isDead()) {
                    gareen.attackHero(teemo);
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            public void run() {
                while (!leesin.isDead()) {
                    bh.attackHero(leesin);
                }
            }
        };
        t2.start();
    }

    @Test
    public void synpho() {

        final Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 10000;

        System.out.printf("盖伦的初始血量是 %.0f%n", gareen.hp);

        //多线程同步问题指的是多个线程同时修改一个数据的时候，导致的问题

        //假设盖伦有10000滴血，并且在基地里，同时又被对方多个英雄攻击

        //用JAVA代码来表示，就是有多个线程在减少盖伦的hp
        //同时又有多个线程在回复盖伦的hp

        //n个线程增加盖伦的hp

        int n = 10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];

        for (int i = 0; i < n; i++) {
            Thread t = new Thread() {
                public void run() {
                    gareen.recover();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;

        }

        //n个线程减少盖伦的hp
        for (int i = 0; i < n; i++) {
            Thread t = new Thread() {
                public void run() {
                    gareen.hurt();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }

        //等待所有增加线程结束
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等待所有减少线程结束
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //代码执行到这里，所有增加和减少线程都结束了

        //增加和减少线程的数量是一样的，每次都增加，减少1.
        //那么所有线程都结束后，盖伦的hp应该还是初始值

        //但是事实上观察到的是：

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量变成了 %.0f%n", n, n, gareen.hp);

    }

    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    @Test
    public void inter() {
        final Hero h = new Hero();
        h.hp = 100;
        h.name = "C罗";
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println(now() + this.getName() + " 试图给" + h.name + "加血");
                synchronized (h) {
                    System.out.println(now() + this.getName() + " 给" + h.name + "加血1");
                    h.recover();
                    System.out.println(now() + this.getName() + " 已经给" + h.name + "加血1，现在释放对英雄的占有");
                }
                System.out.println(now() + " t1线程结束");
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println(now() + this.getName() + " 试图给" + h.name + "减血");
                synchronized (h) {
                    System.out.println(now() + this.getName() + " 给" + h.name + "减血1");
                    if (h.hp > 1)
                        h.hurt();
                    System.out.println(now() + this.getName() + " 已经给" + h.name + "减血1，现在释放对英雄的占有");
                }
                System.out.println(now() + " t2线程结束");
            }
        };
        t2.start();
    }
}