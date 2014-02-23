import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;




public class BinaryTreeSerialization {
	
	Node root;
	
	public class Node{
		int data;
		Node left;
		Node right;
		
		public Node(int data){
			this.data=data;
		}
	}
	
	public Node setRoot(int data){
		this.root=new Node(data);
		return root;
	}
	public Node setRightChild(Node node,int data){
		node.right=new Node(data);
		return node.right;
	}
	
	public Node setLeftChild(Node node,int data){
		node.left=new Node(data);
		return node.left;
	}
	
	void writeBinaryTree(Node node,DataOutputStream dos) throws IOException {
		  if (node==null){
		    dos.writeChar('/');
		  } else {
			  dos.writeChar((char)node.data);
		    writeBinaryTree(node.left,dos);
		    writeBinaryTree(node.right,dos);
		  }
	}
	
	 Node readBinaryTree(Node node, DataInputStream dis) throws IOException {
		  char token=dis.readChar();
		  System.out.println("Token is="+token);
		  if (!isNumber(token))
		    return null;
		  if(isNumber(token)){
		    node = new Node(token);
		    readBinaryTree(node.left, dis);
		    readBinaryTree(node.right, dis);
		  }
		  return node;
	}
	 
	public boolean isNumber(char c){
		try{
			Integer.parseInt(String.valueOf(c));
		}catch(NumberFormatException ex){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		BinaryTreeSerialization myTree=new BinaryTreeSerialization();
		
//		Node root=myTree.setRoot(20);
//		
//		Node n1=myTree.setLeftChild(root, 23);
//		myTree.setLeftChild(n1, 1);
//		Node n2=myTree.setRightChild(n1, 9);
//		myTree.setRightChild(n2, 12);
//		
//		n1=myTree.setRightChild(root, 24);
//		n2=myTree.setRightChild(n1, 8);
//		n1=myTree.setLeftChild(n2, 12);
//		myTree.setRightChild(n1, 20);
//		
//		
//		File file = new File("BinaryTree.txt");
//		
//		try{
//			FileOutputStream fos = new FileOutputStream(file);
//	        DataOutputStream dos = new DataOutputStream(fos);
//	        myTree.writeBinaryTree(root,dos);
//		}catch(FileNotFoundException ex){}
//		catch(IOException ex){}
//		
		File file = new File("BinaryTree.txt");
		
		try{
			FileInputStream fis = new FileInputStream(file);
	        DataInputStream dis = new DataInputStream(fis);
	        myTree.root=myTree.readBinaryTree(myTree.root, dis);
	        System.out.println(myTree.root.data);
	        myTree.inorderTraversal(myTree.root);
		}catch(FileNotFoundException ex){}
		catch(IOException ex){}
		
		
		
	}
	
	public void inorderTraversal(Node node){
		if(node==null)
			return;
		else{
			inorderTraversal(node.left);
			System.out.println(" "+node.data);
			inorderTraversal(node.right);
		}
	}
	
}
