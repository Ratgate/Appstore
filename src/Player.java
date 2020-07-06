import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Player{
    public static final Double STARTING_CASH = 20.0;
    public String name;
    public Double cash = STARTING_CASH;
    public Double cashThisMonth = 0.0;
    public ArrayList<Project> active = new ArrayList<>();
    public ArrayList<Programmer> programmers = new ArrayList<>();
    public ArrayList<Tester> testers = new ArrayList<>();
    public ArrayList<Seller> sellers = new ArrayList<>();
    public Integer majorCounter = 0;
    public Boolean usingSeller = false;
    public Integer bureaucracy = 2;
    public Boolean defeated = false;
    Integer[] skill = {0,1,1,1,1,1};

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

    public Integer[] sumOfTeamSkill(){
        Integer[] sumOfSkill = {0,0,0,0,0,0};
        for (Programmer programmer : programmers) {
            if(ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1.0)/100 > Employee.CHANCE_TO_NOT_COME_TO_WORK){
                for(int i = 0; i < 6; i++){
                    sumOfSkill[i] += programmer.skills[i];
                }
            } else {
                System.out.println(programmer.toString() + "nie przyszedł dzisiaj do pracy");
            }
        }
        System.out.println(Arrays.toString(sumOfSkill));
        return sumOfSkill;
    }

    public Integer powerOfTesters(){
        Integer testersInWork = 0;
        for (Tester tester : testers) {
            if(ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1.0)/100 > Employee.CHANCE_TO_NOT_COME_TO_WORK){
                testersInWork++;
            } else {
                System.out.println(tester.toString() + "nie przyszedł dzisiaj do pracy");
            }
        }
        System.out.println("Pracuje dzisiaj " + testersInWork + " testerów");
        return testersInWork * Tester.TEST_POWER;
    }

    public Integer numberOfSellers(){
        Integer sellersInWork = 0;
        for (Seller seller : sellers) {
            if(ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1.0)/100 > Employee.CHANCE_TO_NOT_COME_TO_WORK){
                sellersInWork++;
            } else {
                System.out.println(seller.toString() + "nie przyszedł dzisiaj do pracy");
            }
        }
        System.out.println("pracuje dzisiaj " + sellersInWork + " sprzedawców");
        return sellersInWork;
    }
}
