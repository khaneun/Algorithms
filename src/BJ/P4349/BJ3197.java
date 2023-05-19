package BJ.P4349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3197 {
	
	static int R, C;
	static char [][] MAP;
	static boolean [][] visited;
	
	static Queue <Point> search;
	static Queue <Point> water;
	static int [] mx = {-1,1,0,0};
	static int [] my = {0,0,-1,1};
	
	static class Point {
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
		
		MAP = new char [R][C];
		visited = new boolean [R][C];
		
		Point [] swan = new Point[2];
		int cnt = 0;
		
		water = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			
			char [] temp = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				MAP[i][j] = temp[j];
				
				if(MAP[i][j] == 'L' && cnt < 2) {
					swan[cnt] = new Point(i,j);
					cnt++;
				}
				
				if(MAP[i][j] != 'X') {
					water.offer(new Point(i,j));
				}
			}
			
		}
		
		search = new LinkedList<>();
		search.offer(swan[0]);
		visited[swan[0].x][swan[0].y] = true;
		
		boolean meet = false;
		int day = 0;
		while(true) {
			Queue <Point> next = new LinkedList<>();
			while(!search.isEmpty()) {
				Point temp = search.poll();
				int x = temp.x;
				int y = temp.y;
				
				if(x == swan[1].x && y == swan[1].y) {
					meet = true;
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int nx = x + mx[i];
					int ny = y + my[i];
					
					if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
					visited[nx][ny] = true;
					
					if(MAP[nx][ny] == 'X') {
						next.add(new Point(nx,ny));
						continue;
					}
					search.offer(new Point(nx,ny));
				}
			}
			
			if(meet) break;
			search = next;
			
			int size = water.size();
			
			for (int i = 0; i < size; i++) {
				Point temp = water.poll();
				int x = temp.x;
				int y = temp.y;
				
				for(int j = 0; j < 4; j++) {
					int nx = x + mx[j];
					int ny = y + my[j];
					
					if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					
					if(MAP[nx][ny] == 'X') {
						MAP[nx][ny] = '.';
						water.offer(new Point(nx,ny));
					}
					
				}
			}
			day++;
		}
		
		System.out.println(day);
		
	}
}