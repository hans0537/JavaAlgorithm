package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class S03_파핑파핑지뢰찾기 {

	static int[][] map;
	static int ans, N;
	static final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}, dy = {0, 0, -1, 1, 1, -1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					// 지뢰가 없는 부분은 -1 
					if(str.charAt(j) == '.') map[i][j] = -1;
					else map[i][j] = -2;
				}
			}
			
			solve();
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void solve() {
		// 주변이 지뢰가 없는 0부분들만 우선적으로 누른다. 
		// 0부분들 중 어느 하나만 눌러도 다 퍼진다 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != -1) continue;
				if (isZero(i, j)) {
					click(i, j); 
					ans ++;
				}
			}
		}
		
		// 0을 먼저 누르고 나서 남은 부분을 클릭해야 하므로
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) ans ++;
			}
		}
	}
	
	private static boolean isZero(int r, int c) {
		for(int i = 0; i < 8; i++) {
			int mr = r + dx[i];
			int mc = c + dy[i];
			
			if (mr < 0 || mr >= N || mc < 0 || mc >= N) continue;
			
			if (map[mr][mc] == -2) return false;
		}
		return true;
	}
	
	private static void click(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		
		// 눌림 처리하기  
		map[r][c] = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			map[cur[0]][cur[1]] = 0;
			for (int i = 0; i < 8; i++) {
				int mr = cur[0] + dx[i];
				int mc = cur[1] + dy[i];
				
				if(mr < 0 || mr >= N || mc < 0 || mc >= N || map[mr][mc] != -1) continue;
				
				// 8방향탐색 부분이 주변에 지뢰가 없는 0부분이면 대기열에 추가 
				if(isZero(mr, mc)) q.add(new int[] {mr, mc});
				map[mr][mc] = 0;
			}
		}
	}
}
