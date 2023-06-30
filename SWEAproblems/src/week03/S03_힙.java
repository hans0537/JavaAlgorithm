package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class S03_힙 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 기본은 낮은순위 이므로 reverse하여 높은순위로 사용
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for(int tc = 1; tc <= T; tc ++) {
			int N = Integer.parseInt(br.readLine());
			sb.append("#").append(tc).append(" ");
			pq.clear();
			for (int i = 0; i < N; i++) {
				String[] tmp = br.readLine().split(" ");
				if(Integer.parseInt(tmp[0]) == 1) {
					pq.add(Integer.parseInt(tmp[1]));
				}else {
					if(pq.isEmpty()) {
						sb.append("-1").append(" ");
					}else {
						sb.append(pq.poll()).append(" ");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
