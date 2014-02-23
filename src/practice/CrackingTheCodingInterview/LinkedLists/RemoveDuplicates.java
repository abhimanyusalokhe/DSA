package LinkedLists;

public class RemoveDuplicates {
	
	public static void removeDuplicatesFromListWOBuffer(ListManipulation list){
		Node head=list.head;
		
		Node runner;
		Node current=head.next;
		Node previous=head;
		boolean duplicate=false;
		while(current!=null){
			runner=head;
			System.out.println("Current node="+current.data);
			System.out.println("Previous node="+previous.data);
			while(runner!=current){
				if(runner.data==current.data){
					duplicate=true;
					break;
				}
				runner=runner.next;
			}
			if(duplicate){
				previous.next=current.next;
				System.out.println("duplicate");
			}else{
				previous=current;
			}
			current=current.next;
		}
	}
	
	public static void findNthToLastNode(ListManipulation list, int n){
		Node head=list.head;
		Node rider=head;
		Node pillion=head;
		
		while(n>1){
			rider=rider.next;
			n--;
		}
		
		while(rider.next!=null){
			rider=rider.next;
			pillion=pillion.next;
		}
		System.out.println(pillion.data);
	}
	
	public static ListManipulation sumof2LinkedList(ListManipulation list1, ListManipulation list2){
		ListManipulation resultList=new ListManipulation();
		Node head1=list1.head;
		Node head2=list2.head;
		int val1=0,val2=0,carry=0,result=0;
		
		while(head1 !=null || head2 !=null){
			if(head1==null){
				val1=0;
			}else{
				val1=head1.data;
				head1=head1.next;
			}
			if(head2==null){
				val2=0;
				
			}else{
				val2=head2.data;
				head2=head2.next;
			}
			System.out.println("Val1="+val1);
			System.out.println("Val2="+val2);
			System.out.println("Carry="+carry);
			
			result=val1+val2+carry;
			System.out.println("Result="+result);
			
			carry=result/10;
			result=result%10;
			
			System.out.println("New Carry="+carry);
			System.out.println("New Result="+result);
			resultList.addToList(result);
			
			
			
		}
		
		
		return resultList;
	}
	
	public static void printBeginningNodeOfCLL(ListManipulation list){
		
		Node head=list.head;
		Node fast=head;
		Node slow=head;
		
		while(fast.next!=null){
			fast=fast.next.next;
			slow=slow.next;
			if(fast==slow){
				break;
			}
			
		}
		if(fast.next==null){
			System.out.println("No Loop");
		}else{
			slow=head;
			while(fast!=slow){
				fast=fast.next;
				slow=slow.next;
			}
			System.out.print("Beginning="+slow.data);
		}
	}
	
	public static void main(String[] args){
		ListManipulation list=new ListManipulation();
		list.printList();
		list.addToList(20);
		list.addToList(12);
		list.addToList(34);
		list.addToList(22);
		list.addToList(88);
		list.addToList(23);
		list.addToList(1);
		list.addToList(22);
		list.addToList(87);
		list.addToList(34);
		list.addToList(34);
		list.addToList(34);
		list.printList();
		removeDuplicatesFromListWOBuffer(list);
		list.printList();
		
		findNthToLastNode(list, 4);
		ListManipulation list2=new ListManipulation();
		ListManipulation list1=new ListManipulation();
		list1.addToList(3);
		list1.addToList(1);
		list1.addToList(5);
		list2.addToList(5);
		list2.addToList(9);
		//list2.addToList(2);
		ListManipulation result=sumof2LinkedList(list1, list2);
		result.printList();
		
		ListManipulation CLlist=new  ListManipulation();
		CLlist.addToList(12);
		CLlist.addToList(2);
		CLlist.addToList(23);
		CLlist.addToList(92);
		CLlist.addToList(55);
		CLlist.createCLL(12);
		
		printBeginningNodeOfCLL(CLlist);
	}
}
