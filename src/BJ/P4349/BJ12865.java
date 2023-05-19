package BJ.P4349;
// https://www.acmicpc.net/workbook/view/4349
// https://www.acmicpc.net/problem/12865

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865 {
	
	static int N;
	static int K;
	
	static int W [];
	static int V [];

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int [N+1];
		V = new int [N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int [][] DP = new int [N+1][K+1];
		
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if(i-W[j] >= 0) {
					DP[j][i] = Math.max(DP[j-1][i], DP[j-1][i-W[j]]+V[j]);
				}else {
					DP[j][i] = DP[j-1][i];
				}
			}
		}
		System.out.println(DP[N][K]);
	}
}
