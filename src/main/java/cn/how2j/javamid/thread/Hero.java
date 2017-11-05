package cn.how2j.javamid.thread;

public class Hero implements Comparable<Hero> {
    public String name;
    public float hp;

    public int damage;
    public int id;

    public Hero(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero() {

    }

    public Hero(String string) {
        name = string;
    }

    public boolean matched() {
        return this.hp > 100 && this.damage < 50;
    }

    @Override
    public int compareTo(Hero anotherHero) {
        if (damage < anotherHero.damage)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }

    public void attackHero(Hero h) {
        try {
            //为了表示攻击需要时间，每次攻击暂停1000毫秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        h.hp -= damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);

        if (h.isDead())
            System.out.println(h.name + "死了！");
    }

    public boolean isDead() {
        return 0 >= hp ? true : false;
    }

    public void recover() {
        hp++;
    }

    public void hurt() {
        hp--;
    }

}