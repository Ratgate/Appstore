
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
    public Integer frontendWorkDays;
    public Integer backendWorkDays;
    public Integer databaseWorkDays;
    public Integer mobileWorkDays;
    public Integer wordpressWorkDays;
    public Integer prestashopWorkDays;
    public Complexity complexity;

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

    public enum Complexity{
        LOW,
        MEDIUM,
        COMPLEX
    }
    public Project(Boolean acquiredBySeller, String name, Client client, Integer deadline, Integer paymentTime, Double payment, Double delayFee,Double advance, Integer frontendWorkDays, Integer backendWorkDays, Integer databaseWorkDays, Integer mobileWorkDays, Integer wordpressWorkDays, Integer prestashopWorkDays, Complexity complexity) {
        this.acquiredBySeller = acquiredBySeller;
        this.name = name;
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
        this.complexity = complexity;
        this.advance = advance;
    }
}
