import java.util.ArrayList;
import java.util.Arrays;

class Potion {

    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();

    ArrayList<String> Potions = new ArrayList<>( //initialising the list of potions
            Arrays.asList("Ageing Potion", "Love Potion", "Death Potion", "Dogbreath Potion", "Doxycide", "Noxious Potion", "Screaming Snakes Hair Potion",
                    "Sleeping Draught", "Laxative Potion", "Forgetfulness Potion", "Fake Protective Potion", "Dizziness Draught", "Draught of Living Death",
                    "Drink of Despair", "Garrotting Gas", "Muffling Draught", "Polyjuice Potion", "Snuffling Potion", "Weakness Potion",
                    "Venomous Tentacula Juice"));

    private ArrayList<String> AgeingPotionIngredientList = new ArrayList<>(Arrays.asList("FrogBrain", "AloeVera", "Dittany", "Mushroom"));
    private ArrayList<Integer> AgeingPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private ArrayList<String> LovePotionIngredientList = new ArrayList<>(Arrays.asList("FrogBrain", "AloeVera", "Dittany", "Mushroom", "OctopusPowder", "Firefly"));
    private ArrayList<Integer> LovePotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 1));

    private ArrayList<String> DeathPotionIngredientList = new ArrayList<>(Arrays.asList("Poison", "Slime", "DragonLiver", "Ginger"));
    private ArrayList<Integer> DeathPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private ArrayList<String> DogbreathPotionIngredientList = new ArrayList<>(Arrays.asList("FrogBrain", "AloeVera", "Dittany", "Mushroom",
            "OctopusPowder", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> DogbreathPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 1, 1, 1, 1, 1, 1));

    private ArrayList<String> DoxycideIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "OctopusPowder", "Firefly", "Poison"));
    private ArrayList<Integer> DoxycideIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private ArrayList<String> NoxiousPotionIngredientList = new ArrayList<>(Arrays.asList("Firefly", "Poison", "Slime", "DragonLiver"));
    private ArrayList<Integer> NoxiousPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private ArrayList<String> ScreamingSnakesHairPotionIngredientList = new ArrayList<>(Arrays.asList("AloeVera", "Dittany", "Mushroom", "OctopusPowder", "Firefly"));
    private ArrayList<Integer> ScreamingSnakesHairPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1));

    private ArrayList<String> SleepingDraughtIngredientList = new ArrayList<>(Arrays.asList("Dittany", "Mushroom", "OctopusPowder", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> SleepingDraughtIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 1, 1));

    private ArrayList<String> LaxativePotionIngredientList = new ArrayList<>(Arrays.asList("Dittany", "Mushroom", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> LaxativePotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(4, 2, 3, 1, 1));

    private ArrayList<String> ForgetfulnessPotionIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> ForgetfulnessPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 2, 1, 1));

    private ArrayList<String> FakeProtectivePotionIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "OctopusPowder", "Poison", "Slime"));
    private ArrayList<Integer> FakeProtectivePotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 1, 2, 1));

    private ArrayList<String> DizzinessDraughtIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "Poison", "Slime", "Dittany"));
    private ArrayList<Integer> DizzinessDraughtIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(1, 1, 1, 1));

    private ArrayList<String> DraughtofLivingDeathIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "OctopusPowder", "Firefly", "Poison"));
    private ArrayList<Integer> DraughtofLivingDeathIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 3, 4));

    private ArrayList<String> DrinkofDespairIngredientList = new ArrayList<>(Arrays.asList("Firefly", "Poison", "Slime", "DragonLiver"));
    private ArrayList<Integer> DrinkofDespairIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 3, 4));

    private ArrayList<String> GarrottingGasIngredientList = new ArrayList<>(Arrays.asList("AloeVera", "Dittany", "Mushroom", "OctopusPowder", "Firefly"));
    private ArrayList<Integer> GarrottingGasIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 1));

    private ArrayList<String> MufflingDraughtIngredientList = new ArrayList<>(Arrays.asList("Dittany", "Mushroom", "OctopusPowder", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> MufflingDraughtIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 3, 4, 1, 1));

    private ArrayList<String> PolyjuicePotionIngredientList = new ArrayList<>(Arrays.asList("Dittany", "Mushroom", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> PolyjuicePotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 3, 1, 1));

    private ArrayList<String> SnufflingPotionIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "Firefly", "Poison", "Slime"));
    private ArrayList<Integer> SnufflingPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 2, 1, 1));

    private ArrayList<String> WeaknessPotionIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "OctopusPowder", "Poison", "Slime"));
    private ArrayList<Integer> WeaknessPotionIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 1, 2, 1));

    private ArrayList<String> VenomousTentaculaJuiceIngredientList = new ArrayList<>(Arrays.asList("Mushroom", "Poison", "Slime", "Dittany"));
    private ArrayList<Integer> VenomousTentaculaJuiceIngredientsNecessaryAmount = new ArrayList<>(Arrays.asList(3, 1, 1, 1));

    ArrayList<ArrayList<String>> IngredientsLists = new ArrayList<>(Arrays.asList(AgeingPotionIngredientList, LovePotionIngredientList,
            DeathPotionIngredientList, DogbreathPotionIngredientList, DoxycideIngredientList, NoxiousPotionIngredientList,
            ScreamingSnakesHairPotionIngredientList, SleepingDraughtIngredientList, LaxativePotionIngredientList, ForgetfulnessPotionIngredientList,
            FakeProtectivePotionIngredientList, DizzinessDraughtIngredientList, DraughtofLivingDeathIngredientList, DrinkofDespairIngredientList,
            GarrottingGasIngredientList, MufflingDraughtIngredientList, PolyjuicePotionIngredientList,
            SnufflingPotionIngredientList, WeaknessPotionIngredientList, VenomousTentaculaJuiceIngredientList));

    ArrayList<ArrayList<Integer>> IngredientsNecessaryAmountLists = new ArrayList<>(Arrays.asList(AgeingPotionIngredientsNecessaryAmount, LovePotionIngredientsNecessaryAmount,
            DeathPotionIngredientsNecessaryAmount, DogbreathPotionIngredientsNecessaryAmount, DoxycideIngredientsNecessaryAmount, NoxiousPotionIngredientsNecessaryAmount,
            ScreamingSnakesHairPotionIngredientsNecessaryAmount, SleepingDraughtIngredientsNecessaryAmount, LaxativePotionIngredientsNecessaryAmount, ForgetfulnessPotionIngredientsNecessaryAmount,
            FakeProtectivePotionIngredientsNecessaryAmount, DizzinessDraughtIngredientsNecessaryAmount, DraughtofLivingDeathIngredientsNecessaryAmount, DrinkofDespairIngredientsNecessaryAmount,
            GarrottingGasIngredientsNecessaryAmount, MufflingDraughtIngredientsNecessaryAmount, PolyjuicePotionIngredientsNecessaryAmount,
            SnufflingPotionIngredientsNecessaryAmount, WeaknessPotionIngredientsNecessaryAmount, VenomousTentaculaJuiceIngredientsNecessaryAmount));

    boolean createPotion(ArrayList<String> IngredientList, ArrayList<Integer> IngredientsNecessaryAmount, int CovenIndex) {

        ArrayList<Integer> ingredientIndexes = new ArrayList<>();

        boolean enoughIngredients = false;
        int enoughCount = 0;

        for(int i = 0; i < IngredientList.size(); i++) {
            ingredientIndexes.add(grandSorcerersCircle.getCovens()[CovenIndex].Ingredients.indexOf(IngredientList.get(i)));
            if(grandSorcerersCircle.getCovens()[CovenIndex].IngredientsCount.get(ingredientIndexes.get(i)) >= IngredientsNecessaryAmount.get(i))
                enoughCount++;
        }

        if (enoughCount == IngredientList.size())
            enoughIngredients = true;

        return enoughIngredients; //returns true if there are enough ingredients
    }













































}
