package BJ.P4349;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2933 {

	static int R, C;
	static char [][] M;
	static int N;
	static int [] B;
	static boolean [][] V;
	static int [] mx = {1,-1,0,0};
	static int [] my = {0,0,1,-1};
	
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		M = new char [R][C];
		V = new boolean [R][C];
		
		for (int i = 0; i < R; i++) {
			char [] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				M[i][j] = line[j];
			}
		}
		
		N = Integer.parseInt(br.readLine());
		B = new int [N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken()); 
		}
		
		//setCluster();
		//printM();
		
		for (int i = 0; i < N; i++) {
			int x = R - B[i];
			if(i%2==0) {
				for (int j = 0; j < C; j++) {
					if(M[x][j] != '.') {
						M[x][j] = '.';
						j = C;
					}
				}
				checkCluster();
				printM();
			}else {
				for (int j = C-1; j >= 0; j--) {
					if(M[x][j] != '.') {
						M[x][j] = '.';
						j = -1;
					}
				}
				checkCluster();
				printM();
			}
		}
	}
	
	private static void setCluster() {
		
		int cluster = 0;
		V = new boolean [R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				if(!V[i][j] && M[i][j] == 'x') {
					V[i][j] = true;
					M[i][j] = (char) ('x'+cluster);
					
					Queue <Point> que = new LinkedList <Point>();
					que.offer(new Point(i,j));
					
					while(!que.isEmpty()) {
						Point temp = que.poll();
						int x = temp.x;
						int y = temp.y;
						
						for (int k = 0; k < 4; k++) {
							int nx = x + mx[k];
							int ny = y + my[k];
							
							if(nx < 0 || nx >= R || ny < 0 || ny >= C || V[nx][ny] || M[nx][ny] == '.') continue;
							
							V[nx][ny] = true;
							M[nx][ny] = (char) ('x'+cluster);
							que.offer(new Point(nx, ny));
						}
					}
					cluster++;
				}
			}
		}
	}
	
	private static void checkCluster() {
		
		V = new boolean [R][C];
		
		for (int j = 0; j < C; j++) {
			
			if(!V[R-1][j] && M[R-1][j] == 'x') {
				V[R-1][j] = true;
				M[R-1][j] = 'y';
				
				Queue <Point> que = new LinkedList <Point>();
				que.offer(new Point(R-1,j));
				
				while(!que.isEmpty()) {
					Point temp = que.poll();
					int x = temp.x;
					int y = temp.y;
					
					for (int k = 0; k < 4; k++) {
						int nx = x + mx[k];
						int ny = y + my[k];
						
						if(nx < 0 || nx >= R || ny < 0 || ny >= C || V[nx][ny] || M[nx][ny] == '.') continue;
						
						V[nx][ny] = true;
						M[nx][ny] = 'y';
						que.offer(new Point(nx, ny));
					}
				}
			}
		}
		
		System.out.println("Done!");
		printM();
		
		Queue <Point> que = new LinkedList <Point>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!V[i][j] && M[i][j] == 'x') {
					que.offer(new Point(i,j));
				}
			}
		}
		
		if(que.isEmpty()) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(M[i][j] == 'y') {
						M[i][j] = 'x';
					}
				}
			}
			return;
		}
		
		boolean finish = false;
		int move = 0;
		Queue <Point> tque = new LinkedList <Point>();
		
		while(!finish) {
			move++;
			while(!que.isEmpty()) {
				Point temp = que.poll();
				tque.offer(temp);
				if(temp.x+move+1 == R) {
					finish = true;
					break;
				}else {
					if(M[temp.x+move+1][temp.y] == 'y') {
						finish = true;
						break;
					}					
				}
			}
			que = tque;
			System.out.println("move : " + move);
		}
		
		while(!que.isEmpty()) {
			Point temp = que.poll();
			int x = temp.x;
			int y = temp.y;
			M[x][y] = '.';
			M[x+move][y] = 'y';
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(M[i][j] == 'y') {
					M[i][j] = 'x';
				}
			}
		}
	}

	public static void printM() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(M[i][j]);
			}
			System.out.println();
		}
	}

}
