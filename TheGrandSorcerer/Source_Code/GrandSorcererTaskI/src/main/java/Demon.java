import static java.lang.Thread.sleep;

public class Demon implements Runnable {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private GrandSorcerersCircle grandSorcerersCircle = new GrandSorcerersCircle();
    Coven[] covens = grandSorcerersCircle.getCovens();

    int CovenIndex;
    private int Line;
    private void setLine(int line) {
       Line = line;
    }
    int getLine() {
        return Line;
    }
    private int Column;
    private void setColumn(int column) {
        Column = column;
    }
    int getColumn() {
        return Column;
    }
    private int DemonIndex;
    private int CreationRestrict = 0;
    private int WallHitsCounter = 0;
    private int SocialSkillsLevel = 0;
    boolean isRetired = false;

    private void setCreationRestrict(int creationRestrict) { //sets the number of turns for which a demon can't create ingredients
        CreationRestrict = creationRestrict;
    }

    private int getCreationRestrict() { //gets the number of turns for which a demon can't create ingredients
        return CreationRestrict;
    }

    void setWallHitsCounter(int wallHitsCounter) { //sets the number of wall hits for a demon
        WallHitsCounter = wallHitsCounter;
    }

    private int getWallHitsCounter() { //gets the number of wall hits for a demon
        return WallHitsCounter;
    }

    private void decreaseSocialSkillsLevel(int socialSkillsLevel) { //decreases the social skills level of a demon by a given value
        SocialSkillsLevel = SocialSkillsLevel - socialSkillsLevel;
        System.out.println("Demon's " + getDemonIndex() + " SOCIAL SKILLS LEVEL DECREASED BY " + socialSkillsLevel );
    }

    private void increaseSocialSkillsLevel(int socialSkillsLevel) { //increases the social skills level of a demon by a given value
        SocialSkillsLevel = SocialSkillsLevel + socialSkillsLevel;
        System.out.println("Demon's " + getDemonIndex() + " SOCIAL SKILLS LEVEL INCREASED BY " + socialSkillsLevel );
    }

    private int getSocialSkillsLevel() {
        return SocialSkillsLevel;
    }

    Demon(int demonIndex, int covenIndex) { //the demon registers to a coven using it's index
        CovenIndex = covenIndex;
        DemonIndex = demonIndex;
    }

    private int getDemonIndex() {
        return DemonIndex;
    }

    private void createIngredient() {
        int nrOfIngredients = getSocialSkillsLevel()/100; //the number of ingredients produced when a demon moves is given by its social skills level

        if(nrOfIngredients<1) //the demon must create at least 1 ingredient regardless its social skills level
            nrOfIngredients = 1;

        if(nrOfIngredients>10)
            nrOfIngredients = 10; //the maximum nr of ingredients a demon can create per move is 10

        System.out.println("\nDemon " + getDemonIndex() + " from coven " + CovenIndex + " creates " + nrOfIngredients + " ingredients:\n");

        for(int i = 1; i <= nrOfIngredients; i++) { //starts from 1 because i is the number of ingredients created by a demon (at least 1)
            int ingredientIndex = randomNumberGenerator.generateRandomNumber(0,9);
            String IngredientName = covens[CovenIndex].Ingredients.get(ingredientIndex);
            covens[CovenIndex].IncreaseIngredientCounter(ingredientIndex); //the coven knows that new ingredients are created
            System.out.println("Ingredient " + IngredientName + " was created by demon " + getDemonIndex() + " from coven " + CovenIndex);
        }
    }

    private void RandomSpawn(Demon demon, int CovenIndex) { // a demon random spawns in a coven
        int Line = randomNumberGenerator.generateRandomNumber(0,covens[CovenIndex].CovenSize); // generating a random line to spawn at
        demon.setLine(Line);
        int Column = randomNumberGenerator.generateRandomNumber(0,covens[CovenIndex].CovenSize); // generating a random column to spawn at
        demon.setColumn(Column);
        demon.covens[CovenIndex].covenMatrix.setLine(Line);
        demon.covens[CovenIndex].covenMatrix.setColumn(Column);
        demon.covens[CovenIndex].covenMatrix.setDemonIndex(demon.getDemonIndex());

        if(demon.covens[CovenIndex].CovenMatrix[Line][Column] == 0) { //checks is the position is free (if there is not already other demon)
            demon.covens[CovenIndex].CovenMatrix[Line][Column] = 1; //if it is free, this position becomes occupied
            System.out.println("Demon position " + Line + " " + Column + " demonIndex " + getDemonIndex() + " from coven " + CovenIndex + "\n");
        }
        else
            RandomSpawn(demon, CovenIndex); //if the demon hasn't spawned, it will try again until he succeeds

    }

