import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    Scanner readStuff = new Scanner(System.in);
    public ArrayList<Player> players = new ArrayList<>();
    public LocalDate today = LocalDate.of(2020, 1, 1);
    public int dayOfTheWeek = 2;
    public Boolean endGame = false;
    public int projectSearchingProgress = 0;
    public Boolean projectSearchingPlayerContribution = false;
    public ArrayList<Project> available = new ArrayList<>();
    public ArrayList<Client> clients = new ArrayList<>();
    public ArrayList<Employee>[] candidates = new ArrayList[3];


    public void makeRoom(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public Boolean isWeekend(){
        return dayOfTheWeek > 4;
    }

    public String dayOfTheWeek(){
        switch (dayOfTheWeek){
            case 0: return "Poniedziałek";
            case 1: return "Wtorek";
            case 2: return "Środa";
            case 3: return "Czwartek";
            case 4: return "Piątek";
            case 5: return "Sobota";
            case 6: return "Niedziela";
        }
        return "Error";
    }

    public void advanceDay(){
        dayOfTheWeek = (dayOfTheWeek + 1)%7;
        today = today.plusDays(1);
    }

    public void playGame(){
        prepareGame();
        while(!endGame){
            if(players.size() > 0){
                for (Player player : players) {
                    playTurn(player);
                }
            } else {
                endGame = true;
                System.out.println("Wszyscy gracze ponieśli klęskę\n\n");
                break;
            }
            players.removeIf(x -> x.defeated);
            advanceDay();
            }
        }


    public void playTurn(Player player){
        int decision = -1;
        while (decision <= 0){
            makeRoom();
            System.out.println(dayOfTheWeek() + " " + today);
            System.out.println("Tura gracza " + player.name + "\n");
            System.out.println("Zzużyj ten dzień, by ");
            System.out.println("1 - Podpisać umowę na realizację dostępnego projektu");
            System.out.println("2 - Poszukać nowych projektów - postęp: " + projectSearchingProgress + "/5");
            System.out.println("3 - Programować");
            System.out.println("4 - Testować");
            System.out.println("5 - Oddać projekt klientowi");
            System.out.println("6 - Rekrutować nowego pracownika");
            System.out.println("7 - Zwolnić pracownika");
            System.out.println("8 - Użerać się z biurokracją - musisz walczyć jeszcze " + player.bureaucracy + " dni w tym miesiącu");
            System.out.println("9 - Nie pracuj dzisiaj");
            System.out.println("Nie tracąc dnia przejrzyj obecne sprawy - 0");
            System.out.println("Twój wybór: ");
            decision = readStuff.nextInt();

            switch (decision){
                case 1:{
                    break;
                }
                case 2:{
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    break;
                }
                case 7:{
                    break;
                }
                case 8:{
                    player.bureaucracy--;
                    break;
                }
                case 9:{
                    break;
                }
                case 0:{
                    lookTroughStuff(player);
                    break;
                }
                default:{
                    System.out.println("Nie przewidziana odpowiedź");
                }
            }
        }
        if(!isWeekend()){
            officeDoItsWork();
        }

        if(today.getMonth() != today.plusDays(1).getMonth()){
            player.salesTax();
        }

        if(player.isVictorious()){
            System.out.println(player.name + " Wygrał grę");
            endGame = true;
        }
        if(today.getMonth() != today.plusDays(1).getMonth()){
            if(player.bureaucracy > 0){
                player.defeated = true;
            } else {
                player.bureaucracy = 2;
            }
        }
        if(player.isDefeated()){
            player.defeated = true;
        }
    }

    public void lookTroughStuff(Player player){
        int innerDecision = -1;

        makeRoom();
        while(true){
            System.out.println(dayOfTheWeek() + " " + today);
            System.out.println("Tura gracza " + player.name + "\n");
            System.out.println("1 - sprawdź stan realizacji poszczególnych projektów");
            System.out.println("2 - przejrzyj dostępne projekty");
            System.out.println("3 - przejrzyj dostępnych pracowników");
            System.out.println("4 - sprawdź stan konta");
            System.out.println("0 - wróć do menu tury");
            System.out.println("Twoja decyzja: ");
            innerDecision = readStuff.nextInt();

            switch(innerDecision){
                case 1:{
                    try{
                        System.out.println(player.active.toString() + "\n\n");
                    } catch (NullPointerException e) {
                        System.out.println("Brak aktywnych projektów\n\n");
                    }
                    break;
                }
                case 2:{
                    try{
                        System.out.println(available.toString() + "\n\n");
                    } catch (NullPointerException e) {
                        System.out.println("Brak dostępnych projektów\n\n");
                    }
                    break;
                }
                case 3:{
                    try{
                        System.out.println("Programiści: \n" + candidates[0].toString());
                    } catch (NullPointerException e) {
                        System.out.println("Brak potencjalnych programistów\n\n");
                    }
                    try{
                        System.out.println("\n Testerzy: \n " + candidates[1].toString());
                    } catch (NullPointerException e) {
                        System.out.println("Brak potencjalnych testerów\n\n");
                    }
                    try{
                        System.out.println("\n Sprzedawcy: \n " + candidates[2].toString() + "\n\n");
                    } catch (NullPointerException e) {
                        System.out.println("Brak potencjalnych sprzedawców\n\n");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Stan konta wynosi: " + player.cash + "\nW tym miesiącu zarobiłeś: " + player.cashThisMonth + "\n\n");
                    break;
                }
                case 0:{
                    return;
                }
            }
        }
    }

    public void prepareGame(){
        clients.add(new Client(Client.Personality.DEMANDING));
        clients.add(new Client(Client.Personality.CHILL));
        clients.add(chooseClient());

        available.add(new Project(false, chooseClient()));
        available.add(new Project(false, chooseClient()));
        available.add(new Project(false, chooseClient()));
    }

    public Client chooseClient(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(randomNum < 8){
            return clients.get(new Random().nextInt(clients.size()));
        } else {
            clients.add(new Client());
            return clients.get(clients.size()-1);
        }
    }

    public void officeDoItsWork(){

    }

}
