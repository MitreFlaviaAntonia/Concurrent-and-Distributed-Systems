import java.util.Random;

class RandomGenerator{

    int generateRandomNr() {
        Random rand = new Random();
        return rand.nextInt(100);
    }

} 