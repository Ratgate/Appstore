import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Project {
    final static Double ADVANCE_RATIO = 0.4;
    final static Double DELAY_FEE_RATIO = 0.8;
    final static Integer WAITING_FOR_PAYMENT = 4;
    public Boolean acquiredBySeller;
    public Boolean playerHasContributed;
    public String name;
    public Client client;
    public Integer deadline = 0;
    public Integer paymentTime;
    public Double payment = 0.0;
    public Double delayFee;
    public Double advance = 0.0;
    public Integer[] workDays = new Integer[]{0, 0, 0, 0, 0, 0};
    public Complexity complexity;
    public Integer testingPoints = 0;

    public enum Complexity{
        LOW,
        MEDIUM,
        COMPLEX
    }

    @Override
    public String toString() {
        return "Project{" +
                "Nazwa: '" + name + '\'' +
                ", Złożoność:" + complexity +
                ", Deadline:" + deadline +
                ", Licznik testowy:" + testingPoints +
                ", Zaliczka:" + advance +
                ", Zapłata bez zaliczki:" + payment +
                ", Kara za spóźnienie:" + delayFee +
                "Pozostała praca: " +
                ", Mobilne:" + workDays[0] +
                ", Front-end:" + workDays[1] +
                ", Back-end:" + workDays[2] +
                ", Bazy danych:" + workDays[3] +
                ", Wordpress:" + workDays[4] +
              //  ", Prestashop:" + Arrays.toString(workDays) +
                ", Prestashop:" + workDays[5] +
                ", Znaleziona przez sprzedawcę:" + acquiredBySeller +
                ", Gracz programował lub testował:" + playerHasContributed +
                '}' + "\n";
    }

    public Project(Boolean acquiredBySeller, Client client) {
        Complexity complex = randomComplex();
        this.name = generateName();
        this.complexity = complex;
        this.playerHasContributed = false;
        this.acquiredBySeller = acquiredBySeller;
        this.client = client;
        this.workDays = generateWorkDays(complex);
        switch (complex){
            case COMPLEX:{
                this.payment += ThreadLocalRandom.current().nextDouble(20.0, 25.0 + 1.0);
                this.deadline += ThreadLocalRandom.current().nextInt(12, 15 + 1);
                this.advance = this.payment * ADVANCE_RATIO;
            }
            case MEDIUM:{
                this.payment += ThreadLocalRandom.current().nextDouble(20.0, 25.0 + 1.0);
                this.deadline += ThreadLocalRandom.current().nextInt(12, 15 + 1);
            }
            case LOW:{
                this.payment += ThreadLocalRandom.current().nextDouble(20.0, 25.0 + 1.0);
                this.deadline += ThreadLocalRandom.current().nextInt(12, 15 + 1);
            }
        }
        this.delayFee = payment * DELAY_FEE_RATIO;
        this.paymentTime = WAITING_FOR_PAYMENT;
    }

    public Complexity randomComplex(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(randomNum < 6){
            return Complexity.LOW;
        } else if(randomNum < 9){
            return Complexity.MEDIUM;
        } else {
            return Complexity.COMPLEX;
        }
    }

    public String generateName(){
        String[] first = {"Fascynujący", "Prowokacyjny", "Elegancki", "Wspierający", "Szybki", "Skomplikowany", "Wygenerowany", "Niestandardowy", "Pospolity"};
        String[] second = {" system", " kalendarz", " sklep", " przybornik", " aplikacja", " sieć neutronowa", " samouczący się program", " zadanie domowe"};
        String[] third = {" bankowy", " społeczny", " rządowy", " sektoru publicznego", " chirurgiczny", " przemysłowy", " wojskowy"};
        return first[ThreadLocalRandom.current().nextInt(0, first.length)] + second[ThreadLocalRandom.current().nextInt(0, second.length)] + third[ThreadLocalRandom.current().nextInt(0, third.length)];
    }

    public Integer[] generateWorkDays(Complexity complex){
        Integer[] generated = new Integer[]{0,0,0,0,0,0};
        switch(complex){
            case COMPLEX: {
                generated[choseAspect(generated)] = ThreadLocalRandom.current().nextInt(5, 8 + 1);
            }
            case MEDIUM: {
                generated[choseAspect(generated)] = ThreadLocalRandom.current().nextInt(5, 8 + 1);
            }
            case LOW: {
                generated[choseAspect(generated)] = ThreadLocalRandom.current().nextInt(5, 8 + 1);
            }
        }
        return generated;
    }

    public Integer choseAspect(Integer[] Chosen){
        while(true){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
            if(Chosen[randomNum] == 0){
                return randomNum;
            }
        }
    }
}
