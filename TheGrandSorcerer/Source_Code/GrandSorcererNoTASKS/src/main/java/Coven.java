import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

class Coven {

    private static int UndeadsNumber;
    Semaphore DemonsStop = new Semaphore(1);
    Semaphore DemonSpawn = new Semaphore(1);
    Semaphore maxWitches = new Semaphore(10); //maximum 10 witches can read ingredients at a time
    Semaphore demonsCreating = new Semaphore(100);

    ArrayList<Demon> DemonList = new ArrayList<>(); // contains demons, which are threads

    static void setUndeadsNumber(int number) {
        UndeadsNumber = number;
    }

    int getUndeadsNumber(){
        return UndeadsNumber;
    }

    private int ActualDemonsNumber;

    int CovenSize;
    int MaxDemonsNumber;

    public static class CovenMatrix { //this class was created to fulfill the need of knowing line and column values
        int Line;
        int Column;
        int DemonIndex = -1;

        void setDemonIndex(int demonIndex) { DemonIndex = demonIndex; }

        void setLine(int line) {
            Line = line;
        }

        void setColumn(int column) {
            Column = column;
        }

        int getDemonIndex(int line, int column) {
            if(Line == line && Column == column)
                return DemonIndex;
            return -2;
        }

        int getLine() {
            return Line;
        }

        int getColumn() {
            return Column;
        }

    }

    CovenMatrix covenMatrix = new CovenMatrix();

    Integer[][] CovenMatrix;

    ArrayList<String> Ingredients = new ArrayList<>(Arrays.asList("FrogBrain", "AloeVera", "Dittany", "Mushroom", "OctopusPowder", "Firefly",
            "Poison", "Slime", "DragonLiver", "Ginger"));
    ArrayList<Integer> IngredientsCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    void setDemonsNumber(int Number) {
        ActualDemonsNumber = Number;
    }

    int getDemonsNumber() {
        return ActualDemonsNumber;
    }

    void setMaxDemonsNumber(int number) {
        MaxDemonsNumber = number;
    }

    String getIngredient(int Index) { //returns the ingredient at a given index from the ingredients list
        return Ingredients.get(Index);
    }

    int getIngredientsCount(int Index) { return IngredientsCount.get(Index); }

    ArrayList<Integer> getIngredientsCount() {
        return IngredientsCount;
    }

    void IncreaseIngredientCounter(int ingredientIndex) { //increases the nr of ingredients of a given kind
        int IncreasedNumber = IngredientsCount.get(ingredientIndex)+1; //calculates the new number of ingredients
        IngredientsCount.set(ingredientIndex, IncreasedNumber); //replaces the old number of ingredients of this type(from the given index)
        //with the new number of ingredients
    }

    void DecreaseIngredientCounter(int ingredientIndex, int DecreasingAmount) { //decreases the nr of ingredients of a given kind
        int IncreasedNumber = 0;

        if(IngredientsCount.get(ingredientIndex) >= DecreasingAmount) //if this condition is false, the nr of ingredients becomes 0 - dropping all the ingredients
            IncreasedNumber = IngredientsCount.get(ingredientIndex) - DecreasingAmount; //calculates the new number of ingredients
        IngredientsCount.set(ingredientIndex, IncreasedNumber); //replaces the old number of ingredients of this type(from the given index)
        //with the new number of ingredients
    }

    void loseIngredients(double percent, int CovenIndex) {
        int amountLost; //used to calculate the amount of lost potions
        int newAmount; //the new amount of existing potions
        for (int i = 0; i < 10; i++)
        {
            amountLost = (int) (getIngredientsCount(i)*percent); //calculating the amount lost as the
            // multiplication between the number of potions and the percent of lost potions
            newAmount = getIngredientsCount(i) - amountLost; //the new amount is calculated by decreasing the old amount of potions with the amount lost
            IngredientsCount.set(i, newAmount); //storing the new amount of potions
        }
        System.out.println("Coven " + CovenIndex + " lost " + percent*100 + "% of the ingredients" );
    }

    boolean StopDemons() { //returns true when the current time can be divided by 10 seconds
        return System.nanoTime() % 1000 % 1000 % 1000 % 10  == 0;

    }
}
