package v1;
import java.util.*;

public class RSA {
	
	public static final String DELIMITER = "00";
	
	public static void main (String args[]) {
		Person Alice = new Person();
		Person Bob = new Person();
		
		String msg = "Bob, let's have lunch";
		long[] cipher;
		cipher = Alice.encryptTo(msg, Bob);
		
		System.out.println("Message is: " + msg);
		System.out.println("Alice sends:");
		show(cipher);
		
		System.out.println("Bob descodes and reads: " + Bob.decrypt(cipher));
		
		System.out.println();
		
	}

	public static long getInverse(long n, long m){
		for (int k = 1; k < m; k++) { 
	        if (((n%m) * (k%m)) % m == 1) { 
	            return k;
	        }
		}
		return 1;
	}
	
	//tested
	public static long power(long base, long power, long mod) {
		long result = 1;
		while(power>0) {
			if(power % 2 != 0) {
				result = result * base;
			}
			power = power / 2;
			base = base * base;
			result = result % mod;
		}
		result = result % mod;
		return result;
	}
	
	
	public static int randomPrime(int min, int max) {
		if(min == 1 || min == 0) {
			min = 2;
		}
		if(max == 0 || max == 1) {
			return -1;
		}
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int k = min; k < max; k++) {
			boolean isPrime = true;
			for(int n = 2; n <= k/2; n++) {
				if(k % n == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				primes.add(k);
			}
		}
		int random = (int)(Math.random() * primes.size());
		return primes.get(random);
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
	
	static String longTo2Chars(long x ) {
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
		System.out.print("]");
	}
}