import java.util.Random;

//correctly implements a Slippery Slot Machine
public class BetterSlot {

	public static void main(String[]args){
		
		BetterSlot s = new BetterSlot();
		int[] values = new int[] {5,1,1,1,5};
		int[] values2 = new int[] {25,42,7,7,7};
		System.out.println("Total payout 1: " + s.payOff(values));
		System.out.println("Total payout 2: " + s.payOff(values2));
	}//end main()


public int[] pullTheLever()
{
	Random rand = new Random();
	int[] values = new int[5];
	for(int i = 0; i < 5; i++){
		values[i] = rand.nextInt(50) + 1;
	}
	return values;
}//end pullTheLever()

public int payOff(int[] values)
{
	int total = 0;
	boolean win = false;
	
	if((total += fiveSame(values)) > 0) {win = true;}
	else if((total += fourSame(values)) > 0 && !win) {win = true;}
	else if((total += threeSame(values)) > 0 && !win) {win = true;}
	else if(!win) {total += twoSame(values);}
	total += existsPerfectSquare(values);
	total += existsPowTwo(values);
	total += existsFortyTwo(values);
	
	
	
	
	return total;
}//end payOff()

private int fiveSame(int[] values) {
	int count = 0;
	int earnings = 0;
	
	for(int i = 0; i < values.length; i++) {
		if(values[0] == values[i]) {count++;} //if compared values are same, iterate count
	}//for i
	
	if(count == 5) {earnings += 1000000;}
	return earnings;
}//end allFiveSame()

private int fourSame(int[] values) {
	int count = 0;
	boolean win = false;
	int earnings = 0;
	
	for(int i = 0; i < values.length; i++) {
		if(count == 4) {win = true;}
		
		count = 0;
		if(!win) {
			for(int k = 0; k < values.length; k++) { //compares values[i] value to all values
				if(values[i] == values[k]) {count++;} //if compared values are same, iterate count
			}//for k
		}//if(!win)
	}//for i
	if(win) {earnings += 10000;}
	
	return earnings;
}//end allFourSame()
	
private int threeSame(int[] values) {
	int count = 0;
	boolean win = false;
	int earnings = 0;
	int[] markers = new int[2];
	int mark = 0;
	int x = 0;
	
	for(int i = 0; i < values.length; i++) {
		if(count == 3) {
			win = true; 
			mark = values[i];
		}//if(count == 3)
		
		count = 0;
		if(!win) {
			for(int k = 0; k < values.length; k++) { //compares values[i] value to all values
				if(values[i] == values[k]) {
					count++; //if compared values are same, iterate count
				} //if(values[i]...)
			} //for k
		} //if(!win)
	} //for i
	
	if(win) {
		for(int i = 0; i < values.length; i++) {
			if(values[i] != mark) {markers[x] = values[i]; x=1;}
		}
		if(markers[0] == markers[1]) {earnings += 5000;}
		else {earnings += 100;}
	}//if(win)
	
	return earnings;
}

private int twoSame(int[] values) {
	int count = 0;
	boolean win = false;
	int earnings = 0;
	
	for(int i = 0; i < values.length; i++) {
		if(count == 2) {win = true;}
		
		count = 0;
		if(!win) {
			for(int k = 0; k < values.length; k++) { //compares values[i] value to all values
				if(values[i] == values[k]) {count++;} //if compared values are same, iterate count
			}//for k
		}//if(!win)
	}//for i
	if(win) {earnings += 10;}
	
	return earnings;
} //end twoSame()

private int existsPerfectSquare(int[] values) {
	
	double x; 
	boolean win = false;
	int earnings = 0;
	for(int i = 0; i< values.length; i++) {
		x  = Math.sqrt(values[i]);
		if(x - Math.floor(x) == 0) {win = true;}
	}
	if(win) {earnings += 7;}
	
	return earnings;
}

private int existsFortyTwo(int[] values) {
	int earnings = 0;
	boolean win = false;
	
	for(int i = 0; i< values.length; i++) {
		if(values[i] == 42) {win = true;}
	}
	
	if(win) {earnings += 2;}
	
	return earnings;
}

private int existsPowTwo(int[] values) {
	int earnings = 0;
	boolean win = false;
	
	for(int i = 0; i < values.length; i++) {
		if(isPowTwo(values[i])) {win = true;}
	}
	
	if(win) {earnings += 3;}
	
	return earnings;
}

private boolean isPowTwo(int value) {
boolean powTwo = false;

if(value > 0) {
	while(value % 2 == 0) {
		value /= 2;
	}
	if(value == 1) {powTwo = true;}
}
if(value == 0 || value != 1) {powTwo = false;}

return powTwo;
} //end isPowTwo()

} //end class BetterSlot
