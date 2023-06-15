package day01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S01_불면증치료법 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			int num = Integer.parseInt(br.readLine());
			
			boolean check[] = new boolean[10];
			int cnt = 0;
			
			int x = 1;
			while (true) {
				int tmp = num * x;			
				while (tmp > 0) {
					if (!check[tmp % 10]) {
						check[tmp % 10] = true;
						cnt ++;
					}
					tmp = tmp / 10;
				}
				
				if (cnt == 10) {
					break;
				}
				x ++;
			}
			System.out.println("#"+i+" "+num*x);
		}
	}

}
