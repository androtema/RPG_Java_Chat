package Units;

import java.util.Scanner;

public class Trader extends GameUnit {
    private static int count100HP = 0;
    private static int count50HP = 0;
    long startTime = System.currentTimeMillis();

    public Trader() {
        super(1, 1, 0, "Торговец");
    }

    private void addProducts() {                                                                                         //увеличение зелий В НАЛИЧИИ у торговца
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        int timeAddInventory = 5_000;                                                                                   //частота добавления зелий
        if (timeElapsed / timeAddInventory > 0){
            count50HP += (int) (timeElapsed / timeAddInventory);
            startTime = System.currentTimeMillis();
        }
        if (count50HP / 3 >= 1){         //ДОДЕЛАТь ПОТОМ
            count100HP += 1;
            count50HP -= 2;
        }
    }

    private void buyThing(Hero hero, String vvod) {                                                                     //процесс покупки и изм.хар-к героя
        int price50HP = 10;
        int price100HP = 20;
        switch (vvod) {
            case "1":
                if (hero.getGold() >= price50HP) {
                    hero.setGold(hero.getGold() - price50HP);
                    hero.setBagage("Зелье 50хп", 1);        //кол-во зелий у героя - 1 (ДОДЕЛАТЬ)
                    System.out.println("Вы купили зелье здоровья на 50хп");
                    openBagTrader(hero);                                                                                      //возврат в предыдущее меню
                } else {
                    System.out.println("Вам нужно подкопить Золота на этот товар\n");
                    openBagTrader(hero);                                                                                      //возврат в предыдущее меню
                }
                break;
            case "2":
                if (hero.getGold() >= price100HP) {
                    hero.setGold(hero.getGold() - price100HP);
                    hero.setBagage("Зелье 100хп", 1);
                    System.out.println("Вы купили зелье здоровья на 100хп");
                    openBagTrader(hero);                                                                                      //возврат в предыдущее меню
                } else {
                    System.out.println("Вам нужно подкопить Золота на этот товар\n");
                    openBagTrader(hero);                                                                                      //возврат в предыдущее меню
                }
                break;
            default:
                System.out.println("Введите корректный пункт выбора");
        }
    }

    public void openBagTrader(Hero hero) {                                                                                    //вывод типа и количества наличия товаров//вывод типа и количества наличия товаров
        addProducts();
        System.out.println(STR."""
У торговца в наличии:
Зелий на 50хп: \{count50HP}
Зелий на 100хп: \{count100HP}

Купить:
1.Зелье на 50хп   2.Зелье на 100хп
Чтобы выйти введите - 0""");
        String vvod = new Scanner(System.in).next();
        if (vvod.equals("0")) {
            return;
        } else buyThing(hero, vvod);
    }

    @Override
    public void defaultParams() {
    }
}
