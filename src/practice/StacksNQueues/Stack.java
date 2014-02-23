
public class Stack {
	Node top;
	int maxStackSize;
	
	Stack(){
		top=null;
		maxStackSize=0;
	}
	
	void push(int data){
		if(maxStackSize==10){
			System.out.println("Stack is full...");
		}else{
			Node temp=new Node();
			temp.setData(data);
			temp.next=top;
			top=temp;
			maxStackSize++;
		}
	}
	
	int pop(){
		if(top==null){
			System.out.println("Stack is empty...");
			return -99;
		}else{
			Node temp=top;
			top=temp.next;
			return temp.data;
		}
	}
	
	void printStack(){
		Node top1=top;
		System.out.print("top");
		while(top1!=null){
			System.out.print("-->");
			System.out.print(top1.data);
			top1=top1.next;
		}
	}
	
	public static void main(String[] args){
		Stack newStack=new Stack();
		newStack.printStack();
		newStack.push(10);
		newStack.push(20);
		newStack.push(30);
		newStack.push(40);
		newStack.push(50);
		newStack.printStack();
		newStack.push(60);
		newStack.push(70);
		newStack.push(80);
		newStack.push(90);
		newStack.push(100);
		newStack.printStack();
		newStack.push(110);
		System.out.println("Starting to Pop");
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		newStack.printStack();
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		System.out.println(newStack.pop());
		newStack.printStack();
		System.out.println(newStack.pop());
	}
}
