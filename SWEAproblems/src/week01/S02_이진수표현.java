package week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S02_이진수표현 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // N자리가 1을 확인 하려면 1을 N번 시프트한후 1을 빼면
            // 1이 N개 된다
            // 그 상태에서 M 과 or 연산하고 M자리 그대로면 1이 그대로 있었다
            // 아니면 0이 있었으므로 OFF
            if((M | ((1 << N) - 1)) == M) {
            	System.out.println("#"+tc+" ON");
            }else {
            	System.out.println("#"+tc+" OFF");            	
            }
		}
	}

}
