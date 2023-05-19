package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://www.acmicpc.net/workbook/view/4349
// https://www.acmicpc.net/problem/1655

public class BJ1655 {

	static int N;
	
	public static void main(String[] args) throws Exception {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int [] C = new int [N];
		int [] R = new int [N];
		
		PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2-o1);
		PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2) -> o1-o2);
		
		for (int i = 0; i < N; i++) {
			C[i] = Integer.parseInt(br.readLine());
			
			if(max.size() == min.size()) {
				max.offer(C[i]);
			}else {
				min.offer(C[i]);
			}
			
			if(!min.isEmpty() && max.peek() > min.peek()) {
				max.offer(min.poll());
				min.offer(max.poll());
			}
			
			R[i] = max.peek();
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(R[i]);
		}
	}
}
