package LinkedLists;

public class ListManipulation {
	Node head;
	
	public ListManipulation(){
		head=null;
	}
	
	public Node addToList(int data){
		Node temp=new Node();
		temp.data=data;
		temp.next=null;
		
		if(head==null){
			head=temp;
		}else{
			Node runner=head;
			while(runner.next!=null){
				runner=runner.next;
			}
			runner.next=temp;
		}
		return head;
	}
	
	public Node createCLL(int data){
		Node runner=head;
		Node tail=head;
		while(tail.next!=null){
			tail=tail.next;
		}
		while(runner.data!=data){
			runner=runner.next;
		}
		tail.next=runner;
		
		return head;
	}
	
	public Node deleteFromList(int data){
		boolean deleted=false;
		Node runner=head;
		Node previous=null;
		while(runner!=null){
			if(runner.data==data){
				if(runner==head){
					head=head.next;
				}else{
					previous.next=runner.next;
				}
				deleted=true;
			}
			previous=runner;
			runner=runner.next;
		}
		if(deleted){
			System.out.println("Node deleted from the list");
		}else{
			System.out.println("Node not found in the list");
		}
		
		return head;
	}
	
	public void printList(){
		Node runner=head;
		System.out.print("Head->");
		while(runner!=null){
			System.out.print(runner.data+"->");
			runner=runner.next;
		}
		System.out.println();
	}
}
