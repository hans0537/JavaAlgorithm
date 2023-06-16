package week01;

public class S05_병사관리 {

	public class Node{
		// 병사의 id
		int id;
		// 각 병사들의 version 관리
		// 이 data로 인해 링크드리스트에 있는 병사를 일일히 찾아 삭제 안해도 됨
		// 병사들의 버전들만 변경하여 현재 버전하고 일치하면 최신 버전이고 아니면 이미 삭제되었거나 변경 이전의 병사이다.
		int v;
		Node next;
		
		Node() {}
		
		Node(int id, int v) {
			this.id = id;
			this.v = v;
			this.next = null;
		}
		
		Node(int id, int v, Node next) {
			this.id = id;
			this.v = v;
			this.next = next;
		}
	}
	
	public Node[] node = new Node[200055];
	public int cnt = 0;
	// 이 verison 배열이 현재 병사(id)의 최신 버전을 담고 있는 배열
	public int[] version = new int[200055];
	// 병사(id)가 소속된 팀 번호 
	public int[] num = new int[100055];
	
	
	public Node getNewNode(int id, Node next) {
		Node cur = node[cnt++];
		cur.id = id;
		cur.next = next;
		// 생성 할때마다 버전 업 (update 할때 버전업 해주기 위함)
		cur.v = ++version[id];
		
		return cur;
	}
	
	public class Team{
		// 1 ~ 5 팀까지 있으므로 
		Node[] head = new Node[6];
		Node[] tail = new Node[6];
	}
	
	public Team[] t = new Team[6];
	
	public void init() {
		// 정점의 개수 초기화
		cnt = 0;

		// 빈 정보를 넣는 작업, 더미데이터 삽입 
		for (int i = 0; i < 200055; i++) {
			if(node[i] == null) node[i] = new Node();
		}
		
		for (int i = 1; i <= 5; i++) { // 각 팀마다 
			t[i] = new Team();
			for (int j = 1; j <= 5; j++) { // 각 스코어 정보를 넣는다 
				t[i].tail[j] = t[i].head[j] = getNewNode(0, null);
			}
		}
		
		for (int i = 0; i < 100000; i++) {
			version[i] = 0;
			num[i] = 0;
		}
	}
	
	// 맨뒤에 이어붙이기만 하면 되므로 O(1) 시간복잡도 
	public void hire(int mID, int mTeam, int mScore) {
		Node newNode = getNewNode(mID, null);
		// 해당 팀의 해당 점수 링크드리스트 맨뒤에 이어 붙인다.
		t[mTeam].tail[mScore].next = newNode;
		System.out.println(t[mTeam].tail[mScore]);
		System.out.println(t[mTeam].tail[mScore].next);
		
		t[mTeam].tail[mScore] = newNode;
		System.out.println(t[mTeam].tail[mScore]);
		System.out.println(t[mTeam].tail[mScore].next);
		num[mID] = mTeam;
	}
	
	// O(1)
	public void fire(int mID) {
		// 현재 버전을 -1로 바꾸어 해고시킴  
		version[mID] = -1;
	}
	
	// update는 그냥 다시 새로 고용 하면 됨 O(1) 
	public void updateSoldier(int mID, int mScore) {
		hire(mID, num[mID], mScore);
	}
	
	// 해당 팀의 점수를 일괄적으로 모두 변경 O(1) 
	public void updateTeam(int mTeam, int mChangeScore) {
		if (mChangeScore < 0) {	// 감소 하는 작업 
			for (int i = 1; i <= 5; i++) { // 1점인 애들부터 증가를 시켜서 k 점인 애들 뒤에 붙인다 
				int k = i + mChangeScore;
				k = k < 1 ? 1 : (k > 5 ? 5 : k);
				if(i == k) continue;
				
				if(t[mTeam].head[i].next == null) continue;
				t[mTeam].tail[k].next = t[mTeam].head[i].next;
				t[mTeam].tail[k] = t[mTeam].tail[i];
				t[mTeam].head[i].next = null;
				t[mTeam].tail[i] = t[mTeam].head[i];
			}
		}
		
		if (mChangeScore > 0) {	// 증가 하는 작업 
			for (int i = 5; i >= 1; i--) {
				int k = i + mChangeScore;
				k = k < 1 ? 1 : (k > 5 ? 5 : k);
				if(i == k) continue;
				
				if(t[mTeam].head[i].next == null) continue;
				t[mTeam].tail[k].next = t[mTeam].head[i].next;
				t[mTeam].tail[k] = t[mTeam].tail[i];
				t[mTeam].head[i].next = null;
				t[mTeam].tail[i] = t[mTeam].head[i];
			}
		}
	}
	
	public int bestSoldier(int mTeam) {
		for (int i = 5; i >= 1; i--) {
			Node node = t[mTeam].head[i].next;
			
			if(node == null) continue;
			
			int ans = 0;
			while(node != null) {
				if(node.v == version[node.id]) {
					ans = ans < node.id? node.id : ans;
				}
				node = node.next;
			}
			
			if (ans != 0) return ans;
		}
		return 0;
	}
}
