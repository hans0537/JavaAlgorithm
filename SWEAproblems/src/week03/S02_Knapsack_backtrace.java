package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class S02_Knapsack_backtrace {
// 이전 문제에서 최대 가치는 구했는데 만약 어떤 물건들을 선택해서 최대가치를 구했는지 알아야 한다면
// backtrace 테크닉을 사용한다.
	
	static int dp[][], come[][], v[], c[];
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			dp = new int[N + 1][K + 1];
			// come[i][j] := 0 ; i번 물건을 안 쓰는 게 더 좋았다.
			// come[i][j] := 1 ; i번 물건을 쓰는 게 더 좋았다.
			come = new int[N + 1][K + 1];
			v = new int[N + 1];
			c = new int[N + 1];
			ans = 0;
			
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
					come[i][j] = 0;
					
					if (v[i] <= j) {
						// 만약 i'번' 물건이 들어갈 수 있다면 더 큰 가치로 바꾼다						
                        if (dp[i][j] < dp[i - 1][j - v[i]] + c[i]) {
                            dp[i][j] = dp[i - 1][j - v[i]] + c[i];
                            come[i][j] = 1;
                        }
					}
				}
			}
			
			// 사용된 물건번호를 담을 배열 선언
			int[] used = new int[N + 1];
			int j = K; // 현재 가방 용량

			for (int i = N; i >= 1; i--) {
				// 현재 j 만큼의 크기에 대해 사용된 i번 물건 찾기
				if(come[i][j] == 0) {
					used[i] = 0;
				}else {
					used[i] = 1;
					j -= v[i];
				}
			}
            for (int i= 1;i<=N;i++){
                System.out.println(i + "번 물건 사용: " + used[i]);
            }
            System.out.println("#" + tc + " " + dp[N][K]);
		}
	}
}
