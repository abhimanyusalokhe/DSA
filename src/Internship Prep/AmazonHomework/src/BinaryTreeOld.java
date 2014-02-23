import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;


public class BinaryTreeOld {
	
	Node root;
	
	public class Node{
		int data;
		Node left;
		Node right;
		
		public Node(int data){
			this.data=data;
		}
	}
	
	public BinaryTreeOld(){
		root=null;
		
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
	
	
	public void saveTreeToArray(){
		if(root!=null){
			System.out.println("Tree Height="+getTreeHeight(this.root));
			int arraySize=(int) Math.pow(2, getTreeHeight(this.root))-1;
			System.out.println("Array size="+arraySize);
			int[] array=new int[arraySize];
			
			array[0]=(char)root.data;
			LinkedList<Node> list=new LinkedList<Node>();
			list.add(root);
			int index=0;
			int data=-99;
			while(list.size()>0){
				Node temp=list.remove();
				if(temp!=null){
					if(temp.left!=null){
						data=temp.left.data;
						list.add(temp.left);
					}else{
						data=-99;
					}
					array[index*2+1]=data;
					
					if(temp.right!=null){
						data=temp.right.data;
						list.add(temp.right);
					}else{
						data=-99;
					}
					array[index*2+2]=data;
					
					
				}
				index++;
			}
			
			
			File file = new File("BinaryTree.txt");
            FileOutputStream fos;
            FileInputStream fis;
			try {
				fos = new FileOutputStream(file);
		        DataOutputStream dos = new DataOutputStream(fos);
	            for (int i=0;i<array.length;i++) {
	            	System.out.print(array[i]);
	            	dos.writeInt(array[i]);
	            }
	            dos.close();
	            
	            fis=new FileInputStream(file);
	            DataInputStream dis=new DataInputStream(fis);
	            int[] array2=new int[arraySize];
	            int i=0;
	            while(true){
	            	array2[i++]=dis.readInt();
	            }
	           
	            
			} catch (FileNotFoundException e) {
			} catch (IOException e){
			} catch (Exception ex){
			}
		}
		
		
		
	}
	
	public void  deserializeTree(){
		File file = new File("BinaryTree.txt");
        FileOutputStream fos;
        FileInputStream fis;
		try {
			fis=new FileInputStream(file);
            DataInputStream dis=new DataInputStream(fis);
            int[] array2=new int[arraySize];
            int i=0;
            while(true){
            	array2[i++]=dis.readInt();
            }
           
            
		} catch (FileNotFoundException e) {
		} catch (IOException e){
		} catch (Exception ex){
		}
	}
	
	
	
	public int getTreeHeight(Node node){
		if(node==null)
			return 0;
		else
			return Math.max(getTreeHeight(node.left),getTreeHeight(node.right))+1;
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
	
	public static void main(String[] args){
		BinaryTreeOld myTree=new BinaryTreeOld();
		
		Node root=myTree.setRoot(20);
		
		Node n1=myTree.setLeftChild(root, 23);
		myTree.setLeftChild(n1, 1);
		Node n2=myTree.setRightChild(n1, 9);
		myTree.setRightChild(n2, 12);
		
		n1=myTree.setRightChild(root, 24);
		n2=myTree.setRightChild(n1, 8);
		n1=myTree.setLeftChild(n2, 12);
		myTree.setRightChild(n1, 20);
		
		
		myTree.inorderTraversal(myTree.root);
		myTree.saveTreeToArray();
	}
	
	
}
