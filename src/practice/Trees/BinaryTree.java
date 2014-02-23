import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;


public class BinaryTree {
	public Node root;
	
	public static class Node{
		int data;
		Node left;
		Node right;
		
		Node(int newData){
			data=newData;
		}
	}
	
	BinaryTree(){
		root=null;
	}
	
	public void recInsert(int newData){
		root=recursiveInsert(root,newData);
	}
	
	private Node recursiveInsert(Node node, int data){
		if(node==null){
			node=new Node(data);
		}else{
			if(data<=node.data){
				node.left=recursiveInsert(node.left,data);
			}else if(data>node.data){
				node.right=recursiveInsert(node.right,data);
			}
		}
		return node;
	}
	
	public Node iterativeInsert(Node root,int data){
		Node runner=root;
		Node parent=null;
		while(runner!=null){
			parent=runner;
			if(data<=runner.data)
				runner=runner.left;
			else if(data>runner.data)
				runner=runner.right;
		}
		Node newNode=new Node(data);
		if(parent==null){
			this.root=newNode;
		}else{
			if(parent.data>=data){
				parent.left=newNode;
			}else if(parent.data<data){
				parent.right=newNode;
			}
		}
		return this.root;
		
	}
	
	public boolean lookUp(Node root,int data){
		Node runner=root;
		if(root==null){
			return false;
		}else{
			if(runner.data==data){
				return true;
			}else{
				if(runner.data>data){
					return lookUp(runner.left,data);
				}else{
					return lookUp(runner.right,data);
				}
			}
		}
	}
	
	public boolean iterativeLookUp(Node root,int data){
		Node runner=root;
		boolean flag=false;
		
		while(runner!=null){
			if(runner.data==data){
				flag=true;
				break;
			}else if(runner.data>data){
				runner=runner.left;
			}else{
				runner=runner.right;
			}
		}
		return flag;
	}
	
	public int size(Node root){
		if(root==null) return(0);
		else{
			return (size(root.left)+1+size(root.right));
		}
	}
	
	public int height(Node root){
		if(root==null) return(0);
		else{
			return (Math.max(height(root.left),height(root.right))+1);
		}
	}
	
	public void inorderTraversal(Node root){
		if(root==null)
			return;
		else{
			inorderTraversal(root.left);
			System.out.println(" "+root.data);
			inorderTraversal(root.right);
		}
	}
	
	public void preorderTraversal(Node root){
		if(root==null)
			return;
		else{
			System.out.println(" "+root.data);
			preorderTraversal(root.left);
			preorderTraversal(root.right);
		}
	}
	
	public void postorderTraversal(Node root){
		if(root==null)
			return;
		else{
			postorderTraversal(root.left);
			postorderTraversal(root.right);
			System.out.println(" "+root.data);
		}
	}
	
	public void bfs(Node root){
		LinkedList<Node> list=new LinkedList<Node>();
		
		list.add(root);
		Node runner;
		while(list.size()>0){
			runner=list.remove();
			System.out.println(runner.data);
			
			if(runner.left!=null)
				list.add(runner.left);
			if(runner.right!=null)
				list.add(runner.right);
			
			
		}
	}
	
	// Iterative Pre-order
	public void DFS(Node root){
		Stack<Node> stack=new Stack<Node>();
		stack.push(root);
		Node tmp;
		System.out.println();
		while(!stack.isEmpty()){
			tmp=stack.pop();
			System.out.print(tmp.data);
			
			if(tmp.right!=null){
				stack.push(tmp.right);
			}
			if(tmp.left!=null){
				stack.push(tmp.left);
			}
			
		}
		System.out.println();
	}
	
	//Iterative in-order
	public void iterativeInorder( ) {
	    Node node = root;
	    Stack<Node> stack = new Stack<Node>( );
	    while( ! stack.isEmpty( ) || node != null ) {
	        if( node != null ) {
	            stack.push( node );
	            node = node.left;
	        } else {
	            node = stack.pop( );
	            System.out.print( node.data + " " );
	            node = node.right;
	        }
	    }
	}
	
	//Iterative Post-order
	public void iterativePostorder( ) {
	    if( root == null ) return;
	 
	    Stack<Node> stack = new Stack<Node>( );
	    Node current = root;
	 
	    while( true ) {
	 
	        if( current != null ) {
	            if( current.right != null ) stack.push( current.right );
	            stack.push( current );
	            current = current.left;
	            continue;
	        }
	 
	        if( stack.isEmpty( ) ) return;
	        current = stack.pop( );
	 
	        if( current.right != null && ! stack.isEmpty( ) && current.right == stack.peek( ) ) {
	            stack.pop( );
	            stack.push( current );
	            current = current.right;
	        } else {
	            System.out.print( current.data + " " );
	            current = null;
	        }
	    }
	}
	
	
	
	public boolean hasPathSum(Node root,int sum){
		if(root==null)
			return (sum==0);
		else{
			int subsum=sum-root.data;
			return (hasPathSum(root.left,subsum) || hasPathSum(root.right,subsum));
		}
	}
	
