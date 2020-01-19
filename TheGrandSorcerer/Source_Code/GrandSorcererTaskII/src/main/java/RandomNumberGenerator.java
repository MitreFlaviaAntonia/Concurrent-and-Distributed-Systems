import java.util.Random;

class RandomNumberGenerator {

    int generateRandomNumber(int MarginLeft, int MarginRight) { //generates a random number between 0 and the given Number
        Random r = new Random();
        int randomNumber = r.nextInt(MarginRight);
        while (randomNumber<=MarginLeft) //this is a constraint that reduces the range for the coven number to [MarginLeft, MarginRight]
            randomNumber = r.nextInt(MarginRight);

        return randomNumber; //the random number is returned
    }
}
