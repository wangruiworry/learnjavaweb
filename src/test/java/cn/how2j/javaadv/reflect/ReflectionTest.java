package cn.how2j.javaadv.reflect;

import cn.how2j.javamid.thread.Hero;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {
    //读取配置文件，生成类对象
    public static Hero getHero() {
        File f = new File("D:/IdeaProjects/testIDEA/chapter1/src/main/resources/hero.config");
        FileReader fr = null;
        //读取文件
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //首先从文件中找到类的名称
        char[] all = new char[(int) f.length()];
        try {
            fr.read(all);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String className = new String(all);
        Class clazz = null;
        Hero hero = null;
        //然后获取类对象
        try {
            clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor();
            hero = (Hero) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //为了构造一个对象，是需要一个构造器
        return hero;
    }

    @Test
    public void reflect() {
        //传统的使用new的方式创建对象
        Hero h1 = new Hero();
        h1.name = "teemo";
        System.out.println(h1);

        try {
            //使用反射的方式创建对象
            String className = "cn.how2j.javamid.thread.Hero";
            //类对象
            Class pClass = Class.forName(className);
            //构造器
            Constructor c = pClass.getConstructor();
            //通过构造器实例化
            Hero h2 = (Hero) c.newInstance();
            h2.name = "gareen";
            System.out.println(h2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reflectConfig() {
        Hero h = getHero();
        System.out.println(h);
    }

    @Test
    public void reflectField() {
        Hero h = new Hero();
        //使用传统方式修改name的值为gareen
        h.name = "gareen";
        try {
            //获取类Hero的名字叫做name的字段
            Field f1 = h.getClass().getDeclaredField("name");
            //修改这个字段的值
            f1.set(h, "teemo");
            //打印被修改后的值
            System.out.println(h.name);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Hero h = new Hero();

        try {
            // 获取这个类的名字叫做setName，参数类型是String的方法
            Method m = h.getClass().getMethod("setName", String.class);
            // 对h对象，调用这个方法
            m.invoke(h, "盖伦");
            // 使用传统的方式，调用getName方法
            System.out.println(h.getName());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}