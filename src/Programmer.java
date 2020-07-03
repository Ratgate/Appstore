import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Programmer extends Employee{
    // 0 - mobile, 1 - front-end, 2 - back-end, 3 - database, 4 - wordpress, 5 - prestashop
    public Integer[] skills;

    public Programmer() {
        this.skills = distributeSkills();
        this.buyInPrice = sumOfSkill(this.skills) * 0.5;
        this.maintenancePrice = sumOfSkill(this.skills) * 0.75;
    }

    public Integer[] distributeSkills(){
        Integer[] skillSheet = {0,0,0,0,0,0};
        for(int i = 0; i < skillSheet.length; i++){
            skillSheet[i] = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        }
        return skillSheet;
    }

    public Integer sumOfSkill(Integer[] table){
        Integer sumOfSkill = 0;
        for (Integer skill : table) {
            sumOfSkill+=skill;
        }
        return sumOfSkill;
    }

    @Override
    public String toString() {
        return "Programista {" +
                ", Imię:'" + name + '\'' +
                "skills=" + Arrays.toString(skills) +
                ", buyInPrice=" + buyInPrice +
                ", maintenancePrice=" + maintenancePrice +
                '}';
    }
}
