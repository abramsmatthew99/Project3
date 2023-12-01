package v1;
import java.util.*;

public class RSA {
	
	public static final String DELIMITER = "00";
	
	public static void main (String args[]) {
		Person Alice = new Person();
		Person Bob = new Person();
		
		String msg = "Bob, let's have lunch";
		long[] cipher = Alice.encryptTo(msg, Bob);
		
		System.out.println("Message is: " + msg);
		System.out.println("Alice sends:");
		show(cipher);
		
		System.out.println("Bob decodes and reads: " + Bob.decrypt(cipher));
		
		System.out.println();
	}

	public static long getInverse(long n, long m){
		for (long k = 1; k < m; k++) { 
	        if (((n%m) * (k%m)) % m == 1) { 
	            return k;
	        }
		}
		return -1;
	}
	
	/* @author Riley Miller
	 * @param  a base value, a power for that base, and a mod value
	 * @return the result of the equation
	 */
	
	public static long power(long base, long power, long mod) {
		if (mod == 1)
			return 0;
		
		long result = 1;
		base = base % mod;
		
		while (power > 0)
		{
			if (power % 2 == 1)
				result = (result * base) % mod;
			
			power/=2;
			base = (base*base)%mod;
		}
		return result;
	}
	
	/*  @author Riley Miller
	 * 	@param minimum value and maximum value for the random number
	 *  @return a random prime number
	 */
	public static long randomPrime(long min, long max) {
		Random random = new Random();
		long randNum;
		boolean done = false;
		
		do {
			randNum = (long)(random.nextDouble() * (max-min + 1)) + min;
			for (int i = 2; i < max; i++)
			{
				if (randNum % i == 0)
					done = true;
			}
		} while (!done)
		
		return randNum;
	}
	
	static long relPrime(long n) {

		Random random = new Random();
		long candidate;
		
		do {
			candidate = random.nextLong() % n;
			//Handle negatives
			if (candidate < n) {
				candidate += n;
			}
		} while (gcd(candidate, n) != 1) ;
		
		return candidate;
	}
	
	private static long  gcd(long a, long b) {
		while (b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	static long toLong(String msg, int p) {
		if (p > msg.length() - 2) {
			return 0;
		}
		char[] msgArr = msg.toCharArray(); 
		String concat = (int) msgArr[p] +  DELIMITER + (int) msgArr[p+1];
		return (long) Integer.parseInt(concat);
	}
	
	static String longTo2Chars(long x) {
		String[] charStrings = Long.toString(x).split(DELIMITER);
		StringBuilder result = new StringBuilder();
		for (String charString : charStrings) {
			char msg = (char) Integer.parseInt(charString);
			result.append(msg);
		}
		return result.toString();
	}
	
	static void show(long[] cipher) {
		System.out.print("[ ");
		for (long val: cipher) {
			System.out.print(val + " ");
		}
		System.out.print("\b]");
	}
}