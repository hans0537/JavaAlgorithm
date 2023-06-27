package week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S06_섬지키기 {
	
	// 해쉬 테이블 최대 길이 
	public final int MAX_HASH = 9999;
	// 섬의 최대 크기가 20 
	public final int MAX_N = 20;
	
	public int n;
	public int[][] initMap = new int[MAX_N + 2][MAX_N + 2]; // 주변에 바다가 둘러싸여 있으므로 
	public int[][] modifiedMap = new int[MAX_N + 2][MAX_N + 2];
	
	// 구조물에 대해 주어진 섬정보에 들어갈 수 있는지에 대한 정보를 담을(후보지) 클래스 선언 
	// 미리 모든 경우의 수에 대해 전처리를 해놓는것
	// 문제에서 주어진 조건을 보면 1 ~ 5 밖에 안되므로 미리 해 놓는것이 시간을 줄일 수 있는 방법이다 
	public class Candidate {
		// 시작 위치 
		int r;
		int c;
		// 가로 or 세로인지
		boolean isHorizontal;
		// 정방향인지 역방향인지 
		boolean isReverse;
		
		public Candidate(int r, int c, boolean isHorizontal, boolean isReverse) {
			this.r = r;
			this.c = c;
			this.isHorizontal = isHorizontal;
			this.isReverse = isReverse;
		}
	}
	
	// 해쉬테이블 생성 
	public List<Candidate>[] candidate = new List[MAX_HASH + 1];

	public void init(int N, int mMap[][]) {
		n = N;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				modifiedMap[i + 1][j + 1] = initMap[i + 1][j + 1] = mMap[i][j];
			}
		}
		
		for (int i = 0; i < MAX_HASH; i++) {
			candidate[i] = new ArrayList<>();
		}
		
		// 구조물이 2칸 ~ 5칸 짜리를 모두 탐색 
		// 1칸인 것은 무시 
		// 시간 복잡도는 O(4 * N * N * 4) => O(16N^2) 그리고 N은 20밖에 안됨 
		for (int length = 2; length <= 5; length++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j + length - 1 <= n; j++) {
					int hash = 0;
					
					for (int k = 0; k < length - 1; k++) {
						hash = hash * 10 + (initMap[i][j + k + 1] - initMap[i][j + k] + 5);
					}
					candidate[hash].add(new Candidate(i, j, true, false));
					
					int reverseHash = 0;
					for (int k = length - 1; k >= 1; k--) {
						reverseHash = reverseHash * 10 + (initMap[i][j + k - 1] - initMap[i][j + k] + 5);
					}
					
					// 중복 무시 (데칼 모양) 
					if(reverseHash != hash) {
						candidate[reverseHash].add(new Candidate(i, j, true, true));
					}
				}
			}

			// 세로 탐색 
			for (int i = 1; i + length - 1 <= n; i++) {
				for (int j = 1; j <= n; j++) {
					int hash = 0;
					
					for (int k = 0; k < length - 1; k++) {
						hash = hash * 10 + (initMap[i + k + 1][j] - initMap[i + k][j] + 5);
					}
					candidate[hash].add(new Candidate(i, j, false, false));
					
					int reverseHash = 0;
					for (int k = length - 1; k >= 1; k--) {
						reverseHash = reverseHash * 10 + (initMap[i + k - 1][j] - initMap[i + k][j] + 5);
					}
					
					// 중복 무시 (데칼 모양) 
					if(reverseHash != hash) {
						candidate[reverseHash].add(new Candidate(i, j, false, true));
					}
				}
			}
		}
	}

	public int numberOfCandidate(int M, int mStructure[])
	{
		if (M == 1) { // 구조물이 1칸 짜리이면 모든 구역 
			return n * n;
		}
		
		// 입력 받은 값을 해쉬 값 계산하기 
		// 그리고 전처리 해놨던 모든 해쉬 값이랑 비교 하는 것... 
		int hash = 0;
		for(int i = 0; i < M - 1; i++) {
			hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);
		}
		// 해당 해쉬의 배열 크기 반환 
		return candidate[hash].size();
	}

	public boolean[][] visited = new boolean[MAX_N + 2][MAX_N + 2];
	public int[] dx = {1, 0, -1, 0};
	public int[] dy = {0, 1, 0, -1};
	
	// 해수면 올리기 
	public int bfs(int[][] mMap, int mSeaLevel) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		for (int i = 0; i <= n + 1; i++) {
			for (int j = 0; j <= n + 1; j++) {
				// 테두리에 있는 바다 부터 시작 
				if(i == 0 || i == n + 1 || j == 0 || j == n + 1) {
					q.add(new int[] {i, j});
					visited[i][j] = true;
				}else {
					visited[i][j] = false;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 4방향 탐색 
			for(int d = 0; d < 4; d++) {
				int[] next = {cur[0] + dx[d], cur[1] + dy[d]};
				
				if(next[0] >= 1 && next[0] <= n && next[1] >= 1 && next[1] <= n) {
					// 땅이 해수면 보다 낮은 곳들은 잠기게 처리 그리고 대기열에 추가 
					if(!visited[next[0]][next[1]] && mMap[next[0]][next[1]] < mSeaLevel) {
						q.add(next);
						visited[next[0]][next[1]] = true;
					}
				}
			}
		}
		
		int res = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(!visited[i][j]) res++;
			}
		}
		
		return res;
	}
	
	
	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		int res = -1;
		
		// 1칸 짜리 구조물이면 모든 위치에 놓아 본다 
		if (M == 1) {
			for(int i = 1; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					modifiedMap[i][j] = initMap[i][j] + mStructure[0];
					res = Math.max(res, bfs(modifiedMap, mSeaLevel));
					modifiedMap[i][j] = initMap[i][j];
				}
			}
			return res;
		}
		
		// 주어진 구조물도 해쉬 작업 실행 
		int hash = 0;
        for (int i = 0; i + 1 < M; i++) {
        	hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);        	
        }
        
        for (Candidate wall : candidate[hash]) {
        	if (wall.isHorizontal) {
        		// 구조물을 놓으면 모든 위치가 같아지므로 하나의 구조물 + 원래 높이 만 구하면 된다
        		// 첫번째 위치끼리 더한 값을 사용 
        		int height = mStructure[0] + (wall.isReverse ? initMap[wall.r][wall.c + M - 1] : initMap[wall.r][wall.c]);
        		for (int i = 0; i < M; i++) {
        			modifiedMap[wall.r][wall.c + i] = height;
        		}
        		res = Math.max(res, bfs(modifiedMap, mSeaLevel));
        		for (int i = 0; i < M; i++) {
        			modifiedMap[wall.r][wall.c + i] = initMap[wall.r][wall.c + i];
        		}
        	} else {
        		int height = mStructure[0] + (wall.isReverse ? initMap[wall.r + M - 1][wall.c] : initMap[wall.r][wall.c]);
        		for (int i = 0; i < M; i++) {
        			modifiedMap[wall.r + i][wall.c] = height;
        		}
        		res = Math.max(res, bfs(modifiedMap, mSeaLevel));
        		for (int i = 0; i < M; i++) {
        			modifiedMap[wall.r + i][wall.c] = initMap[wall.r + i][wall.c];
        		}
        	}
        }
		return res;
	}
}
