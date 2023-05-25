package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BJ2749 {

	static int D = 1000000;
	static long N;
	static HashMap<Long, long[][]> cache;
	static long [][] F = {{1,1},{1,0}};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		cache = new HashMap<>();
		
		long result = fibo(N-1);
		System.out.println(result);
		
		//Pisano period
		long p1 = 0;
		long p2 = 1;
		long period = 0;
		while(true) {
			if(p1 == 0 && p2 == 1 && period != 0) {
				break;
			}
			long temp = p1;
			p1 = p2;
			p2 = (temp + p1)%D;
			period++;
		}
		
		System.out.println(period);
	}

	private static long fibo(long k) {

		if(k==-1) return 0;
		if(k==0||k==1) return 1;
		
		long temp [][] = pow(k);
		return temp[0][0];
		
	}
	
	private static long [][] pow(long k) {
		
		if(k==1) return F;
		if(k==2) return mul(F,F);
		
		long temp [][] = F;
		
		if(cache.containsKey(k)) return cache.get(k);
		
		if(k%2==0) {
			temp = mul(pow(k/2), pow(k/2));
		}else {
			temp = mul(F, mul(pow((k-1)/2), pow((k-1)/2)));
		}
		
		cache.put(k, temp);
		
		return temp;
	}
	
	private static void printM(long [][] m) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}		
	}

	private static long [][] mul(long [][] A, long [][] B){
		long [][] temp = new long[2][2];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					temp[i][j] += (A[i][k]%D*B[k][j]%D)%D;
					temp[i][j] = temp[i][j]%D;
				}
			}
		}
		return temp;
	}

}
