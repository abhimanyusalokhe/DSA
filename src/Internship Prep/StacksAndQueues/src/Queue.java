
public class Queue {
	
	Node front;
	Node rear;
	int count;
	
	Queue(){
		front=null;
		rear=null;
		count=0;
	}
	
	void enque(int data){
		
		if(count==10){
			System.out.println("Queue is full");
		}else{
			Node temp=new Node();
			temp.setData(data);
			if(count==0){
				front=rear=temp;
			}else{
				rear.next=temp;
				rear=temp;
			}
			count++;
		}
	}
	
	int dequeue(){
		if(front==rear && front==null){
			System.out.println("Queue is empty");
			return -99;
		}else{
			Node temp=front;
			front=front.next;
			return temp.data;
		}
	}
	
	void printQueue(){
		Node front1=front;
		System.out.print("Front-->");
		while(front1!=null){
			System.out.print("-->");
			System.out.print(front1.data);
			front1=front1.next;
		}
		System.out.print("<--Rear");
	}
	
	public static void main(String[] args){
		Queue newQ=new Queue();
		newQ.printQueue();
		newQ.enque(10);
		newQ.enque(20);
		newQ.enque(30);
		newQ.enque(40);
		newQ.enque(50);
		newQ.printQueue();
		newQ.enque(60);
		newQ.enque(70);
		newQ.enque(80);
		newQ.enque(90);
		newQ.enque(100);
		newQ.printQueue();
		System.out.println("Starting to DeQueue");
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		newQ.printQueue();
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		System.out.println(newQ.dequeue());
		newQ.printQueue();
	}
}
