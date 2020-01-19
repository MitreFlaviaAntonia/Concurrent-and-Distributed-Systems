import java.util.Random;

class RandomGenerator {

    int getRandomNr() {
        Random rand = new Random();
        return rand.nextInt(100);
    }

} 