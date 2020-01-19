import java.util.ArrayList;
import java.util.Arrays;

class MagicPortal { //this class helps witches give the grand sorcerer potions and viceversa

    ArrayList<Integer> potionsCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    void increasePotionsCount(int index, int amount) { //increases the potions count for a potion with a given amount
        int newCount = potionsCount.get(index) + amount; //the new number of potions is stored here
        potionsCount.set(index, newCount); //the value from the list is modified to the new value
    }

}
