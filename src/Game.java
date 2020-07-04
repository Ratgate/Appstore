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
    public ArrayList<Programmer> programmers = new ArrayList<>();
    public ArrayList<Tester> testers = new ArrayList<>();
    public ArrayList<Seller> sellers = new ArrayList<>();


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
                    if(!acceptNewContract(player)){
                        decision = -1;
                    }
                    break;
                }
                case 2:{
                    projectSearchingPlayerContribution = true;
                    searchForProject(1);
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
                    if(!recruitEmployee(player)){
                        decision = -1;
                    }
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
            employeesAreQuiting(player);
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
                        System.out.println("Programiści: \n" + programmers.toString());
                    } catch (NullPointerException e) {
                        System.out.println("Brak potencjalnych programistów\n\n");
                    }
                    try{
                        System.out.println("\n Testerzy: \n " + testers.toString());
                    } catch (NullPointerException e) {
                        System.out.println("Brak potencjalnych testerów\n\n");
                    }
                    try{
                        System.out.println("\n Sprzedawcy: \n " + sellers.toString() + "\n\n");
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

        programmers.add(new Programmer());
        programmers.add(new Programmer());
        programmers.add(new Programmer());
        testers.add(new Tester());
        testers.add(new Tester());
        sellers.add(new Seller());
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

    public void searchForProject(Integer times){
        for(int i = 1; i <= times; i++){
            projectSearchingProgress++;
            if(projectSearchingProgress >= 5){
                if(!projectSearchingPlayerContribution){
                    available.add(new Project(true, chooseClient()));
                } else {
                    available.add(new Project(false, chooseClient()));
                }
                projectSearchingProgress = 0;
                projectSearchingPlayerContribution = false;
            }
        }
    }

    public Boolean acceptNewContract(Player player){
        int decision;
        for(int i = 0; i < available.size(); i++){
            System.out.println(i + 1 + " " + available.get(i));
        }
        System.out.println("Proszę wpisać numer kontraktu, który podpisujesz lub 0, jeżeli nie chcesz podpisywać kontraktu (Nie tracisz tury) :");
        decision = readStuff.nextInt();
        if (decision != 0) {
            player.active.add(available.get(decision - 1));
            player.cash += available.get(decision - 1).advance;
            available.remove(decision - 1);
            return true;
        }
        return false;
    }

    public void employeesAreQuiting(Player player){
        try{
            programmers.addAll(player.programmers);
        } catch (NullPointerException ignored){

        }
        try{
            testers.addAll(player.testers);
        } catch (NullPointerException ignored){

        }
        try{
            sellers.addAll(player.sellers);
        } catch (NullPointerException ignored){

        }
    }

    public Boolean recruitEmployee(Player player){
        int decision = -1;
        int counter = 3;
        System.out.println("0 - zrezygnuj z rekrutacji");
        System.out.println("1 - nowy programista");
        System.out.println("2 - nowy tester");
        System.out.println("3 - nowy sprzedawca");
                System.out.println("Dostępni Programiści: ");
        for(int i = 0; i < programmers.size(); i++){
            System.out.println(counter + 1 + " - " + programmers.get(i));
            counter++;
        }
        System.out.println("Dostępni Testerzy: ");
        for(int i = 0; i < testers.size(); i++){
            System.out.println(counter + 1 + " " + testers.get(i));
            counter++;
        }
        System.out.println("Dostępni Sprzedawcy: ");
        for(int i = 0; i < sellers.size(); i++){
            System.out.println(counter + 1 + " " + sellers.get(i));
            counter++;
        }
        System.out.println("Proszę wpisać numer nowego pracownika [1-3] - koszt (3.0) (pokrycie kosztów nowego pracownika pozwala na decyzję o jego zatrudnieniu), numer istniejącego pracownika [4-" + counter + "], którego chcesz zatrudnić lub 0, jeżeli nie chcesz podpisywać kontraktu (Nie tracisz tury) :");

        while(decision !=0){
            decision = readStuff.nextInt();

            if(decision <=3){
                if(decision == 0){
                    return false;
                }
                player.cash -= 3.0;
                switch (decision){
                    case 1:{
                        Programmer chosen = new Programmer();
                        System.out.println("Nowy pracownik: " + chosen.toString());
                        if(player.cash >= chosen.buyInPrice){
                            System.out.println("Czy chcesz zatrudnić tego pracownika? \n 1 - tak \n 2 - nie");
                            if(readStuff.nextInt() > 1){
                                System.out.println("Pracownik zatrudniony");
                                player.cash -= chosen.buyInPrice;
                                player.programmers.add(chosen);
                            } else {
                                System.out.println("pracownik trafia na listę potencjalnych pracowników");
                                programmers.add(chosen);
                            }
                        } else {
                            System.out.println("Niestety nie masz dość pieniędzy, by go zatrudnić");
                            programmers.add(chosen);
                        }
                        break;
                    }
                    case 2:{
                        Tester chosen = new Tester();
                        System.out.println("Nowy pracownik: " + chosen.toString());
                        if(player.cash >= chosen.buyInPrice){
                            System.out.println("Czy chcesz zatrudnić tego pracownika? \n 1 - tak \n 2 - nie");
                            if(readStuff.nextInt() > 0){
                                System.out.println("Pracownik zatrudniony");
                                player.cash -= chosen.buyInPrice;
                                player.testers.add(chosen);
                            } else {
                                System.out.println("pracownik trafia na listę potencjalnych pracowników");
                                testers.add(chosen);
                            }
                        } else {
                            System.out.println("Niestety nie masz dość pieniędzy, by go zatrudnić");
                            testers.add(chosen);
                        }
                        break;
                    }
                    case 3:{
                        Seller chosen = new Seller();
                        System.out.println("Nowy pracownik: " + chosen.toString());
                        if(player.cash >= chosen.buyInPrice){
                            System.out.println("Czy chcesz zatrudnić tego pracownika? \n 1 - tak \n 2 - nie");
                            if(readStuff.nextInt() > 0){
                                System.out.println("Pracownik zatrudniony");
                                player.cash -= chosen.buyInPrice;
                                player.sellers.add(chosen);
                            } else {
                                System.out.println("pracownik trafia na listę potencjalnych pracowników");
                                sellers.add(chosen);
                            }
                        } else {
                            System.out.println("Niestety nie masz dość pieniędzy, by go zatrudnić");
                            sellers.add(chosen);
                        }
                        break;
                    }
                }
                return true;
            } else {
                if(decision - 4 <= programmers.size()){
                    Programmer chosen = programmers.get(decision - 4);
                    if(player.cash >= chosen.buyInPrice){
                        System.out.println("Pracownik zatrudniony");
                        player.cash -= chosen.buyInPrice;
                        player.programmers.add(chosen);
                        programmers.remove(chosen);
                        return true;
                    } else {
                        System.out.println("Niestety, nie masz dość pieniędzy by zatrudnić" + chosen.toString());
                        decision = -1;
                    }
                } else if(decision - 4 <= programmers.size() + testers.size()){
                    Tester chosen = testers.
                            get(decision - 4 - programmers.size());
                    if(player.cash >= chosen.buyInPrice){
                        System.out.println("Pracownik zatrudniony");
                        player.cash -= chosen.buyInPrice;
                        player.testers.add(chosen);
                        testers.remove(chosen);
                        return true;
                    } else {
                        System.out.println("Niestety, nie masz dość pieniędzy by zatrudnić" + chosen.toString());
                        decision = -1;
                    }
                } else {
                    Seller chosen = sellers.get(decision - 4 - programmers.size() - testers.size());
                    if(player.cash >= chosen.buyInPrice){
                        System.out.println("Pracownik zatrudniony");
                        player.cash -= chosen.buyInPrice;
                        player.sellers.add(chosen);
                        sellers.remove(chosen);
                        return true;
                    } else {
                        System.out.println("Niestety, nie masz dość pieniędzy by zatrudnić" + chosen.toString());
                        decision = -1;
                    }
                }
            }
        }
        return false;
    }
}
