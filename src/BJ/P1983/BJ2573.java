package BJ.P1983;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2573

public class BJ2573 {

	static int X,Y;
	static int [][] Ices;
	static boolean [][] Visited;
	
	static int [] mx = {1,-1,0,0};
	static int [] my = {0,0,1,-1};
	
	static public class Ice{
		int x;
		int y;
		int height;
		Ice(int x, int y, int height){
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		Ices = new int [X][Y];
		Visited = new boolean [X][Y];
		
		for (int i = 0; i < X; i++) {
			Arrays.fill(Ice, 0);
			Arrays.fill(Visited, false);
		}
		
		int max_height = -1;
		Queue<Ice> que = new LinkedList<>();
		
		for (int i = 0; i < X; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < Y; j++) {
				Ices[i][j] = Integer.parseInt(st.nextToken());
				
				if(Ices[i][j] > 0) {
					que.offer(new Ice(i,j, Ices[i][j]));
					
					max_height = Math.max(max_height, Ices[i][j]);
					
					Visited[i][j] = true;
				}
			}
		}

		int day = 0;
		for (int i = 0; i < max_height; i++) {
			
			for (int j = 0; j < X; j++) {
				for (int k = 0; k < Y; k++) {
					if(Ices[j][k] > 0) {
						int ice_count = 0;
						for (int l = 0; l < 4; l++) {
							int nx = j + mx[l];
							int ny = k + my[l];
							if(nx < 0 || nx >= X || ny < 0 || ny >= Y) continue;
							if(Ices[nx][ny] == 0) ice_count += 1;
						}
						
						Ices[j][k] = (Ices[j][k] - ice_count < 0)? 0 : Ices[j][k] - ice_count;
					}
				}
			}
			day++;			
			if(seperated()) {
				i = max_height;
			}
			
			
		}
		
	}

	private static boolean seperated() {
		return false;
	}

}