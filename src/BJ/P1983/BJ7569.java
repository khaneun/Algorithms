package BJ.P1983;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7569

public class BJ7569 {
	
	static int X,Y,Z;
	static int mx [] = {1, -1, 0, 0, 0, 0};
	static int my [] = {0, 0, 1, -1, 0, 0};
	static int mz [] = {0, 0, 0, 0, 1, -1};
	
	static int Apples [][][];
	static boolean Visited [][][];
	
	static public class Apple{
		int x;
		int y;
		int z;
		int day;
		Apple(int x, int y, int z, int day){
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());
		
		Apples = new int [X][Y][Z];
		Visited = new boolean [X][Y][Z];
		
		Queue <Apple> que = new LinkedList<>();
		
		int apple_count = 0;
		for (int i = 0; i < Z; i++) {
			for (int j = 0; j < X; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < Y; k++) {
					Apples[j][k][i] = Integer.parseInt(st.nextToken());
					Visited[j][k][i] = false;
					
					if(Apples[j][k][i] == 1) {
						que.offer(new Apple(j,k,i,0));
						Visited[j][k][i] = true;
					}
					
					if(Apples[j][k][i] == 1 || Apples[j][k][i] == 0) {
						apple_count++;
					}
					
				}
			}
		}
		
		int Answer = 0;
		if(que.size() == 0) Answer = -1;
		if(que.size() == apple_count) Answer = 0;
		
		while(!que.isEmpty()) {
			Apple temp = que.poll();
			int x = temp.x;
			int y = temp.y;
			int z = temp.z;
			int day = temp.day;
			
			for (int i = 0; i < 6; i++) {
				int nx = x + mx[i];
				int ny = y + my[i];
				int nz = z + mz[i];
				if(nx < 0 || nx >= X || ny < 0 || ny >= Y || nz < 0 || nz >= Z) continue;
				if(Apples[nx][ny][nz] == -1 || Apples[nx][ny][nz] == 1) continue;
				if(!Visited[nx][ny][nz]) {
					Visited[nx][ny][nz] = true;
					Apples[nx][ny][nz] = 1;
					//System.out.println("("+x+","+y+","+z+") -> ("+nx+","+ny+","+nz+") // Day : " + (day+1));
					que.offer(new Apple(nx,ny,nz,day+1));
					if(Answer < day+1 ) {
						Answer = day+1;
					}
				}
			}
		}
		
		for (int i = 0; i < Z; i++) {
			for (int j = 0; j < X; j++) {
				for (int k = 0; k < Y; k++) {
					if(Apples[j][k][i] == 0) Answer = -1;
				}
			}
		}
		
		System.out.println(Answer);
	}

}
