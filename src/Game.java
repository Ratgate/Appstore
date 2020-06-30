import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Game {
    Scanner readStuff = new Scanner(System.in);
    public ArrayList<Player> players = new ArrayList<>();
    public LocalDate today = LocalDate.of(2020, 1, 1);
    public int dayOfTheWeek = 2;
    public Boolean endGame = false;
    public int projectSearchingProgress = 0;
    public Boolean projectSearchingPlayerContribution = false;
    public ArrayList<Project> available; // = new ArrayList<>(new Project());
    public ArrayList<Client> clients;

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
        while(!endGame){
            if(players.size() > 0){
                for (Player player : players) {
                    playTurn(player);
                }
            } else {
                endGame = true;
                System.out.println("Wszyscy gracze ponieśli klęskę");
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
        int innerDecision;
        while(true){
            makeRoom();
            System.out.println(dayOfTheWeek() + today);
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
                    System.out.println(player.active.toString());
                }
                case 2:{
                    System.out.println(available.toString());
                }
                case 3:{

                }
                case 4:{
                    System.out.println("Stan konta wynosi: " + player.cash + "\nW tym miesiącu zarobiłeś: " + player.cashThisMonth);
                }
                case 0:{
                    return;
                }
            }
        }
    }

    public void officeDoItsWork(){

    }

}
