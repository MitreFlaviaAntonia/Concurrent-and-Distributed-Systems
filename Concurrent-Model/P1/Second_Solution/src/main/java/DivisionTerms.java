class DivisionTerms {
    private MainThread mainThread = new MainThread();

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

    long IntervalRightMargin(long Position) {

        long IRight = Position;

        while(IRight < mainThread.getNumber()) {

            IRight = IRight + (mainThread.getThreadsNr()+1);
        }

        if(IRight > mainThread.getNumber())
            IRight = IRight - (mainThread.getThreadsNr()+1);

        return IRight;
    }

}