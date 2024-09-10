import Units.GameUnit;
import Units.Hero;
import Units.Monsters.Goblin;
import Units.Monsters.Skeleton;
import Units.Trader;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trader trader = new Trader();

        System.out.print("Добро пожаловать в мир Проб и Ошибок. Введите имя Вашего георя: ");
        String name = scanner.nextLine();
        Hero hero = new Hero(name);
        System.out.println(STR."Привет: \{name}! Ты просыпаешься в лесу и слышишь шелест кустов. Едва выглянув ты видишь гоблина! Ты бросаешься в бой с этим бандитом!");

        boolean inGame = true;
        while (inGame) {
            Thread thread = Battle(hero, unit());
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }

            mainMenu();

            boolean g = true;
            while (g) {
                switch (scanner.next()) {
                    case "1":
                        trader.openBagTrader(hero);
                        mainMenu();
                        break;
                    case "2":
                        System.out.println("Ищем неприятности!");
                        g = false;
                        break;
                    case "3":
                        if (hero.getBagage().isEmpty()) {
                            System.out.println("Рюкзак пуст");
                        } else hero.getStringBagage();
                        mainMenu();
                        break;
                    case "4":
                        System.out.println("Показатели героя: ");
                        hero.getParams();
                        hero.getExp();
                        hero.getStringGold();
                        mainMenu();
                        break;
                    case "5":
                        hero.getStringBagage();
                        if (!hero.getBagage().isEmpty()){
                            System.out.println("Введите сколько хп вы хотите пополнить: 50 или 100");
                            hero.drinkHP();
                        }
                        mainMenu();
                        break;
                    case "6":
                        System.out.println("Выход из игры...");
                        inGame = false;
                        g = false;
                    default:
                        System.out.println("Введите корректный пункт выбора");
                        mainMenu();
                }
            }
        }

    }

    private static Thread Battle(Hero hero, GameUnit unit) {
        DoBattle battle = new DoBattle();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                battle.battle(hero, unit);
                unit.defaultParams();                                                                                   //возвращаем монстрам полное хп
            }
        });
        thread.start();
        return thread;
    }

    private static GameUnit unit(){
        if ((new Random()).nextInt(2) == 0){
            return new Goblin();
        } else return new Skeleton();
    }

    private static void mainMenu() {
        System.out.println("""
                
                Выберите вариант действий:
                1.Пойти к Торговцу          2.Искать приключения!
                3.Открыть рюкзак            4.Показатели героя
                5.Восстановить здоровье     6.Выход из игры""");
    }
}