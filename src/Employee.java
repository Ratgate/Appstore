import java.util.concurrent.ThreadLocalRandom;

abstract public class Employee {
    public static final Double CHANCE_TO_NOT_COME_TO_WORK = 0.05;
    public String name;
    public Double buyInPrice;
    public Double maintenancePrice;

    public String[] potentialNames = {"Ulrick", "Alojzy", "Chester", "Karen", "Gordon", "Rick", "Ashley", "Amadeusz", "Johenstain", "Barrack", "Tim", "Decker", "Coleslav", "Wiesław", "Mieczysław", "Rabarbarsław", "Krzesław", "Paul"};

    public Employee() {
        this.name = potentialNames[ThreadLocalRandom.current().nextInt(0, potentialNames.length - 1)];
        this.buyInPrice = 1.0;
        this.maintenancePrice = 0.5;
    }
}
