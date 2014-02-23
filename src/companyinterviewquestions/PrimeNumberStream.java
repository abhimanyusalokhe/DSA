
/*
 * Question: Write a command-line program that reads 16-bit integers from stdin, prints "yes" or "no" depending on whether each number is a prime, 
 * and loops while it can still read more numbers from stdin. Numbers will be separated by whitespace,
 * and there may be an infinite sequence of them (e.g. the input could be coming from some other program). 
 * Any input errors should cause warnings to be printed on stderr. The program needs to run quickly, and produce accurate results.
 * 
 * Approach: The basic method is to take an integer and check whether it is prime or not and print it.
 * It requires O(n) time complexity.
 * To improve upon this approach:
 * We the size of the integer is 16bit.
 * Thus the integers are in the range of 1 - 2^16. This implies that there is high probability of the integers repeating.
 * So if we could store the results of the previous computations in a HashMap we can quickly check for the result of the repeated integers in O(1).
 * Thus this reduces the time required for processing a large InputStream.
 * The space complexity of this is O(n/2) where n = 2^16 as even numbers cannot be prime. 
 * 
 * 
 */

package JustinTVInterview;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class PrimeNumberStream {


	/*
	 * Input: integer to be processed and HashMap storing the previously computed integers with result.
	 * Output: returns a true if the integer is a prime.
	 * 
	 * It first determines if the integer is odd, then it checks whether the HashMap contains the integer as key,
	 * if yes then return the boolean value stored for the integer.
	 * else it calls the private method checkPrime to determine whether the integer is prime and store it in HasMap and then return the value. 
	 */

	public static boolean isNumberPrime(short number, HashMap<Short, Boolean> primeMap)
	{
		boolean isPrime = false;
		try{
			if(number == 2 || number ==1)
			{
				return true;
			}
			if(number != 0 && number%2 !=0)
			{
				if(primeMap.containsKey(number))
				{
					isPrime = primeMap.get(number);
					return isPrime;
				}
				else{

					isPrime = checkPrime(number);
					primeMap.put(number, isPrime);
					return isPrime;
				}
			}
			else{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isPrime;
	}

// checks whether a given integer is prime.
	private static boolean checkPrime(short number)
	{
		try{
			
			for(short i = 3; i*i <=number; i+=2)
			{
				if(number%i == 0)
				{
					return false;
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			HashMap<Short, Boolean> primeMap = new HashMap<>(); 
			

			// default delimiter for scanner is " ".
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter a sequence: ");

			while(scanner.hasNext())
			{
				String str = scanner.next();
				if(str.matches("[-+]?\\d+(\\.\\d+)?"))
				{

					short num = Short.valueOf(str);
					boolean isPrime = isNumberPrime(num, primeMap);
					if(isPrime)
					{
						System.out.println(num+": Yes");

					}
					else{
						System.out.println(num+": No");
					}

				}
				else{
					System.out.println(str + ": Input not a number");
				}
			}
		}catch(Exception e)
		{
			System.out.println("Invalid Input");
		}


	}

}
