bool wantp = false;
bool wantq = false;
byte turn = 1;

active proctype P() {
    
    do
    :: wantp = true;
       	do
       	:: wantq ->
          if
          :: (turn == 2) ->
              wantp = false;
			  turn == 1;
			  wantp = true;
          :: else
          fi
    	:: else -> break
    	od;
    	turn = 2;
    	wantp = false
	od
}

active proctype X() {
    
    do
    :: wantq = true;
       do
       :: wantp ->
          if
          :: (turn == 1) ->
              wantq = false;
			  turn == 2;
			  wantq = true;
          :: else
          fi
    	:: else -> break
    	od;
    	turn = 1;
    	wantq = false
	od
}