    private boolean isSurrounded(int CovenIndex, int Line, int Column) {

        int surroundingDemonsCounter = 0;

        if(Line > 0) { //if the demon is not at the left wall
            if (covens[CovenIndex].CovenMatrix[Line - 1][Column] == 1) //if the left position is occupied by a demon, we count it
            surroundingDemonsCounter++;
        }
        else
        {
            surroundingDemonsCounter++; //if the left position can't be occupied by a demon, it is blocked, so the demon can't move there and we count it
        }

        if(Line < covens[CovenIndex].CovenSize-1) { //if the demon is not at the right wall
            if (covens[CovenIndex].CovenMatrix[Line + 1][Column] == 1) //if the right position is occupied by a demon, we count it
                surroundingDemonsCounter++;
        }
        else
        {
            surroundingDemonsCounter++; //if the right position can't be occupied by a demon, it is blocked, so the demon can't move there and we count it
        }

        if(Column > 0) { //if the demon is not at the upper wall
            if (covens[CovenIndex].CovenMatrix[Line][Column - 1] == 1) //if the upper position is occupied by a demon, we count it
                surroundingDemonsCounter++;
        }
        else
        {
            surroundingDemonsCounter++; //if the upper position can't be occupied by a demon, it is blocked, so the demon can't move there and we count it
        }

        if(Column < covens[CovenIndex].CovenSize-1) { //if the demon is not at the down wall
            if (covens[CovenIndex].CovenMatrix[Line][Column + 1] == 1) //if the down position is occupied by a demon, we count it
                surroundingDemonsCounter++;
        }
        else
        {
            surroundingDemonsCounter++; //if the down position can't be occupied by a demon, it is blocked, so the demon can't move there and we count it
        }

        return surroundingDemonsCounter == 4; //if 4 demons are surrounding the demon, return true, else false
    }


    private int RandomMove(Demon demon, int CovenIndex, int Line, int Column) {

        int direction = randomNumberGenerator.generateRandomNumber(0,3);
        int OldLine = Line;
        int OldColumn = Column;
        int hasMoved = 0;
        int otherDemonIndex;
        int demonIndex =  demon.covens[CovenIndex].covenMatrix.getDemonIndex(Line, Column);

        if (direction == 0 && Line > 0) //avoid walls
        {
            Line = Line - 1; //move up - the demon shall go to the upper line
        }

        if (direction == 1 && Column < covens[CovenIndex].CovenSize-1 ) //avoid walls
        {
            Column = Column + 1; //move down - the demon shall go to the next column
        }

        if (direction == 2 && Line < covens[CovenIndex].CovenSize-1 ) //avoid walls
        {
            Line = Line + 1; //move down - the demon shall go to the lower line
        }

        if (direction == 3 && Column > 0) //avoid walls
        {
            Column = Column - 1; //move left - the demon shall go to the previous column
        }

        if (covens[CovenIndex].CovenMatrix[Line][Column] == 0) { //if it is free

            hasMoved = 1;
            covens[CovenIndex].CovenMatrix[OldLine][OldColumn] = 0; //the last position becomes free
            covens[CovenIndex].CovenMatrix[Line][Column] = 1; //this position becomes occupied
            covens[CovenIndex].covenMatrix.setDemonIndex(-1); //a value that is never generated for a demon index, indicating there is no demon
            demon.setLine(Line);
            demon.setColumn(Column);

            covens[CovenIndex].covenMatrix.setDemonIndex(demon.getDemonIndex());
            covens[CovenIndex].covenMatrix.setLine(Line);
            covens[CovenIndex].covenMatrix.setColumn(Column);

            if (!CheckWallCollision(CovenIndex, Line, Column)) //if the demon does not collide a wall
            {
                if(getCreationRestrict() == 0) //and it is not restricted from producing ingredients
                    createIngredient(); //it creates an ingredient
            }
            else {
                System.out.print("Wall collision from demon " + getDemonIndex() + " from coven " + CovenIndex + "\n" );
                setCreationRestrict(2); //if the demon collides a wall, for the next 2 moves he can't create ingredients
            }

            if(getCreationRestrict() != 0) //if a demon is blocked from creating ingredients from the last move, this should decrease
                setCreationRestrict(getCreationRestrict()-1);
        }
        else //the demon hasn't moved, he met another demon
        {
            otherDemonIndex = demon.covens[CovenIndex].covenMatrix.getDemonIndex(Line, Column);
            if(otherDemonIndex!=demonIndex && otherDemonIndex>=0) {
                System.out.println("Demon " + demon.getDemonIndex() + " has met demon " + otherDemonIndex);
                increaseSocialSkillsLevel(10); //if the position where the demon wants to move is occupied, then its social skills level increases
                GrandSorcererHelper.getDemons()[otherDemonIndex].increaseSocialSkillsLevel(10); //if the position where the demon wants to move is occupied, then the other's demon social skills level increases
            }
        }

        return hasMoved;
    }

