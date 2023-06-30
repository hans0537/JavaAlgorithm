package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S03_힙Implementation {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc ++) {
			int N = Integer.parseInt(br.readLine());
			Heap heap = new Heap();
			StringBuilder res = new StringBuilder();
			for (int i = 0; i < N; i++) {
				String[] tmp = br.readLine().split(" ");
				if(Integer.parseInt(tmp[0]) == 1) {
					heap.add(Integer.parseInt(tmp[1]));
				}else {
					if(heap.isEmpty()) {
						res.append(-1).append(" ");
					}else {
						res.append(heap.poll()).append(" ");
					}
				}
			}
			System.out.println("#" + tc + " " + res);
		}
	}
}

// 최대힙 만들기
class Heap {
	int[] arr;
	int cnt = 0;
	
	Heap() {
		// 힙을 구현할 배열 (크기 조건을 활용해 적당한 크기로 선언)
		arr = new int[100001];
	}
	
	// 데이터 삽입 과정 
	void add(int data) {
		// 1번부터 데이터를 넣는다.
		arr[++cnt] = data;
		int now = cnt;
		while (now > 1) {
			int parent = getParent(now);
			// 만약 현재 입력 받은 데이터가 부모보다 크면 서로 위치를 바꾸고
			// 현재 위치를 부모로 변경
			if(arr[now] > arr[parent]) {
				int temp = arr[parent];
				arr[parent] = arr[now];
				arr[now] = temp;
				now = parent;
			} else {
				break;
			}
		}
	}
	
	// 삭제후 정렬하기 위함
	void heapify() {
		int now = 1;
		while(getRight(now) <= cnt) {
			int larger = now;
			int left = getLeft(now);
			int right = getRight(now);
			if(arr[left] > arr[larger]) {
				larger = left;
			}
			if(arr[right] > arr[larger]) {
				larger = right;
			}
			if(larger != now) {
				int temp = arr[now];
				arr[now] = arr[larger];
				arr[larger] = temp;
				now = larger;
			}else {
				break;
			}
		}
	}
	
	int poll() {
		int max = arr[1];
		// 맨 마지막거를 일단 root로 옮김
		arr[1] = arr[cnt];
		arr[cnt] = 0;
		cnt--;
		heapify();
		return max;
	}
	
	boolean isEmpty() {
		return cnt == 0;
	}
	
	// 완전 이진트리의 특성을 활용 부모 = 자식 / 2 
	int getParent(int child) {
		return child / 2;
	}
	
	int getRight(int parent) {
		return parent * 2 + 1;
	}
	
	int getLeft(int parent) {
		return parent * 2;
	}
}
