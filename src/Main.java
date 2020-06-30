import java.util.Scanner;


public class Main {
    private static Scanner readDecision = new Scanner(System.in);
    private static int decision;

    public static void main(String[] args){
        while(true){
            intro();
            switch (decision){
                case 1:{
                    setup(1);
                } break;
                case 2:{
                    System.out.println("Podaj proszę liczbę graczy");
                    int playersNumber = readDecision.nextInt();
                          if(playersNumber > 0){
                              setup(playersNumber);
                       } else {
                           System.out.println("Podano niepoprawną liczbęgraczy");
                        }
                } break;
                case 0:{
                    System.out.println("Dziękuję za grę");
                    return;
                }
            }
        }
    }

    static void intro(){
        System.out.println("Zagrajmy w grę");
        System.out.println("1 - rozpocznij nową grę jednoosobową");
        System.out.println("2 - rozpocznij nową grę wieloosobową");
        System.out.println("0 - wyjdź z gry");
        decision = readDecision.nextInt();
    }

    static void setup(int numberOfPlayers){
        Game play = new Game();
        boolean success = false;
        String name;
        while(!success){
                    //this line clears buffer;
                    readDecision.nextLine();
                for(int i = 1; i <= numberOfPlayers;){
                    boolean nameTaken = false;
                    System.out.println("Podaj proszę imię gracza " + i);
                    name = readDecision.nextLine();
                    for (Player gamer : play.players) {
                        if(gamer.name.equals(name)){
                            nameTaken = true;
                            System.out.println("Imię zajęte");
                        }
                    }
                    if(!nameTaken){
                        play.players.add(new Player(name));
                        i++;
                    }
                }
                System.out.println(play.players);
                success = true;
        }
        play.playGame();
    }
}
