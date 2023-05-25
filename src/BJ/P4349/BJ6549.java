package BJ.P4349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6549

public class BJ6549 {
	
	static int N;
	static long Histogram [];

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			Histogram = new long [N];
			
			if(N==0) break;
			
			for (int i = 0; i < N; i++) {
				Histogram[i] = Long.parseLong(st.nextToken());
			}
			
			Stack <Integer> stack = new Stack <Integer>();
			
			long result = Long.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				
				int cnt = 0;
				
				while(!stack.isEmpty() && Histogram[stack.peek()] >= Histogram[i]) {
					cnt++;
					long hight = Histogram[stack.pop()];
					long width = stack.isEmpty() ? i : cnt;
					result = Math.max(result, hight * width);
					
				}
				stack.push(i);
			}
			
			while(!stack.isEmpty()) {
				int temp = stack.pop();
				long width = stack.isEmpty() ? N : N-temp;
				
				result = Math.max(result, Histogram[temp]*width);
			}
			
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
	}
}
