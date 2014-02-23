package com.amazon.test;



/*
 * Approach - The algorithm uses 2 references traversing the linked list. 
 * 				1. singleStep - traverses one node at a time
 * 				2. doubleStep - traverses two nodes at a time
 * 			  If these 2 references meet it means that we have a loop in linked List
 * 			  If they don't and the doubleStep reaches null no loop in linked List
 *               
 * Complexity - Time - O(n) 
 * 				   We travel through the given linked list linearly using 2 references simultaneously. 
 *                 
 *             Space - O(1)
 *             		
 * */

public class ListLoopQuestion {

	
	public static class ListNode {
		public int value;
		public ListNode next;
	}
	
	static ListNode head;
	
	public static boolean hasLoops( ListNode myList ) {
		if (myList == null){
			return false;
		}else{
			// 
			ListNode singleStep = myList;
			ListNode doubleStep = myList;
			
			while(doubleStep != null && doubleStep.next != null){
				singleStep = singleStep.next;
				doubleStep = doubleStep.next.next;
				
				// Are they at same node??  If yes... Its a loop
				if (singleStep == doubleStep){
					return true;
				}		
			}
			// Did not meet... No loop
			return false;
		}
  }
	
	public static void main(String args[]){
		addToList(5);
		addToList(6);
		//addToList(3);
		createLoop();
		
		System.out.println(hasLoops(head));
	}
	
	public static ListNode addToList(int data){
		ListNode temp=new ListNode();
		temp.value=data;
		temp.next=null;
		
		if(head==null){
			head=temp;
		}else{
			ListNode runner=head;
			while(runner.next!=null){
				runner=runner.next;
			}
			runner.next=temp;
		}
		return head;
	}
	
	public static void printList(){
		ListNode runner=head;
		System.out.print("Head->");
		while(runner!=null){
			System.out.print(runner.value+"->");
			runner=runner.next;
		}
		System.out.println();
	}
	
	public static void createLoop(){
		ListNode runner = head;
		while(runner.next != null){
			runner = runner.next;
		}
		
		runner.next = head;
	}

}