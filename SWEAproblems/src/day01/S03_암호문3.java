package day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S03_암호문3 {
	
	// 초기 링크드리스트 길이
	static int NODE_MAX = 5000;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// 각 노드의 정보를 담을 클래스
	static class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	// 링크드 리스트 선언
	static class LinkedList {
		Node head;
		Node tail;
		Node[] nodeArr;
		// 현재 노드의 개수
		int nodeCnt;
		
		public LinkedList() {
			head = null;
			nodeArr = new Node[NODE_MAX];
			nodeCnt = 0;
		}
		
		Node getNewNode(int data) {
			nodeArr[nodeCnt] = new Node(data);
			return nodeArr[nodeCnt++];
		}
		
		// idx번 부터 삽입하기
		void insert(int idx, int[] nums) {
			int st = 0;
			// 만약 맨 처음이면
			if (idx == 0) {
				if (head != null) {
					Node newNode = getNewNode(nums[0]);
					newNode.next = head;
					head = newNode;
				} else {
				// head가 없는 경우 바로 head로 선언
					head = getNewNode(nums[0]);
				}
				
				idx = 1;
				st = 1;
			}
			
			Node cur = head;
			
			// idx개 만큼 이동하기
			for (int i = 1; i < idx; i++) {
				cur = cur.next;
			}
			
			// cur와 그 다음거 사이에 끼여넣기
			for(int i = st; i < nums.length; i++ ) {
				Node newNode = getNewNode(nums[i]);
				newNode.next = cur.next;
				cur.next = newNode;
				// 현재 노드를 방금 끼어넣은거로 초기화 시켜주기
				cur = newNode;
			}
			
			// 끝인경우
			if (cur.next == null) {
				tail = cur;
			}
		}
		
		void delete(int idx, int cnt) {
			Node cur = head;
			
			if(idx == 0) {
				// 맨 앞을 삭제할 경우 head 최신화
				for(int i=0; i < cnt; i++) {
					cur = cur.next;
				}
				head = cur;
				return;
			}
			
			for (int i = 1; i < idx; i++) {
				cur = cur.next;
			}
			
			// 삭제 전의 위치를 기억하고
			Node anchor = cur;
			// 삭제 할 개수 넘어간뒤
			for (int i = 0; i < cnt; i++) {
				cur = cur.next;
			}
			// 삭제 하고 남은 뒤 것들을 바로 이어주기
			anchor.next = cur.next;
			if (anchor.next == null) {
				tail = anchor;
						
			}
		}
		
		void add(int data) {
			Node cur = tail;
			Node newNode = getNewNode(data);
			cur.next = newNode;
			tail = newNode;
		}
		
        void print() throws Exception {  // 출력하기
            int cnt = 10;
            Node cur = head;
            while(--cnt >=0) {
                bw.write(cur.data +" ");
                cur = cur.next;
            }
        }
	}

	public static void main(String[] args) throws Exception {

        int T = 10;
        
        StringTokenizer stk;
        for (int t = 1; t <= T; t++) {
            LinkedList list = new LinkedList();
            bw.write("#");
            bw.write(t + " ");
            int N = Integer.parseInt(br.readLine());
            int[] initArr = new int[N];
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                initArr[i] = Integer.parseInt(stk.nextToken());
            }
            list.insert(0, initArr);

            int M = Integer.parseInt(br.readLine());
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                char cmd = stk.nextToken().charAt(0);
                int x, y;
                switch (cmd) {
                    case 'I':
                        x = Integer.parseInt(stk.nextToken());
                        y = Integer.parseInt(stk.nextToken());
                        int[] temp = new int[y];
                        for (int j = 0; j < y; j++) {
                            temp[j] = Integer.parseInt(stk.nextToken());
                        }
                        list.insert(x, temp);
                        break;
                    case 'D':
                        x = Integer.parseInt(stk.nextToken());
                        y = Integer.parseInt(stk.nextToken());
                        list.delete(x, y);
                        break;
                    case 'A':
                        y = Integer.parseInt(stk.nextToken());
                        for (int j = 0; j < y; j++) {
                            list.add(Integer.parseInt(stk.nextToken()));
                        }
                        break;
                }
            }
            list.print();
            bw.write("\n");
        }
        bw.flush();
        br.close();
        bw.close();
		
		
	}

}
