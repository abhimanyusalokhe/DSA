package StacksAndQueues;

public class Stack {
	Node top;
	
	void push(int data){
		Node temp=new Node(data);
		temp.next=top;
		top=temp;
		
	}
	
	int pop(){
		if(top!=null){
			int data=top.data;
			top=top.next;
			return data;
		}
		System.out.println("Stack is empty");
		return -99;
	}
	
	void printStack(){
		
		if(top==null){
			System.out.println("Stack is empty");
			return;
		}
		Node runner=top;
		System.out.println("Top");
		System.out.println("|");
		while(runner!=null){
			System.out.println(runner.data);
			System.out.println("|");
			runner=runner.next;
		}
	}
	
	public static void main(String[] args){
		Stack s = new Stack();
		s.printStack();
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);
		s.push(50);
		s.printStack();
		System.out.println("Pop 1:"+s.pop());
		System.out.println("Pop 2:"+s.pop());
		System.out.println("Pop 3:"+s.pop());
		System.out.println("Pop 4:"+s.pop());
		System.out.println("Pop 5:"+s.pop());
		System.out.println("Pop 6:"+s.pop());
		s.printStack();
		
	}
}
