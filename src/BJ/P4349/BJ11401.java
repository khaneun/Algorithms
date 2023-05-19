package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ11401 {
	
	static int D = 1000000007;
	static HashMap <Long, Long> cache;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		cache = new HashMap <> ();
		
		System.out.println(combination(N,K));
		
	}
	
	public static long combination(long n, long k) {
		
		if(n == k) return 1;
		if(k == 0) return 1;
		
		long key = n * 10000000 + k;
		if(cache.containsKey(key)) return cache.get(key);
		
		long result = (combination(n-1, k-1) % D + combination(n-1, k) % D ) % D;
		cache.put(key, result);
		
		return result;
	}

}
