package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S01_중위순회 {
	
	static char[] arr;
	static int N;
	static String ans = "";
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new char[N + 1];
			
			for (int i = 1; i <= N; i++) {
				arr[i] = br.readLine().split(" ")[1].charAt(0);
			}
			inorder(1);
			System.out.println("#"+tc+" "+ans);
			ans = "";
		}
	}
	
	// 중위 순회 
	public static void inorder(int cur) {
		// 종료 조건... N개를 넘으면 종료 
		if (cur > N) return;
		
		// 왼쪽 자식 탐색 *2 
		inorder(cur * 2);
		ans += arr[cur];
		// 오른쪽 자식 *2 + 1
		inorder(cur * 2 + 1);
		
		
	}
}
