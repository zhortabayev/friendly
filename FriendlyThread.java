import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FriendlyThread extends Thread{

	//public static volatile int trueCounter = 0;
	//public static volatile Map<Integer, Double> numbersAndRatios = new HashMap<Integer, Double>();
	int number;
	int till;
	public static volatile int instanceCounter;

	public static Map<Integer, Double> numbersAndRatios = Collections.synchronizedMap(new HashMap<Integer, Double>());
	public FriendlyThread(int number, int till) {
		instanceCounter++;
		this.number = number;
		this.till = till;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = number; i < till; i++) {
			int sum = getDivisorsSum(i);	
			numbersAndRatios.put(i, (double) sum/(double) i);		
		}
				//trueCounter++;
	}
	
	protected void finalize() {
        instanceCounter--;
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
