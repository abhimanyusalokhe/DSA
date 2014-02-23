package StacksAndQueues;

import java.util.ArrayList;

public class Assign3 {
	
	ArrayList<Stack> stacks;
	Stack current;
	int threshold;
	
	Assign3(){
		stacks=new ArrayList<Stack>();
		current=new Stack();
	}
	void switchToNewStack(){
		stacks.add(current);
		current=new Stack();
	}
	
	void push(int data){
		if(threshold>=10){
			switchToNewStack();
			threshold=0;
		}
		current.push(data);
		threshold++;
	}
	
	int pop(){
		int data=current.pop();
		if(data==-99){
			if(stacks.size()>0){
				current=stacks.get(stacks.size()-1);
				data=current.pop();
			}else{
				System.out.println("Stack is empty");
			}
		}
		return data;
	}
	
	void printStack(){
		Stack runner=current;
		while(runner!=null){
			
		}
		for(int i=0;i<stacks.size()+1;i--){
			runner=stacks.get(i);
			Node tempTop=runner.top;
			while(tempTop!=null){
				System.out.print(tempTop.data+"-");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Assign3 st=new Assign3();
		st.printStack();
//		pushTenElements(st,1);
//		st.printStack();
//		pushTenElements(st,2);
//		st.printStack();
//		pushTenElements(st,3);
//		st.printStack();
//		for(int i=0;i<17;i++){
//			st.pop();
//		}
//		st.printStack();
	}
	
	static void pushTenElements(Assign3 st,int n){
		for(int i=(10+((n-1)*100));i<=100*n;i+=10){
			st.push(i);
		}
	}
}
