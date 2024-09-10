import Units.GameUnit;
import Units.Hero;

import java.util.Random;
import java.util.Scanner;

public class DoBattle implements Battle {
    Random random = new Random();

    @Override
    public void battle(Hero hero, GameUnit unit) {
        if (0 == random.nextInt(2)) {                                               //с вероятн 1/2 первый удар делает герой или монстр
            while (hero.getHP() > 0 && unit.getHP() > 0) {                                //обмен ударов
                heroKick(hero, unit);
                if (unit.getHP() > 0) {
                    monsterKick(hero, unit);
                }
            }
        } else {
            while (hero.getHP() > 0 && unit.getHP() > 0) {                                //обмен ударов
                monsterKick(hero, unit);
                if (hero.getHP() > 0) {
                    heroKick(hero, unit);
                }
            }
        }

        if (hero.getHP() > 0) {                                                      //вывод данных о бое
            System.out.println("Герой победил!");
            int gold = random.nextInt(5) + 5;
            hero.setGold(hero.getGold() + gold);                //увеличение золота за победный бой
            System.out.print("Получено " + gold + " золота и ");
            lvlUp(hero);
            System.out.println("Уровень здоровья: " + hero.getHP());
        } else {
            System.out.println("Наш герой идёт побитый в ближайшую таверну...");
        }
    }

    private void heroKick(Hero hero, GameUnit unit) {                           //удар героя
        System.out.print("Удар " + hero.getName() + ": ");
        if (mobility(hero.getMobility())) {                                     //проверка попадания
            if (kritDamage(hero.getMobility())) {                               //проверка крит.урона
                int damage = hero.getPower() * (random.nextInt(2) + 2);   //крит х2/х3
                System.out.println("КРИТИЧЕСКИЙ - " + damage);
                unit.setHP(unit.getHP() - damage);
            } else {
                System.out.println(hero.getPower());
                unit.setHP(unit.getHP() - hero.getPower());
            }
        } else {
            System.out.println("*ПРОМАХ*");
        }
    }

    private void monsterKick(Hero hero, GameUnit unit) {                         //удар монстра
        unit.setMobility(unit.getMobility() + hero.getLevel());
        unit.setPower(unit.getPower() + hero.getLevel());

        System.out.print("Удар " + unit.getName() + ": ");
        if (mobility(unit.getMobility())) {                                     //проверка попадания
            System.out.println(unit.getPower() + " ");
            hero.setHP(hero.getHP() - unit.getPower());
        } else {
            System.out.println("*ПРОМАХ*");
        }
    }

    private boolean kritDamage(int mobility) {
        return mobility > random.nextInt(50);
    }      //то же что и ловкость

    private boolean mobility(int mobility) {            //Попадание зависит от уровня ЛОВКОСТИ (если больше рандомного числа)
        return mobility > random.nextInt(11);
    }

    private void lvlUp(Hero hero) {                                                                                     //получение опыта и уровней
        Scanner scanner = new Scanner(System.in);
        int exp = random.nextInt(15) + 10;
        hero.setExperience(hero.getExperience() + exp);
        System.out.println(exp + " опыта");
        if (hero.getExperience() >= 100) {
            System.out.println("Вы получили НОВЫЙ уровень");
            hero.setLevel(hero.getLevel() + 1);
            hero.setExperience(hero.getExperience() - 100);
            int count = 2;
            while (count != 0) {
                System.out.println("Очков апргрейда: " + count + "\nВыберите улучшение:\n1.Сила   2.Ловкость");
                switch (scanner.next()) {
                    case "1":
                        hero.setPower(hero.getPower() + 1);
                        System.out.println("Новое значение СИЛЫ - " + hero.getPower());
                        count -= 1;
                        break;
                    case "2":
                        hero.setMobility(hero.getMobility() + 1);
                        System.out.println("Новое значение ЛОВКОСТИ - " + hero.getMobility());
                        count -= 1;
                        break;
                    default:
                        System.out.println("Введите корректный пункт выбора");
                        break;
                }
            }
        }
    }
}
