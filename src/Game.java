import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    final static Integer[] PLAYER_SKILLS = {0,1,1,1,1,1};
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
        Boolean playerProgram = false;
        Boolean playerTest = false;
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
                    playerProgram = true;
                    break;
                }
                case 4:{
                    playerTest = true;
                    break;
                }
                case 5:{
                    if(!finalizeProject(player)){
                        decision = -1;
                    }
                    break;
                }
                case 6:{
                    if(!recruitEmployee(player)){
                        decision = -1;
                    }
                    break;
                }
                case 7:{
                    if(!fireEmployee(player)){
                        decision = -1;
                    }
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

        officeDoItsWork(player, playerProgram, playerTest);

        advanceActiveProjects(player);
        advanceFinalizingProjects(player);
        player.finalizing.removeIf(x -> x.finalized);


        if(!isWeekend()){
            officeReceivesPayment(player);
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

    public void officeDoItsWork(Player player, Boolean helpedPrograming, Boolean helpedTesting){
        doPrograming(player, helpedPrograming);
        doTesting(player, helpedTesting);
        doSelling(player);
    }

    public Boolean fireEmployee(Player player){
        Integer counter = 1;
        Integer decision = -1;
        System.out.println("Twoi programiści");
        for (Programmer programmer : player.programmers) {
            System.out.println(counter + " - " + programmer);
            counter++;
        }
        System.out.println("Twoi testerzy");
        for (Tester tester: player.testers) {
            System.out.println(counter + " - " + tester);
            counter++;
        }
        System.out.println("Twoi sprzedawcy");
        for (Seller seller : player.sellers) {
            System.out.println(counter + " - " + seller);
            counter++;
        }
        System.out.println("Wybierz 0 by zmienić zdanie lub numer pracownika by go zwolnić");

        while(decision !=0){
            decision = readStuff.nextInt();
            if(decision == 0){
                break;
            }
                if(decision - 1 < player.programmers.size()){
                    Programmer chosen = player.programmers.get(decision - 1);
                    this.programmers.add(chosen);
                    player.programmers.remove(chosen);
                    System.out.println("Zwolniony pracownik jest dostępny do ponownej rekrutacji");
                        return true;
                } else if(decision - 1 < player.programmers.size() + player.testers.size()){
                    Tester chosen = player.testers.get(decision - 1 - player.programmers.size());
                    this.testers.add(chosen);
                    player.testers.remove(chosen);
                    System.out.println("Pracownik został zwolniony");
                    return true;
                } else {
                    Seller chosen = player.sellers.get(decision - 1 - player.programmers.size() - player.testers.size());
                    this.sellers.add(chosen);
                    player.sellers.remove(chosen);
                    System.out.println("Pracownik został zwolniony");
                    return true;
                }
        }
        return false;
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
            Project chosen = available.get(decision - 1);
            player.active.add(chosen);
            player.cash += chosen.advance;
            available.remove(chosen);
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
                if(decision - 3 <= programmers.size()){
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
                } else if(decision - 3 <= programmers.size() + testers.size()){
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

    public void doPrograming(Player player, Boolean helped){
        Integer workDaysLeft = 0;
        if(((isWeekend() | player.programmers.size()==0) & !helped) | player.active.size() == 0){
            System.out.println("Albo gracz nie ma zleceń nad którymi może pracować, albo gracz nie programował i albo jest weekend albo nie ma programistów");
            return;
        }

        Integer[] programmingPower = {0,0,0,0,0,0};
        int decision;

        if(!isWeekend()){
            sumProgrammingPower(programmingPower, player.sumOfTeamSkill());
        }

        for(int i = 0; i < player.active.size(); i++){
            System.out.println(i + 1 + " - " + player.active.get(i));
        }

        if(player.programmers.size() == 0){
            System.out.println("Nie zatrudniłeś programistów więc programujesz sam. Skomplikowane projekty Cię przerastają. Nad którym projektem pracujesz?");
        } else if(isWeekend()) {
            System.out.println("Jest weekend, programujesz sam. Skomplikowane projekty są za skomplikowane dla Ciebie samego. Nad którym projektem pracujesz?:");
        } else {
            System.out.println("Proszę wpisać numer projektu, który dzisiaj wraz z ekipą programujecie:");
        }

        decision = readStuff.nextInt();
        Project chosen = player.active.get(decision - 1);

        if(helped & (chosen.complexity != Project.Complexity.COMPLEX | (!isWeekend() & player.programmers.size() > 0))){
            chosen.playerHasContributed = true;
            sumProgrammingPower(programmingPower, PLAYER_SKILLS);
        }

        for(int i = 0; i < chosen.workDays.length; i++){
            if(chosen.workDays[i] >= programmingPower[i]){
                chosen.testingPoints += programmingPower[i];
                chosen.workDays[i] -= programmingPower[i];
            } else {
                chosen.testingPoints += chosen.workDays[i];
                chosen.workDays[i] = 0;
            }
            workDaysLeft += chosen.workDays[i];
        }

        if(workDaysLeft == 0){
            System.out.println("Wszelkie programowanie w projekcie skończone. Projekt gotowy do oddania teraz lub po ewentualnych testach");
            chosen.readyToGiveAway = true;
        }
    }

    public void doTesting(Player player, Boolean helped){
        if(((isWeekend() | player.testers.size()==0) & !helped) | player.active.size() == 0){
            System.out.println("Albo gracz nie ma zleceń nad którymi może pracować, albo gracz nie testował i albo jest weekend albo nie ma testerów");
            return;
        }

        Integer testingPower = 0;
        int decision;

        if(!isWeekend()){
            testingPower += player.powerOfTesters();
        }



        for(int i = 0; i < player.active.size(); i++){
            System.out.println(i + 1 + " - " + player.active.get(i));
        }
        if(player.testers.size() == 0){
            System.out.println("Nie masz biura, testujesz sam. Który projekt testujesz");
        } else if(isWeekend()) {
            System.out.println("Jest weekend, testujesz sam sam. Który projekt testujesz?:");
        } else {
            System.out.println("Proszę wpisać numer projektu, który dzisiaj wraz testujecie z ekipą:");
        }

        decision = readStuff.nextInt();
        Project chosen = player.active.get(decision - 1);

        if(helped){
            chosen.playerHasContributed = true;
            testingPower += player.TEST_POWER;
        }

        if(chosen.testingPoints >= testingPower){
            chosen.testingPoints -= testingPower;
        } else{
            chosen.testingPoints = 0;
        }
    }

    public Integer[] sumProgrammingPower(Integer[] initial, Integer[] additive){
        for(int i = 0; i < initial.length; i++){
            initial[i] += additive[i];
        }
        return initial;
    }

    public void officeReceivesPayment(Player player){
        Double dailyPay = 0.0;

        for (Programmer programmer : player.programmers) {
            dailyPay += programmer.maintenancePrice;
        }

        for (Tester tester : player.testers) {
            dailyPay += tester.maintenancePrice;
        }

        for (Seller seller : player.sellers) {
            dailyPay += seller.maintenancePrice;
        }

        player.cash -= dailyPay;
    }

    public void doSelling(Player player){
        if(!isWeekend()){
            searchForProject(player.numberOfSellers());
        }
    }

    public void advanceFinalizingProjects(Player player){
        for (Project project : player.finalizing) {
            if(project.paymentTime > 0){
                project.paymentTime--;
            } else {
                if(ThreadLocalRandom.current().nextDouble(1.0, 100.0 + 1.0) < project.testingPoints * project.PROJECTS_CHANCE_TO_FAIL_PER_TESTING_POINT){
                    System.out.println("Klient ma zażalenia co do oddanego projektu, który miał pewne problemy...");
                    switch (project.client.personality){
                        case DEMANDING: {
                            if(ThreadLocalRandom.current().nextInt(1, 100 + 1) <= 50){
                                System.out.println("i wycofał się z umowy. Zapłata przepadła. Zaliczka, jeżeli była, zostaje");
                                project.finalized = true;
                            } else {
                                System.out.println("Ale nie wycofał się z umowy i zapłaci za projekt");
                            }
                            break;
                        }
                        case CHILL: {
                            System.out.println("Ale nie wycofał się z umowy i zapłaci za projekt");
                            break;
                        }
                        case SONOFAB: {
                            System.out.println("i wycofał się z umowy. Zapłata przepadła. Zaliczka, jeżeli była, zostaje");
                            project.finalized = true;
                            break;
                        }
                    }
                } else {
                    if(!project.finalized){
                        if(doClientDelaysPayment(project)){
                            project.testingPoints = 0;
                            switch (project.client.personality){
                                case CHILL: {
                                    if(ThreadLocalRandom.current().nextInt(1, 100 + 1) <= 30){
                                        System.out.println("Ponieważ powody, klient musi opóźnić zapłatę za projekt o " + project.LIGHTLY_PAYMENT_DELAY + " dni");
                                        project.paymentTime += project.LIGHTLY_PAYMENT_DELAY;
                                        project.delayedPayment = true;
                                        return;
                                    }
                                    break;
                                }
                                case SONOFAB: {
                                    int diceRoll = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                                    if(diceRoll <= 1){
                                        System.out.println("Wszelki kontakt z klientem sięurwał. Projekt można uznać za stracony");
                                        project.finalized = true;
                                        return;
                                    } else if(diceRoll <= 6){
                                        System.out.println("Z poważnych powodów, klient opóźnia zapłatę za projekt o " + project.SEVERE_PAYMENT_DELAY + " dni i mówi, że dobrze że w ogóle dostajesz za to zapłatę");
                                        project.paymentTime += project.SEVERE_PAYMENT_DELAY;
                                        project.delayedPayment = true;
                                        return;
                                    } else if(diceRoll <= 36) {
                                        System.out.println("Ponieważ powody, klient musi opóźnić zapłatę za projekt o " + project.LIGHTLY_PAYMENT_DELAY + " dni");
                                        project.paymentTime += project.LIGHTLY_PAYMENT_DELAY;
                                        project.delayedPayment = true;
                                        return;
                                    }
                                    break;
                                }
                            }
                        }
                        endingProject(player, project);
                    }
                }
            }
        }

    }

    public void advanceActiveProjects(Player player){
        for (Project project : player.active) {
                project.deadline--;
        }
    }

    public Boolean finalizeProject(Player player){
        int decision;
        int readyCounter = 0;
        for(int i = 0; i < player.active.size(); i++){
            if(player.active.get(i).readyToGiveAway){
                System.out.println(i + 1 + " " + player.active.get(i));
                readyCounter++;
            }
        }
        if(readyCounter == 0){
            System.out.println("Nie ma żadnego projektu gotowego do oddania");
            return false;
        } else {
            System.out.println("Proszę wpisać numer kontraktu, który chcesz oddać lub 0, jeżeli nie tracąc cury chcesz wrócić do menu tury :");
            decision = readStuff.nextInt();
            if (decision != 0) {
                Project chosen = player.active.get(decision - 1);
                deliverProjectToClient(chosen);
                player.finalizing.add(chosen);
                player.active.remove(chosen);
                return true;
            }
        }
        return false;
    }

    public Boolean doClientDelaysPayment(Project project) {
        if (!project.delayedPayment) {
            project.delayedPayment = true;
            switch (project.client.personality) {
                case DEMANDING: {
                    return false;
                }
                case CHILL: {
                    return ThreadLocalRandom.current().nextInt(1, 10 + 1) <= 3;
                }
                case SONOFAB: {
                    return ThreadLocalRandom.current().nextInt(1, 100 + 1) <= 36;
                }
            }
        }
        return false;
    }

    public void deliverProjectToClient(Project project){
        if(project.deadline < 0){
            if(project.client.personality == Client.Personality.CHILL & ThreadLocalRandom.current().nextInt(1, 5 + 1) == 1 & project.deadline <= Client.CHILLED_PATIENCE){
                    System.out.println("Klient nie przejmuje się spóźnieniem. Otrzymujesz pełną zapłatę");
            } else {
                System.out.println("W ramach kary za spóźnienie " + (-1) * project.deadline + " dni, reszta zapłaty " + project.payment + " została zmniejszona o " + delayFee(project));
                   project.payment -= delayFee(project);
                   project.receivedFee = true;
            }
        } else {
            System.out.println("Projekt dostarczony na czas");
        }

    }
    
    public Double delayFee(Project project){
        return (project.payment - project.advance) * (project.deadline / (project.deadline - 1));
    }

    public void endingProject(Player player, Project project){
        project.finalized = true;
        player.cash += project.payment - project.advance;
        if(project.complexity == Project.Complexity.COMPLEX & !project.playerHasContributed){
            player.majorCounter++;
            if(project.acquiredBySeller){
                player.usingSeller = true;
            }
        }
    }
}

