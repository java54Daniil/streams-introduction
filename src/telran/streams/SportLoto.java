package telran.streams;

import static telran.streams.StreamIntroductionMethods.*;

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

			if (args.length!=3|| min >= max || amount <= 0 || amount > (max - min + 1)) {
				throw new IllegalArgumentException("Invalid arguments");
			}

			int[] numbers = getRandomArray(min, max + 1, amount);
			System.out.print("Sport Loto numbers: ");
			for (int i = 0; i < amount; i++) {
				System.out.print(numbers[i]);
				if (i < amount - 1) {
					System.out.print(",");
				}
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
