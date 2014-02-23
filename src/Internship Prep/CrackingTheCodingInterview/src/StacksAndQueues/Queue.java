package StacksAndQueues;

public class Queue {
	Node front,rear;
	
	void enQ(int data){
		Node temp=new Node(data);
		if(front==null){
			rear=temp;
			front=rear;
		}else{
			rear.next=temp;
			rear=temp;
		}
	}
	
	int deQ(){
		if(front==null){
			System.out.println("Queue is empty");
			return -99;
		}
		int data=front.data;
		front=front.next;
		return data;
	}
	
	void printQ(){
		Node runner=front;
		if(runner==null){
			System.out.println("Q is empty");
			return;
		}
		System.out.print("Front->");
		while(runner!=null){
			System.out.print(runner.data+"->");
			runner=runner.next;
		}
		System.out.println("Rear");
	}
	
	public static void main(String[] args){
		Queue q=new Queue();
		q.printQ();
		q.enQ(10);
		q.enQ(20);
		q.enQ(30);
		q.enQ(40);
		q.enQ(50);
		q.printQ();
		System.out.println("D Q 1:"+q.deQ());
		System.out.println("D Q 2:"+q.deQ());
		System.out.println("D Q 3:"+q.deQ());
		System.out.println("D Q 4:"+q.deQ());
		System.out.println("D Q 5:"+q.deQ());
		System.out.println("D Q 6:"+q.deQ());
		q.printQ();
	}
}
