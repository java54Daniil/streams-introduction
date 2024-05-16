package telran.streams;


import java.util.Random;
public class SportLoto {

	public static void main(String[] args) {
	
		// printing out sportloto random numbers
		// application with command line mandatory arguments
		// first argument minimal inclusive value
		// second argument maximal inclusive value
		// third argument amount of numbers
		// Example :java -jar sportloto 1 49 7
		// 3,10,49,1,40,6,7
	
		
		try {
			if (args.length != 3) {
				 throw new IllegalArgumentException("Pattern to use application: <min> <max> <number of numbers>");
			}
			int min = Integer.parseInt(args[0]);
			int max = Integer.parseInt(args[1]);
			int amount = Integer.parseInt(args[2]);

			if ( min >= max || amount <= 0 || amount > (max - min + 1)) {
				throw new IllegalArgumentException("Invalid arguments");
			}

			int[] numbers =  new Random().ints(min, max+1).distinct().limit(amount)
					.toArray();
			System.out.print("Sport Loto numbers: ");
			for (int i = 0; i < amount && i < numbers.length; i++) {
				
				if (i>0) {
					System.out.print(",");
				}
				System.out.print(numbers[i]);
			}
			System.out.println();

		} catch (NumberFormatException e) {
			System.out.println("Invalid arguments. Please provide integers.");
		}
		catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
	}
}
