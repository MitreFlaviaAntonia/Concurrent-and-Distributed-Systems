import java.util.ArrayList;
import java.util.Arrays;

class GrandSorcerer{ //this class represents the grand sorcerer that gets potions

    private ArrayList<Integer> PotionsCount = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));

    void readPotions(ArrayList<Integer> potionsCount) {
        System.out.println("The Grand Sorcerer reads potions");
        PotionsCount = potionsCount;
        for(int i=0; i<20; i++) {
            if(potionsCount.get(i)!=0)
                ;//System.out.println("The Grand Sorcerer reads potion: " + potionsCount.get(i));
        }
    }

    boolean givePotions(int requestedAmount) {
        int counter = 0;
        int requestedAmountLeft = requestedAmount;
            int i = 0;
            while(i<20 && requestedAmountLeft!=0) {

                if(PotionsCount.get(i)!=0) {

                    if(PotionsCount.get(i) >= requestedAmount) {
                        PotionsCount.set(i, PotionsCount.get(i)-requestedAmount);
                        return true;
                    }
                    else
                    {
                            counter = counter + PotionsCount.get(i);
                            requestedAmountLeft = requestedAmountLeft - PotionsCount.get(i);
                            PotionsCount.set(i, 0);
                    }
                }
                i++;
            }

        return counter == requestedAmount;

    }



}
