public class  GrandSorcerersCircle  {

    private static int CovensNumber;
    private static int AllCovensMaxDemonsNumber = 0;
    private static Coven[] covensGlobal;
    private static int UndeadsNumber;

    private static GrandSorcererHelper GrandSorcererHelper;

    private static void setGrandSorcererHelper(GrandSorcererHelper grandSorcererHelper) {
        GrandSorcererHelper = grandSorcererHelper;
    }

    GrandSorcererHelper getGrandSorcererHelper() {
        return GrandSorcererHelper;
    }

    private static void setMaxDemonsNumber(int number) {
        AllCovensMaxDemonsNumber = AllCovensMaxDemonsNumber + number;
    }

    void setMaxDemonsNumber1(int number) {
        AllCovensMaxDemonsNumber = AllCovensMaxDemonsNumber + number;
    }

    int getMaxDemonsNumber(){
        return AllCovensMaxDemonsNumber;
    }

    private static void setCovensNumber(int number) {
        CovensNumber = number;
    }

    int getCovensNumber(){
        return CovensNumber;
    }

    private static void setCovens(Coven[] covens) {
        covensGlobal = covens;
    }

    Coven[] getCovens() {
       return covensGlobal;
    }

    private static void setUndeadsNumber(int number) {
        UndeadsNumber = number;
    }

    int getUndeadsNumber(){
        return UndeadsNumber;
    }


    private static RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();//this variable assures access to the RandomNumberGenerator class
    public static void main(String[] args) {

        CovensNumber = randomNumberGenerator.generateRandomNumber(3,20); //here the coven number is generated
        setCovensNumber(CovensNumber);
        System.out.println("\nThe number of covens is " + CovensNumber + ".\n"); //print the number of covens

        Coven[] covens = new Coven[CovensNumber];

        for(int i=0; i<CovensNumber; i++) { //creates the covens and the matrices of the covens
            covens[i] = new Coven();

            covens[i].CovenSize = randomNumberGenerator.generateRandomNumber(100,500); //here the size of coven i is generated
            System.out.println("The size of coven " + i + " is " + covens[i].CovenSize + ".\n"); //print the size of the coven i
            covens[i].MaxDemonsNumber = covens[i].CovenSize/2; //calculate the max number of demons for coven i
            covens[i].setMaxDemonsNumber(covens[i].MaxDemonsNumber);
            setMaxDemonsNumber(covens[i].MaxDemonsNumber);
            AllCovensMaxDemonsNumber = AllCovensMaxDemonsNumber + covens[i].MaxDemonsNumber; //calculates the total maximum number of demons from all the covens
            //System.out.println("Coven "+ i +" contains: \n");
            covens[i].CovenMatrix = new Integer[covens[i].CovenSize][covens[i].CovenSize];

            for(int j=0; j<covens[i].CovenSize; j++) {
                for(int k=0; k<covens[i].CovenSize; k++) {
                    covens[i].CovenMatrix[j][k] = 0; //every cell of the coven is initialised with 0 - indicates that is free
                    //System.out.print(covens[i].CovenMatrix[j][k] + " "); //print the cells
                }
                //System.out.println("\n");
            }
        }

        setCovens(covens); // passes the covens created here

        setMaxDemonsNumber(AllCovensMaxDemonsNumber); //saving the max demons number
        setUndeadsNumber(randomNumberGenerator.generateRandomNumber(20,50)); //saving the number of undeads

        GrandSorcererHelper grandSorcererHelper = new GrandSorcererHelper();
        Thread t = new Thread(grandSorcererHelper); //a new thread is created
        t.start();

        DemonRetirer demonRetirer = new DemonRetirer(); //a new demon retirer is created
        setGrandSorcererHelper(grandSorcererHelper);
        Thread p = new Thread(demonRetirer); // a thread p is created using it
        p.start(); //the p thread is started

    }
}
