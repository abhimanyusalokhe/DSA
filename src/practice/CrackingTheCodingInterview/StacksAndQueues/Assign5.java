package StacksAndQueues;

public class Assign5 {
	Stack s1,s2;
	
	Assign5(){
		s1=new Stack();
		s2=new Stack();
	}
	
	void enQ(int data){
		s1.push(data);
	}
	
	int deQ(){
		Node runner=s1.top;
		if(s2.top==null){
			while(runner!=null){
				s2.push(runner.data);
				runner=runner.next;
			}
			s1.top=null;
		}
		return (s2.pop());
		
	}
	
	public static void main(String[] args){
		Assign5 myQ=new Assign5();
		myQ.enQ(10);
		myQ.enQ(20);
		myQ.enQ(30);
		myQ.enQ(40);
		myQ.enQ(50);
		System.out.println("DQ 1:"+myQ.deQ());
		myQ.enQ(60);
		myQ.enQ(70);
		myQ.enQ(80);
		myQ.enQ(90);
		myQ.enQ(100);
		System.out.println("DQ 2:"+myQ.deQ());
		System.out.println("DQ 3:"+myQ.deQ());
		System.out.println("DQ 4:"+myQ.deQ());
		System.out.println("DQ 5:"+myQ.deQ());
		System.out.println("DQ 6:"+myQ.deQ());
		System.out.println("DQ 7:"+myQ.deQ());
		System.out.println("DQ 8:"+myQ.deQ());
		System.out.println("DQ 9:"+myQ.deQ());
		System.out.println("DQ 10:"+myQ.deQ());
		
		
	}
}
