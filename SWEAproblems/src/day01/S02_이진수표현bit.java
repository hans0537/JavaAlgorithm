package day01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S02_이진수표현bit {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 비트 연산으로 0 ~ 9 를 비트 배열로 생각
			// 즉 0 ~ 9 가 1로 채워지면 1023
			int check = 0;
			
			int x = 1;
			while (true) {
				int tmp = num * x;			
				while (tmp > 0) {
					// 각각의 자리수를 현재 check와 or 연산으로 1을 덮어 씌운다
					check = check | (1 << (tmp % 10));
					tmp = tmp / 10;
				}
				
				// 만약 check가 0 ~ 9 가 1 이면(1023) 종료
				if (check == 1023) {
					break;
				}
				x ++;
			}
			System.out.println("#"+i+" "+num*x);
		}
	}

}
