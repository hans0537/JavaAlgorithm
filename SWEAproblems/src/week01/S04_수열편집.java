package week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S04_수열편집 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int ansIdx = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			LinkedList<Integer> list = new LinkedList<>();
			
			for (int i = 0; i < n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				
				if (tmp.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.add(x, y);
				}else if (tmp.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					list.remove(x);
				} else if (tmp.equals("C")) {
                    int x = Integer.parseInt(st.nextToken()); // 위치
                    int y = Integer.parseInt(st.nextToken()); // 수
                    list.set(x, y);
				}
			}
			
            if (list.size() > ansIdx) {
                System.out.println("#" + tc + " " + list.get(ansIdx));
            } else {
                System.out.println("#" + tc + " " + -1);
            }
		}
	}

}
