package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S02_사탕가방 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			long M = Long.parseLong(st.nextToken());
			
			Long[] candy = new Long[N];
			long max = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				candy[i] = Long.parseLong(st.nextToken());
				max = Math.max(max, candy[i]);
			}
			
			long start = 1;
			
			// 가방의 최대개수는 최대개수의 사탕을 한개씩 넣었다고 가정
			long end = max;
			long res = 0;
			
			while(start <= end) {
				// 가방을 mid개 만큼 만들었다고 가정
				long mid = (start + end) / 2;
				
				// 각 가방에 최대 몇개의 사탕을 넣을 수 있는지 계산
				long sum = 0;
				for(int i = 0; i < N; i++) {
					sum += candy[i] / mid;
				}
				
				// 만약 개수가 M보다 작으면 mid 줄이기
				if(sum < M) {
					end = mid - 1;
				}else {
					res = mid;
					start = mid + 1;
				}
			}
			System.out.println("#" + tc + " " + res);
		}
	
	}
}
