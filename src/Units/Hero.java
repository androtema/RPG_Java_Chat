package Units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Hero extends GameUnit {
    private static int experience;
    private static int gold;
    private static int level;
    private HashMap<String, Integer> bagage = new HashMap<>();                                         //рюкзак георя. Map - для возможности

    //-изменять кол-во элементов в рюкзаке
    public Hero(String name) {
        super(100, 5, 5, name);
        level = 0;
        gold = 100;
        experience = 0;
    }

    public void setBagage(String s, Integer i) {
        bagage.put(s, i);
    }

    public HashMap<String, Integer> getBagage() {
        return bagage;
    }

    public void getStringBagage() {
        if (bagage.isEmpty()) {
            System.out.println("Рюкзак пуст");
        } else bagage.forEach((k, v) -> System.out.println(k + " - " + v + " шт."));
    }

    public void drinkHP() {                                                                         //восстановление Здоровья
        Scanner stringHP = new Scanner(System.in);
        boolean work = true;
        while (work) {
            switch (stringHP.nextLine()) {
                case "50":
                    if (bagage.get("Зелье 50хп") != null) {                      //проверка что такое зелье есть в рюкзаке
                        setHP(Math.min(getHP() + 50, 100));                     //проверка чтобы было не больше 100хп
                        if (bagage.get("Зелье 50хп") == 1) {                     //проверяем сколько зелий было в рюкзаке (для изм. отображения в коллекции)
                            bagage.remove("Зелье 50хп");                    //удаляем из коллекции
                        } else {
                            bagage.replace("Зелье 50хп", bagage.get("Зелье 50хп") - 1);     //уменьшаем на один
                        }
                        work = false;
                        break;
                    } else {
                        System.out.println("У вас нет необходимого зелья\nВведите корректное значение");
                        break;
                    }
                case "100":
                    if (bagage.get("Зелье 100хп") != null) {
                        setHP(100);
                        if (bagage.get("Зелье 100хп") == 1) {
                            bagage.remove("Зелье 100хп");
                        } else {
                            bagage.replace("Зелье 100хп", bagage.get("Зелье 100хп") - 1);
                        }
                        work = false;
                        break;
                    } else {
                        System.out.println("У вас нет необходимого зелья\nВведите корректное значение");
                        break;
                    }
                default:
                    System.out.println("Введите корректное значение");
                    break;
            }
        }
    }

    public void setExperience(int experience) {
        Hero.experience = experience;
    }

    public void setGold(int gold) {
        Hero.gold = gold;
    }

    public void setLevel(int level) {
        Hero.level = level;
    }

    public int getLevel() {
        return level;
    }


    public int getGold() {
        return gold;
    }

    public void getStringGold() {
        System.out.printf("gold %d\n", gold);
    }

    public int getExperience() {
        return experience;
    }

    public void getExp() {
        System.out.printf("level %d, exp: %d%n", level, experience);
    }

    @Override
    public void defaultParams() {
    }     //не нужен. Можно ли убрать???
}
