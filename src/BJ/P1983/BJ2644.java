package BJ.P1983;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2644

public class BJ2644 {
	
	static int N, C;
	static int A, B;
	static ArrayList<Integer> [] R;
	static boolean [] V;
	static HashSet <Integer> cache;
	static int Result = -1;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		R = new ArrayList [N+1];
		V = new boolean [N+1];
		
		for (int i = 1; i < N+1; i++) {
			R[i] = new ArrayList<Integer>();
			V[i] = false;
		}
		
		C = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < C+1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			R[parent].add(child);
			R[child].add(parent);
		}

		dp(A, B, 0);
		
		System.out.println(Result);

	}

	private static void dp(int a, int b, int cnt) {

		if(a == b) {
			Result = cnt;
			return;
		}
		
		if(!V[a]) {
			V[a] = true;
			cnt += 1;
			for (int i = 0; i < R[a].size(); i++) {
				dp(R[a].get(i), b, cnt);				
			}
			V[a] = false;
			cnt -= 1;
		}
	}
}
