import java.util.concurrent.ThreadLocalRandom;

public class Client {
    public Personality personality;

    public enum Personality{
        CHILL,
        DEMANDING,
        SONOFAB,
    }

    public Client() {
        this.personality = randomPersonality();
    }
    public Client(Personality personality) {
        this.personality = personality;
    }

    public Personality randomPersonality(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(randomNum < 8){
            return Personality.DEMANDING;
        } else if(randomNum < 10){
            return Personality.CHILL;
        } else {
            return Personality.SONOFAB;
        }
    }
}
