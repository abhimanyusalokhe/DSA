
public class SkAQuAssign2 {
	int q[];
	int front;
	int rear;
	
	public SkAQuAssign2(){
		q=new q[10];
		front=0;
		rear=0;
	}
	
	void enQ(int data){
		rear=(rear++)%10;
		if(front==rear){
			System.out.println("Q is full");
			if(front==0){
				rear=9;
			}else{
				rear--;
			}
		}else{
			q[rear]=data;
		}
	}
	
	int dQ(){
		if(front==rear){
			System.out.println("Q is empty");
			return -99;
		}else{
			front=(front++)%10;
			return q[front];
		}
	}
	
	int deleteKthElement(int k){
		
	}
}
