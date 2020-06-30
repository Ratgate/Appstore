import java.util.concurrent.ThreadLocalRandom;

public class Project {
    public Boolean acquiredBySeller;
    public Boolean playerHasContributed;
    public String name;
    public Client client;
    public Integer deadline;
    public Integer paymentTime;
    public Double payment;
    public Double delayFee;
    public Double advance;
    public Integer frontendWorkDays = 0;
    public Integer backendWorkDays = 0;
    public Integer databaseWorkDays = 0;
    public Integer mobileWorkDays = 0;
    public Integer wordpressWorkDays = 0;
    public Integer prestashopWorkDays = 0;
    public Complexity complexity;

    public enum Complexity{
        LOW,
        MEDIUM,
        COMPLEX
    }

    @Override
    public String toString() {
        return "Project{" +
                "acquiredBySeller=" + acquiredBySeller +
                ", playerHasContributed=" + playerHasContributed +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", paymentTime=" + paymentTime +
                ", payment=" + payment +
                ", delayFee=" + delayFee +
                ", frontendWorkDays=" + frontendWorkDays +
                ", backendWorkDays=" + backendWorkDays +
                ", databaseWorkDays=" + databaseWorkDays +
                ", mobileWorkDays=" + mobileWorkDays +
                ", wordpressWorkDays=" + wordpressWorkDays +
                ", prestashopWorkDays=" + prestashopWorkDays +
                ", complexity=" + complexity +
                '}';
    }


    public Project(Boolean acquiredBySeller, Client client,      Integer deadline, Integer paymentTime, Double payment, Double delayFee, Integer frontendWorkDays, Integer backendWorkDays, Integer databaseWorkDays, Integer mobileWorkDays, Integer wordpressWorkDays, Integer prestashopWorkDays, Double advance) {
        this.name = generateName();
        this.complexity = randomComplex();
        this.playerHasContributed = false;
        this.acquiredBySeller = acquiredBySeller;
        this.client = client;



        this.deadline = deadline;
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.delayFee = delayFee;
        this.frontendWorkDays = frontendWorkDays;
        this.backendWorkDays = backendWorkDays;
        this.databaseWorkDays = databaseWorkDays;
        this.mobileWorkDays = mobileWorkDays;
        this.wordpressWorkDays = wordpressWorkDays;
        this.prestashopWorkDays = prestashopWorkDays;
        this.advance = advance;
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
}
