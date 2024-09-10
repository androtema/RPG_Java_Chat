package Units.Monsters;

import Units.GameUnit;

public class Goblin extends GameUnit {

    public Goblin() {
        super(10, 5, 5, "Goblin");
    }

    public void defaultParams(){
        super.HP = 10;
        super.power = 1;
        super.mobility = 1;
    }
}
