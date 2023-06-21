package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S04_프로세서연결하기 {
	
	static int N, maxCnt, ans;
	static int[][] arr;
	static final int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	
	// 코어의 위치를 담을 배열 선언 
	static List<int[]> cList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			cList = new ArrayList<int[]>();
			
			for (int i = 0; i < N; i++) {
				String[] tmp = br.readLine().split(" ");
				for (int j = 0; j < tmp.length; j++) {
					arr[i][j] = Integer.parseInt(tmp[j]);
					
					// 가장 자리 를 제외한 코어위치 들을 저장 
					if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;
					if(arr[i][j] == 1) cList.add(new int[] {i, j});
				}
			}
			
			ans = 0;
			maxCnt = 0;
			backtracking(0, 0, 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	// idx 는 현재 코어 번호, length 는 현재까지의 코어 전선의 길이 총합, cnt 는 코어가 연결된 개수 
	private static void backtracking(int idx, int length, int cnt) {
		
		// 최대개수 최신화 
		if(cnt > maxCnt) {
			maxCnt = cnt;
			ans = length;
		// 만약 개수가 같다면 최소 길이 최신화 
		} else if (cnt == maxCnt) {
			ans = Math.min(length, ans);
		}
		
		// 모든 코어를 탐색했다면 종료 
		if (idx == cList.size()) {
			return;
		}
		
		
		// 4방향 중 한 방향 선택 
		for (int i = 0; i < 4; i++) {
			int mx = cList.get(idx)[0] + dx[i];
			int my = cList.get(idx)[1] + dy[i];
			
			int l = extend(cList.get(idx), i);
			if (l == -1) continue;
			
			// 하나의 코어에 하나의 방향을 선택한뒤 다음 코어를 선택해 나간다 
			backtracking(idx + 1, length + l, cnt + 1);
			// 경우의수 가 끝나면 직전의 상태로 되돌려야 하므로
			rollback(cList.get(idx), i);	
		}
		
		// 아무 방향을 선택안하는 방법 탐색 
		backtracking(idx + 1, length, cnt);
	}
	
	// 현재 방향에서 전선을 이을 수 있는지 확인 
	private static int extend(int[] point, int d) {
		int r = point[0] + dx[d];
		int c = point[1] + dy[d];
		
		int res = 0;
		
		// 늘일 수 있는 길인지 판별  
		while(isValid(r, c)) {
			if(arr[r][c] != 0) return -1;
			r += dx[d];
			c += dy[d];
		}
		
		// 가능하면 전선을 잇는다 (2로 표시)
		r = point[0] + dx[d];
		c = point[1] + dy[d];
		while(isValid(r, c)) {
			arr[r][c] = 2;
			r += dx[d];
			c += dy[d];
			res++;
		}
		return res;
	}
	
	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	private static void rollback(int[] point, int d) {
		int r = point[0] + dx[d];
		int c = point[1] + dy[d];
		
		while(isValid(r, c)) {
			arr[r][c] = 0;
			r += dx[d];
			c += dy[d];
		}
	}
}
