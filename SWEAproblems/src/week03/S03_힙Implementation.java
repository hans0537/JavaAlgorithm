package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S03_힙Implementation {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc ++) {
			int N = Integer.parseInt(br.readLine());
			
			
		}
	}
}

// 최대힙 클래스를 정의 
class Heap {
	int[] arr;
	int cnt = 0;
	
	Heap() {
		// 힙을 구현할 배열 (크기 조건을 활용해 적당한 크기로 선언)
		arr = new int[100001];
	}
}
