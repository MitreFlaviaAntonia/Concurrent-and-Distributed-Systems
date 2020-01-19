class DivisionTerms {

    private MainThread mainThread = new MainThread();

    long CalculateQuotient(long Number, long ThreadsNr) {
        return Number/ThreadsNr;
    }
    long CalculateRemainder(long Number, long ThreadsNr) {
        return Number%ThreadsNr;
    }

    long CheckPrime(long Number) {

        if(Number == 1)
            return 0;

        if(Number == 2)
            return Number;

        for(long iterator = 2 ; iterator <= Number/2 ; iterator++) {
            if(Number%iterator == 0) {
                return 0;
            }
        }

        return Number;
    }

    long IntervalLeftMargin(long Position) {
        long ILeft;

        if(Position == 1)
            return 1;
        else
        if(Position == 2)
            ILeft = mainThread.getQuotient() + 2;
        else
        if(Position == 3)
            ILeft = (Position-1) * mainThread.getQuotient() + 3;
        else
            ILeft = (Position-1) * mainThread.getQuotient() + mainThread.getRemainder() + 1;

        return ILeft;
    }

    long IntervalRightMargin(long Position) {
        long IRight;

        if(Position == 1)
            IRight = mainThread.getQuotient() + 1;
        else
        if(Position == 2)
            IRight = 2 * mainThread.getQuotient() + 2;
        else
            IRight = Position * mainThread.getQuotient() + mainThread.getRemainder();

        return IRight;
    }

}