import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Scanner readStuff = new Scanner(System.in);
    public ArrayList<Player> players = new ArrayList<>();
    public LocalDate today = LocalDate.of(2020, 1, 1);
    public int dayOfTheWeek = 2;
    public Boolean endGame = false;
    public int projectSearchingProgress = 0;
    public Boolean projectSearchingPlayerContribution = false;
    public ArrayList<Project> available;
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
            for (Player player : players) {
                    playTurn(player);
                }
            advanceDay();
            }
        }


    public void playTurn(Player player){
        int decision;
        while (true){
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
                    return;
                }
                case 2:{
                    return;
                }
                case 3:{
                    return;
                }
                case 4:{
                    return;
                }
                case 5:{
                    return;
                }
                case 6:{
                    return;
                }
                case 7:{
                    return;
                }
                case 8:{
                    return;
                }
                case 9:{
                    return;
                }
                case 0:{
                    lookTroughStuff(player);
                }
                default:{
                    System.out.println("Nie przewidziana odpowiedź");
                }
            }
        }
    //    player.isDefeated()
    //    player.isVictorious()
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

}
