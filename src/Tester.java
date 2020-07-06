public class Tester extends Employee {
    public static final Integer TEST_POWER = 3;
    @Override
    public String toString() {
        return "Tester{" +
                "Imię: '" + name + '\'' +
                ", Koszt zatrudnienia: " + buyInPrice +
                ", Koszt utrzymania: " + maintenancePrice +
                '}';
    }
}