    private boolean CheckWallCollision(int CovenIndex, int Line, int Column) {
        if(Line == 0 || Column == 0 || Line == covens[CovenIndex].CovenSize-1 || Column == covens[CovenIndex].CovenSize-1) { // the margins of the matrix are the walls of the coven

            setWallHitsCounter(getWallHitsCounter() + 1); //the number of wall hits is increased for this demon

            if(getWallHitsCounter() % 10 == 0 && getWallHitsCounter()!=0)
                decreaseSocialSkillsLevel(100); //if a demon hits a wall 10 times, this demon will be demoted with 100 levels on their social skills

            return true; //if the demon collides the wall the function returns true
        }
        return false; //if the demon does not collide the wall the function returns false
    }

    private void dropIngredients(int CovenIndex, int IngredientIndex) {

        if(covens[CovenIndex].getIngredientsCount(IngredientIndex) > 0)
            System.out.println("Demons drop all ingredients of type " + covens[CovenIndex].getIngredient(IngredientIndex));
        covens[CovenIndex].DecreaseIngredientCounter(IngredientIndex, covens[CovenIndex].getIngredientsCount().get(IngredientIndex));//the demons drop all the existing ingredients
    }


    @Override
    public void run() {

        try {

            covens[CovenIndex].DemonSpawn.acquire(); //the coven is announced that a new demon will be spawned, so the demon spawn semaphore is acquired
            sleep(randomNumberGenerator.generateRandomNumber(500,1000));

            RandomSpawn(GrandSorcererHelper.demons[getDemonIndex()], CovenIndex); // the random spawn function is called

            covens[CovenIndex].DemonList.add(GrandSorcererHelper.demons[getDemonIndex()]); // the new spawned demon is added to the covens's demon list (he joins the coven)
            covens[CovenIndex].DemonSpawn.release(); // the semaphore for demon spawning is released

            while (!isRetired) {

                while(!covens[CovenIndex].StopDemons()) {
                    covens[CovenIndex].demonsCreating.acquire(); //the demons creating ingredients should not be interrupted by witches taking ingredients
                    if (isSurrounded(CovenIndex, covens[CovenIndex].covenMatrix.getLine(), covens[CovenIndex].covenMatrix.getColumn())) //if a demon is surrounded by other demons
                    sleep(randomNumberGenerator.generateRandomNumber(10,50)); //it sleeps a random time btw 10 & 50 milliseconds
                    if (RandomMove(GrandSorcererHelper.demons[getDemonIndex()],CovenIndex, covens[CovenIndex].covenMatrix.getLine(), covens[CovenIndex].covenMatrix.getColumn()) == 1) //if the demon has moved, it has created one or more ingredients
                        sleep(30); // so it sleeps for 30 milliseconds

                    covens[CovenIndex].demonsCreating.release(); //the demons finished making ingredients, so witches can take them
                }

                if(covens[CovenIndex].StopDemons()) // if the coven tells the demons to stop
                    covens[CovenIndex].DemonsStop.acquire(); // the semaphore for demons stop is acquired

                System.out.println("Coven message: Demons must sleep");

                for(int i=0; i<=9; i++)
                    dropIngredients(CovenIndex, i);
                System.out.println("Demons go to sleep");

                sleep(1000); // the demons sleep for 1 second
                covens[CovenIndex].DemonsStop.release(); // the semaphore for demons stop is released

            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
