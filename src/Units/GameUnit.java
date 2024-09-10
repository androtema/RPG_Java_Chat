package Units;

public abstract class GameUnit {
    protected int HP;
    protected int power;
    protected int mobility;
    protected String name;

    public GameUnit(int HP, int power, int mobility, String name) {
        this.HP = HP;
        this.power = power;
        this.mobility = mobility;
        this.name = name;
    }

    public abstract void defaultParams();
    //-------------------
    //_____сетты_____
    //-------------------
    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public void setName(String name) {
        this.name = name;
    }

    //-------------------
    //_____гетты_____
    //-------------------

    public int getHP() {
        return this.HP;
    }

    public int getPower() {
        return this.power;
    }

    public int getMobility() {
        return this.mobility;
    }

    public String getName() {
        return this.name;
    }


    public void getParams() {
        System.out.printf("HP: %d, power: %d, mobility: %d, name: %s%n", this.HP, this.power, this.mobility, this.name);
    }
}
