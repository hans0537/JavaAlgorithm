package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S01_촛불이벤트 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			
			long start = 1;
			
			// 촛불의 마지막 범위는 (k(k+1))/2 = N 에서 N의 최대범위를 넣고 근을 구하면 되는데 넉넉히 잡음
			long end = 10000000000L;
			long res = 0;
			
			while(start <= end) {
				long mid = (start + end) / 2;
				long k = (mid * (mid + 1)) / 2;
				
				if(k <= N) {
					res = mid;
					start = mid + 1;
				}else if(k > N) {
					end = mid - 1;
				}
			}
			
			if (N != (res * (res + 1)) / 2) res = -1;
			System.out.println("#" + tc + " " + res);
		}
	
	}
}
