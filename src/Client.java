public class Client {
    public Personality personality;

    public enum Personality{
        CHILL,
        DEMANDING,
        SONOFAB,
    }

    public Client(Personality personality) {
        this.personality = personality;
    }
}
