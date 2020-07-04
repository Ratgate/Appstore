public class Tester extends Employee {
    @Override
    public String toString() {
        return "Tester{" +
                "Imię: '" + name + '\'' +
                ", Koszt zatrudnienia: " + buyInPrice +
                ", Koszt utrzymania: " + maintenancePrice +
                '}';
    }
}
