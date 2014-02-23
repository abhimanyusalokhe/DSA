import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class BinaryTree {
	TreeNode root;
	
	public class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int data){
			this.data=data;
		}
	}
	

    public BinaryTree(){
		root=null;
	}
    
    
    /*
     * Methods to help create the Binary Tree
     * */
    public TreeNode setRoot(int data){
		this.root=new TreeNode(data);
		return root;
	}
	public TreeNode setRightChild(TreeNode node,int data){
		node.right=new TreeNode(data);
		return node.right;
	}
	
	public TreeNode setLeftChild(TreeNode node,int data){
		node.left=new TreeNode(data);
		return node.left;
	}
	
	/* Function name: serialize
	 * Input param	: OutputStream, TreeNode
	 * Returns		: void
	 * Description	: Processes tree and writes bytes into file specified by the output stream
	 * */
	public  void serialize( final OutputStream outStr, TreeNode tNode ) 
			throws IOException{
       if( tNode==null ) {
            outStr.write("()".getBytes());
        } else {
            outStr.write("(".getBytes());
            outStr.write((""+tNode.data).getBytes());
            serialize(outStr,tNode.left);
            serialize(outStr,tNode.right);
            outStr.write(")".getBytes());
        }
	}
	    
	/* Function name: deserialize
	 * Input param	: BufferedInputStream
	 * Returns		: TreeNode root
	 * Description	: Processes inputstream from file and creates a Binary Tree.
	 * */
    public  TreeNode deserialize(BufferedInputStream bisStr) 
    		throws IOException{
        if( bisStr.available()>0){
            char currChar=(char)bisStr.read();
 
            if(currChar=='('){
                if(bisStr.available()>0){
                    currChar=(char)bisStr.read();
                    if(currChar==')'){
                        return null; // Returns null if empty parenthesis found
                    }
                    StringBuffer sb = new StringBuffer(); //builds the integer for the data of the node
                    while(currChar!=')' && currChar!='('){
                        sb.append( currChar );
                        if( bisStr.available()>0){
                            bisStr.mark(1);
                            currChar=(char)bisStr.read();
                        }else{
                            break;
                        }
                    }
                    
                    //Creates new node for the integer value
                    TreeNode newNode=new TreeNode(Integer.parseInt(sb.toString())); 
 
                    if(currChar=='('){
                        bisStr.reset( );
                        newNode.left=deserialize(bisStr);
                        newNode.right=deserialize(bisStr);
                    }
 
                    if(bisStr.available()>0){
                        bisStr.mark(1);
                        currChar=(char)bisStr.read();
                        if(currChar!=')'){
                            bisStr.reset();
                        }
                    }
                    return newNode;
                }
            }
        }
        return null;
    }
    
	public void inorderTraversal(TreeNode node){
		if(node==null)
			return;
		else{
			inorderTraversal(node.left);
			System.out.println(" "+node.data);
			inorderTraversal(node.right);
		}
	}
	
	// Test code to check the above functionality.
	// Creates a binary tree as per specified in requirements, serializes it into a file, de-serializes into a new tree, serializes into new file.
	// Compare two files to verify the structure of the trees.
	
	
    public static void main(String[] args){
		
    	// Build a sample tree
    	BinaryTree myTree=new BinaryTree();
		TreeNode root=myTree.setRoot(20);
		TreeNode n1=myTree.setLeftChild(root, 23);
		myTree.setLeftChild(n1, 1);
		TreeNode n2=myTree.setRightChild(n1, 9);
		myTree.setRightChild(n2, 12);
		n1=myTree.setRightChild(root, 24);
		n2=myTree.setRightChild(n1, 8);
		n1=myTree.setLeftChild(n2, 12);
		myTree.setRightChild(n1, 20);
		
		
		// Create new file and call serialize function
		File file = new File("BinaryTree1.txt");
		try{
			FileOutputStream fos=new FileOutputStream(file);
			myTree.serialize(fos,root);
		}catch(Exception ex){
			System.out.println("Exception occurred while serializing tree 1");
		}
		
		BinaryTree myTree2=null;
		try{
			FileInputStream fis=new FileInputStream(file);
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			//Create new tree by de-serializing BinaryTree1.txt
			 myTree2=new BinaryTree();
			myTree2.root=myTree2.deserialize(bis);
		}catch(Exception ex){
			System.out.println("Exception occurred while de-serializing tree 1");
		}
			// Re-Serialize new Tree to new file BinaryTree2.txt to verify the correctness by comparing 2 files
			file = new File("BinaryTree2.txt");
			try{
				FileOutputStream fos=new FileOutputStream(file);
				myTree2.serialize(fos,myTree2.root);
			}catch(Exception ex){
				System.out.println("Exception occurred while serializing tree 2");
			}
		
	}
}
