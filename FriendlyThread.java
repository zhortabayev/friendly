import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FriendlyThread extends Thread{

	int number;
	int till;
	public static Map<Double, ArrayList<Integer>> ratiosAndNumbers = Collections.synchronizedMap(new HashMap<Double, ArrayList<Integer>>());
	
	public FriendlyThread(int number, int till) {
		this.number = number;
		this.till = till;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = number; i < till; i++) {
			int sum = getDivisorsSum(i);				
			double temp = (double) sum/(double) i;
			if(ratiosAndNumbers.get(temp) == null) {
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(i);
				ratiosAndNumbers.put(temp, al);
			} else ratiosAndNumbers.get(temp).add(i);												
		}
	}

	private int getDivisorsSum(int n) {	
		int sum = 0;
		int theNumber = n;
		sum += n;
		
		if(isEven(n)){			
			sum += n/2;
			n = n / 2;			
		}
		
		for(int i = n - 1; i > 0; i--) {
			if(theNumber % i == 0){
				sum += i;
			}
		}
		return sum;
	}
	
	private boolean isEven(int n) {
		if (n % 2 == 0)
			return true;
		return false;
	}
}
