package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S02_공통조상 {
	
	// 정점의 정보를 담을 클래스 
	static public class Node {
		// 해당 정점이 가지고 있는 자식들 리스트 
		List<Integer> children;
		int parents;
		
		Node(){
			this.children = new ArrayList<Integer>();
			this.parents = 0;
		}
	}
	
	public static Node[] arr;
	// 타겟 정점의 조상들을 비교하기 위해 담아놓을 리스트 
	static ArrayList<Integer> ancestorv1, ancestorv2;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc ++) {
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			arr = new Node[V + 1];
			ancestorv1 = new ArrayList<Integer>();
			ancestorv2 = new ArrayList<Integer>();
			
			for (int i = 0; i < V + 1; i++) {
				arr[i] = new Node();
			}
			
			String[] tmp = br.readLine().split(" ");
			for (int i = 0; i < E * 2; i+=2) {
				arr[Integer.parseInt(tmp[i])].children.add(Integer.parseInt(tmp[i + 1]));
				arr[Integer.parseInt(tmp[i + 1])].parents = Integer.parseInt(tmp[i]);
			}
			
			findAns(v1, ancestorv1);
			findAns(v2, ancestorv2);
			
			int ans = -1;
			for(int i = 0; i < V; i++) {
				// 최상위 공통 조상 부터 훑어 내려가면서 다를때가 가장 최근 공통조상 
				// O(N)
				if (!ancestorv1.get(i).equals(ancestorv2.get(i))) break;
				ans = ancestorv1.get(i);
			}
			System.out.println("#" + tc + " " + ans + " " + subtree(ans));
		}
	}
	
	public static int subtree(int idx) {
		int res = 1;
		for(int c : arr[idx].children) {
			res += subtree(c);
		}
		return res;
	}
	
	// 최상위 부모 노드부터 순차적으로 밑으로 내려가면서 저장 
	// 나중에 공통조상을 찾기 위함 O(N)
	public static void findAns(int idx, ArrayList<Integer> ancestor) {
		int p = arr[idx].parents;
		if(p != 0) {
			// 부모 노드가 존재하면 계속 찾음 
			findAns(p, ancestor); 
		}
		ancestor.add(idx);
	}
}
