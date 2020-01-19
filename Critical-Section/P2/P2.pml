byte	n = 0;

active proctype P() {
	byte 	i = 1;
	byte	temp;
	do :: ( i > 10 ) -> break  
	   :: else ->
		temp = n;
		n = temp + 1;
		i++
	od;
}

active proctype X() {
	byte 	i = 1;
	byte	temp;
	do :: ( i > 10 ) -> break  
	   :: else ->
		temp = n;
		n = temp + 1;
		i++
	od;
}

