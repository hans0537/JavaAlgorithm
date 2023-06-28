package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S01_최장공통부분수열 {
	
	// 두 문자열이 각각 1000 이하의 자연수 인데 모든 문자열의 수열을 비교하려면 2^1000 * 2^1000 의 시간복잡도가 걸리니
	// 이전 거를 활용할 수 있는 DP를 사용한다. 
	// 1 ~ N, 1 ~ M 이면 각각 i,j 라고 생각하고 dp[i][j] 는 A의 1 ~ i 와 B의 1 ~ j 길이에서 LCM길이 이다.
	static int dp[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			
			dp = new int[s1.length() + 1][s2.length() + 1];
			for (int i = 1; i <= s1.length(); i++) {
				for (int j = 1; j <= s2.length(); j++) {
					// 만약 현재 위치에서 맨뒤에 문자가 s1, s2 같으면 이전 최장길이수열에 + 1만 하면 된다.
					if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}else {
						// 만약 끝에 자리가 다르면 이전거에서 최신 LCM길이를 가져오는데
						// s1 하나 전거, s2 하나 전거 에서 가장 큰 LCM을 저장해준
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					}
				}
			}
			System.out.println("#" + tc + " " + dp[s1.length()][s2.length()]);
		}
	}
}