	public void printArray(int[] paths,int len){
		for(int i=0;i<len;i++){
			System.out.print(" "+paths[i]);
		}
		System.out.println();
	}
	
	public void printPaths(Node root,int[] paths,int len){
		if(root==null)
			return;
		
		paths[len++]=root.data;
		
		if(root.left==null && root.right==null){
			printArray(paths,len);
		}else{
			printPaths(root.left,paths,len);
			printPaths(root.right,paths,len);
		}
	}
	
	
	public void findMirror(Node node){
		mirror(node);
		inorderTraversal(node);
	}
	
	private void mirror(Node node){
		if(node!=null){
			mirror(node.left);
			mirror(node.right);
			
			Node temp=node.left;
			node.left=node.right;
			node.right=temp;
		}
	}

	public boolean sameTree(Node a,Node b){
		if(a==null && b==null)
			return true;
		else if(a!=null && b!=null){
			return(a.data==b.data && sameTree(a.left,b.left) && sameTree(a.right,b.right));
		}else{
			return false;
		}
	}
	
	public int leastCommonAncestor(Node node,int n1,int n2){
		if(node==null){
			return -99;
		}else if(n1< node.data && n2<node.data){
			return leastCommonAncestor(node.left,n1,n2);
		}else if(n1 > node.data && n2 > node.data){
			return leastCommonAncestor(node.right,n1,n2);
		}else{
			return node.data;
		}
	}
	
	public Node leastCommonAncestorBT(Node node,Node p,Node q){
		if(isDescendant(node.left,p) && isDescendant(node.left,q))
			return leastCommonAncestorBT(node.left, p, q);
		if(isDescendant(node.right,p)&& isDescendant(node.right, q))
			return leastCommonAncestorBT(node.right, p, q);
		return node;
	}
	
	boolean isDescendant(Node root,Node n){
		if(root==null) return false;
		else if(root==n) return true;
		return isDescendant(root.left,n) || isDescendant(root.right,n);
	}
	
	
	public boolean isBST(Node root,boolean flag){
		if(root==null){
			
		}else{
			isBST(root.left,flag);
			if((root.left!=null && root.data<root.left.data)||(root.right!=null && root.data>root.right.data)){
				System.out.println("Not a BST");
				flag=false;
			}
			isBST(root.right,flag);
		}
		return flag;
		
	}
	
	public int minDepth(Node root){
		if(root==null)
			return 0;
		else{
			return Math.min(minDepth(root.left), minDepth(root.right))+1;
		}
	}
	
	public int maxDepth(Node root){
		if(root==null)
			return 0;
		else{
			return Math.max(minDepth(root.left), minDepth(root.right))+1;
		}
	}
	
	public boolean isBalanced(Node root){
		return (maxDepth(root)-minDepth(root)<=1);
	}
	
	public Node sortedArrayToBinaryTree(int[] array,int start,int end){
		if(end<start)
			return null;
		else{
			int mid=(start+end)/2;
			Node newNode=new Node(array[mid]);
			newNode.left=sortedArrayToBinaryTree(array, start, mid-1);
			newNode.right=sortedArrayToBinaryTree(array, mid+1, end);
			return newNode;
		}
		
	}
	
	public void levelWiseLinkedLists(Node root){
		LinkedList<Node> list=new LinkedList<Node>();
		ArrayList<LinkedList<Node>> master=new ArrayList<LinkedList<Node>>();
		int level=0;
		list.add(root);
		Node tmp;
		master.add(level,list);
		while(true){
			list=new LinkedList<Node>();
			for(int i=0;i<master.get(level).size();i++){
				tmp=master.get(level).get(i);
				if(tmp!=null){
					if(tmp.left!=null){
						list.add(tmp.left);
					}
					if(tmp.right!=null){
						list.add(tmp.right);
					}
				}
			}
			if(list.size()>0){
				master.add(level+1,list);
			}else{
				break;
			}
			level++;
			
		}
		printLists(master);
		
	}
	
	private void printLists(ArrayList<LinkedList<Node>> master){
		LinkedList<Node> list;
		System.out.println();
		for(int i=0;i<master.size();i++){
			list=(LinkedList<Node>)master.get(i);
				System.out.print("Linked List level "+i+"->");
			for(int j=0;j<list.size();j++){
				System.out.print(list.get(j).data);
			}
			System.out.println();
		}
	}
	
	public void findPreviousInInorder(Node root,Node previous,int data){
		if(root==null)
			return;
		else{
			findPreviousInInorder(root.left,root,data);
			if(previous!=null && previous.data==data){
				System.out.println("Next element is:"+root.data);
			}
			findPreviousInInorder(root.right,root,data);
		}
	}
	
	public void printPathsFromNode(Node root,int[] paths,int len,int data){
		if(root==null)
			return;
		
		paths[len++]=root.data;
		
		if(root.data==data){
			printArray(paths,len);
			return;
		}else{
			printPathsFromNode(root.left,paths,len,data);
			printPathsFromNode(root.right,paths,len,data);
		}
	}
	
	
}
