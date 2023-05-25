package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ10830 {
	
	static int N;
	static long B;
	static int P = 1000;
	static long [][] MAT;
	static HashMap <Long, long [][]> cache;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		cache = new HashMap<>();
		
		MAT = new long [N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				MAT[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		long [][] result = pow(B);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				MAT[i][j] = result[i][j] % P;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(MAT[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static long [][] pow(long b) {
		
		if(b == 1) return MAT;
		if(b == 2) return mul(MAT, MAT);
		
		if(cache.containsKey(b)) return cache.get(b);
		
		long [][] temp = new long [N][N];
		
		if(b%2==0) {
			temp = mul(pow(b/2), pow(b/2));
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = temp[i][j] % P;
				}
			}
		}else {
			temp = mul(MAT, mul(pow((b-1)/2), pow((b-1)/2)));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = temp[i][j] % P;
				}
			}
		}
		
		cache.put(b, temp);
		return temp;
	}
	
	private static long [][] mul(long [][] A, long [][] B) {
		
		long [][] TMAT = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					TMAT[i][j] = (TMAT[i][j] % P + ( A[i][k] % P * B[k][j] % P ) % P ) % P;
				}
			}
		}
		return TMAT;
	}
}
