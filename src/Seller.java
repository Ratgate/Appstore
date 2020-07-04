public class Seller extends Employee {
    @Override
    public String toString() {
        return "Sprzedawca{" +
                "Imię: '" + name + '\'' +
                ", Koszt zatrudnienia: " + buyInPrice +
                ", Koszt utrzymania: " + maintenancePrice +
                '}';
    }
}
