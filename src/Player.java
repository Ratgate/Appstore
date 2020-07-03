import java.util.ArrayList;

public class Player {
    public static final Double STARTING_CASH = 20.0;
    public String name;
    public Double cash = STARTING_CASH;
    public Double cashThisMonth = 0.0;
    public ArrayList<Project> active = new ArrayList<>();
    public ArrayList<Employee>[] Office = new ArrayList[3];
    public Integer majorCounter = 0;
    public Boolean usingSeller = false;
    public Integer bureaucracy = 2;
    public Boolean defeated = false;

    public Player(String name) {
        this.name = name;
    }

    public Boolean isVictorious(){
        return majorCounter >= 3 & usingSeller & cash > STARTING_CASH;
    }

    public Boolean isDefeated(){
        return cash <= 0 | defeated;
    }

    public void salesTax(){
        cash -= cashThisMonth/10;
        cashThisMonth = 0.0;
    }
}
