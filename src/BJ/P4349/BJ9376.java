package BJ.P4349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9376 {
	
	static int T;
	static int H,W;
	
	static int MAP [][];
	static int WALL = -1;
	static int PRISONER = 2;
	static int DOOR = 1;
	static int PATH = 0;
	
	static int mx [] = {1,-1,0,0};
	static int my [] = {0,0,1,-1};
	
	static public class Person {
		int x;
		int y;
		
		Person(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		T = Integer.parseInt(br.readLine());
		
		int answer [] = new int [T];
		
		for (int i = 0; i < T; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken())+2;
			W = Integer.parseInt(st.nextToken())+2;
			
			MAP = new int [H][W];
			
			Person [] person = new Person [3];
			person[0] = new Person(0,0);
			int prisoner = 1;
			
			for (int j = 1; j < H-1; j++) {
				String inputMap = "." + br.readLine() + ".";
				for (int k = 1; k < W-1; k++) {
					char position = inputMap.charAt(k);
					switch(position) {
						case '*':
							MAP[j][k] = WALL;
							break;
						case '#':
							MAP[j][k] = DOOR;
							break;
						case '.':
							MAP[j][k] = PATH;
							break;
						case '$':
							MAP[j][k] = PRISONER;
							person[prisoner] = new Person(j,k);
							prisoner++;
							break;
					}
				}
			}
			
			for (int j = 0; j < W; j++) {
				MAP[0][j] = MAP[H-1][j] = PATH;
			}
			
			int [][] person_cost = bfs(person[0]);
			int [][] prisoner1_cost = bfs(person[1]);
			int [][] prisoner2_cost = bfs(person[2]);
			
			int temp_cost [][] = new int [H][W];
			answer[i] = Integer.MAX_VALUE;
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++) {
					if(MAP[j][k] == WALL) continue;
					temp_cost[j][k] = person_cost[j][k] + prisoner1_cost[j][k] + prisoner2_cost[j][k];
					if(MAP[j][k] == DOOR) temp_cost[j][k] -= 2;
					answer[i] = (answer[i] > temp_cost[j][k])? temp_cost[j][k] : answer[i];
				}
			}
			
		}
		
		for (int j = 0; j < T; j++) {
			System.out.println(answer[j]);
		}
		
	}

	private static void printMap(int[][] person_cost) {
		System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(person_cost[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static int [][] bfs(Person person) {

		int temp_cost [][] = new int [H][W];
		for (int j = 0; j < H; j++) {
			Arrays.fill(temp_cost[j], -1);
		}
		
		Queue<Person> que = new LinkedList<Person>();
		que.add(person);
		temp_cost[person.x][person.y] = 0;
		
		while(!que.isEmpty()) {
			Person temp_person = que.poll();
			int x = temp_person.x;
			int y = temp_person.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + mx[i];
				int ny = y + my[i];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				if(MAP[nx][ny] == WALL) continue;
				
				if(MAP[nx][ny] == PATH || MAP[nx][ny] == PRISONER) {
					if(temp_cost[nx][ny] == -1 || temp_cost[nx][ny] > temp_cost[x][y]) {
						temp_cost[nx][ny] = temp_cost[x][y];
						que.add(new Person(nx,ny));
					}
				}else if(MAP[nx][ny] == DOOR) {
					if(temp_cost[nx][ny] == -1 || temp_cost[nx][ny] > temp_cost[x][y] + 1) {
						temp_cost[nx][ny] = temp_cost[x][y] + 1;
						que.add(new Person(nx,ny));
					}
				}
			}
		}
		return temp_cost;
	}
}
