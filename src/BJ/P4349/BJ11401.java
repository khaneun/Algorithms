package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ11401 {
	
	static int P = 1000000007;
	static HashMap <Long, Long> cache;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		cache = new HashMap <Long, Long>();
		
		long result = ( factorial(N) % P * exp(factorial(K) * factorial(N-K), P-2) % P ) % P;
		
		System.out.println(result);
		
	}
	
	public static long factorial(long a) {
		// when a = 0, return 1 
		long result = 1L;
		while(a > 1) {
			result = (result * a) % P;
			a--;
		}
		return result;
	}
	
	public static long exp(long base, long expo) {

		if(expo == 1) return base % P;
		
		long result = 1;
		long temp = exp(base, (expo)/2);
		
		if (expo % 2 == 0) {
			result = temp % P * temp % P;
		}else {
			result = base % P *  temp % P * temp % P;
		}
		return result;
	}

}
