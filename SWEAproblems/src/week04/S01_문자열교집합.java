package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S01_문자열교집합 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			HashSet<String> setA = new HashSet<>();
			for (int i = 0; i < N; i++) {
				setA.add(st.nextToken());				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				if(setA.contains(st.nextToken())) ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
}
