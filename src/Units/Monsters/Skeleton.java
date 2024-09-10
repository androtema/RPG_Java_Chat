package Units.Monsters;

import Units.GameUnit;

public class Skeleton extends GameUnit {

    public Skeleton() {
        super(20, 5, 5, "Skeleton");
    }

    public void defaultParams(){
        super.HP = 20;
        super.power = 1;
        super.mobility = 1;
    }
}
