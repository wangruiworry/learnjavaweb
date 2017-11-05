package cn.how2j.javamid.lambda;

import cn.how2j.javamid.thread.Hero;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class LambdaTest {
    @Test
    public void normal() {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 10; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("筛选出 hp>100 && damange<50的英雄");
        filter(heros);
    }

    private static void filter(List<Hero> heros) {
        for (Hero hero : heros) {
            if (hero.hp > 100 && hero.damage < 50)
                System.out.print(hero);
        }
    }

    @Test
    public void anonymity() {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("使用匿名类的方式，筛选出 hp>100 && damange<50的英雄");
        HeroChecker checker = new HeroChecker() {
            @Override
            public boolean test(Hero h) {
                return (h.hp > 100 && h.damage < 50);
            }
        };

        filter(heros, checker);
    }

    private static void filter(List<Hero> heros, HeroChecker checker) {
        for (Hero hero : heros) {
            if (checker.test(hero))
                System.out.print(hero);
        }
    }

    @Test
    public void lambda() {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("使用Lamdba的方式，筛选出 hp>100 && damange<50的英雄");
        filter(heros, h -> h.hp > 100 && h.damage < 50);
    }

    @Test
    public void lambdaStatic() {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);

        HeroChecker c = new HeroChecker() {
            public boolean test(Hero h) {
                return h.hp > 100 && h.damage < 50;
            }
        };

        System.out.println("使用匿名类过滤");
        filter(heros, c);
        System.out.println("使用Lambda表达式");
        filter(heros, h -> h.hp > 100 && h.damage < 50);
        System.out.println("在Lambda表达式中使用静态方法");
        filter(heros, h -> LambdaTest.testHero(h));
        System.out.println("直接引用静态方法");
        filter(heros, LambdaTest::testHero);
    }

    public static boolean testHero(Hero h) {
        return h.hp > 100 && h.damage < 50;
    }

    @Test
    public void lambdaConstructor() {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);

        System.out.println("Lambda表达式：");
        filter(heros, h -> h.hp > 100 && h.damage < 50);

        System.out.println("Lambda表达式中调用容器中的对象的matched方法：");
        filter(heros, h -> h.matched());

        System.out.println("引用容器中对象的方法 之过滤结果：");
        filter(heros, Hero::matched);
    }

    @Test
    public void lambdaCons() {
        Supplier<List> s = new Supplier<List>() {
            public List get() {
                return new ArrayList();
            }
        };

        //匿名类
        List list1 = getList(s);

        //Lambda表达式
        List list2 = getList(() -> new ArrayList());

        //引用构造器
        List list3 = getList(ArrayList::new);

    }

    public static List getList(Supplier<List> s) {
        return s.get();
    }

    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }

        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("查询条件：hp>100 && damage<50");
        System.out.println("通过传统操作方式找出满足条件的数据：");

        for (Hero h : heros) {
            if (h.hp > 100 && h.damage < 50)
                System.out.println(h.name);
        }

        System.out.println("通过聚合操作方式找出满足条件的数据：");
        heros
                .stream()
                .filter(h -> h.hp > 100 && h.damage < 50)
                .forEach(h -> System.out.println(h.name));

    }
}