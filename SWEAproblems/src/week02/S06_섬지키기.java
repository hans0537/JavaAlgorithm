package week02;

import java.util.ArrayList;
import java.util.List;

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
		for (int length = 2; length <= 5; length++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j + length - 1 <= n; j++) {
					int hash = 0;
					
					for (int k = 0; k < length; k++) {
						if (hash + k)
					}
					
				}
			}
		}
	}

	public int numberOfCandidate(int M, int mStructure[])
	{
		return 0;
	}

	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		return 0;
	}
}
