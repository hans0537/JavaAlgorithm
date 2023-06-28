package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S02_Knapsack {
	
	// dp[N][K] := 1~N 개 물건을 이용해서 부피가 K인 가방에 넣을 수 있는 최대 가치
	// 팁!! 문제에서 주어진 미지수의 갯수를 활용하면 DP 테이블을 설정하는데 도움이 된다.
	static int dp[][], v[], c[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			dp = new int[N + 1][K + 1];
			v = new int[N + 1];
			c = new int[N + 1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				c[i] = Integer.parseInt(st.nextToken());
			}
		
			// 활용할 물건의 개수를 1개부터 ~ N 개까지. 즉) i개의 물건을 사용할 수 있다.
			for (int i = 1; i <= N; i++) {
				// j 크기만큼의 부피에서 i개중에서 최대 가치를 얻을 것이다.
				for (int j = 0; j <= K; j++) {
					// 1번째 선택
					// 먼저 i'번' 물건을 선택 안한것 즉 dp[i-1][j] 직전 값을 가져온다.
					dp[i][j] = dp[i-1][j];
					
					if (v[i] <= j) {
						// 만약 i'번' 물건이 들어갈 수 있다면 더 큰 가치로 바꾼다						
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + c[i]);
					}
				}
			}
			System.out.println("#" + tc + " " + dp[N][K]);
		}
	}
}
