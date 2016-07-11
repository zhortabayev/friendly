import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FriendlyNumbers {
	
	public static void main(String [] args) {

		double startTime = System.currentTimeMillis();

		if(args.length != 1) {
			System.out.println("Please specify input file "); 
			return;
		}
		
		String input = args[0];	
				
		int [] numbers = new int [12];
		int counter = 0;		

		int start, end;
				
		try {
			FileReader fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			
			String str = "";
			
			while((str = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str, " ");
				
				if(st.hasMoreTokens()) {
					numbers[counter] = Integer.valueOf(st.nextToken());
					counter++;
					numbers[counter] = Integer.valueOf(st.nextToken());
					counter++;					
				}
			}			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		for(int outer = 0; outer < counter - 1; outer = outer + 2) {			
			
			start = numbers[outer];
			end = numbers[outer + 1];
			
			if((start == 0 && end == 0))
				break;
			
			System.out.println("Among numbers " + start + " and " + end);

			int processors = Runtime.getRuntime().availableProcessors();
			int inter = end - start;			
			int theChunk = inter / processors + 1;
			
			for(int i = start; i < end; i = i + theChunk) {
				if(i + theChunk <= end)
					new FriendlyThread(i, i + theChunk).start();
				else new FriendlyThread(i, end).start();
			}
			
			while(Thread.activeCount() != 1) {
				/*wait while working */
			}
			
			for(double k: FriendlyThread.ratiosAndNumbers.keySet())
				if(FriendlyThread.ratiosAndNumbers.get(k).size() > 1) {
					System.out.print("Friendly numbers: ");
					for(int i: FriendlyThread.ratiosAndNumbers.get(k))
						System.out.print(i + " ");
					System.out.println();
				}
			
			FriendlyThread.ratiosAndNumbers.clear();
		}
		
		double stopTime = System.currentTimeMillis();
	    double elapsedTime = (stopTime - startTime) / 1000 ;
	    System.out.println("The time spent is: "  + elapsedTime + "s");
	}
}