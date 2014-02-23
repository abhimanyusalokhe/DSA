import java.util.ArrayList;
import java.util.LinkedList;




public class TreeExecutor {
	MyNode root;
	private class MyNode{
		int data;
		MyNode left,right;
		
		MyNode(int data){
			this.data=data;
		}
	}
	public static void main(String[] args){
		BinaryTree bt=new BinaryTree();
		bt.recInsert(4);
		bt.recInsert(2);
		bt.recInsert(1);
		bt.recInsert(3);
		bt.recInsert(6);
		bt.recInsert(5);
		bt.recInsert(7);
		
		System.out.println("Inorder");
		bt.inorderTraversal(bt.root);
		System.out.println("Pre-order");
		bt.preorderTraversal(bt.root);
		System.out.println("Post-order");
		bt.postorderTraversal(bt.root);
		System.out.println("BFS");
		bt.bfs(bt.root);
		System.out.println("DFS");
		bt.DFS(bt.root);
		
		System.out.println("Size: "+bt.size(bt.root));
		
		System.out.println("Height: "+bt.height(bt.root));
		
		
		System.out.println("5 found in tree rec:"+bt.lookUp(bt.root, 5));
		System.out.println("5 found in tree rec:"+bt.iterativeLookUp(bt.root, 5));
		
		System.out.println("15 found in tree:"+bt.lookUp(bt.root, 15));
		System.out.println("15 found in tree:"+bt.iterativeLookUp(bt.root, 15));
		
		System.out.println("Tree has root to leaf path sum 7:"+bt.hasPathSum(bt.root, 7));
		System.out.println("Tree has root to leaf path sum 9:"+bt.hasPathSum(bt.root, 9));
		System.out.println("Tree has root to leaf path sum 15:"+bt.hasPathSum(bt.root, 15));
		System.out.println("Tree has root to leaf path sum 17:"+bt.hasPathSum(bt.root, 17));
		System.out.println("Tree has root to leaf path sum 27:"+bt.hasPathSum(bt.root, 27));
		
		System.out.println("Mirror of tree:");
		bt.findMirror(bt.root);
		bt.findMirror(bt.root);
		
		BinaryTree bt2=new BinaryTree();
		bt2.root=bt2.iterativeInsert(bt2.root,4);
		bt2.root=bt2.iterativeInsert(bt2.root,2);
		bt2.root=bt2.iterativeInsert(bt2.root,1);
		bt2.root=bt2.iterativeInsert(bt2.root,3);
		bt2.root=bt2.iterativeInsert(bt2.root,6);
		bt2.root=bt2.iterativeInsert(bt2.root,5);
		bt2.root=bt2.iterativeInsert(bt2.root,7);
		bt2.inorderTraversal(bt2.root);
		System.out.println("Trees are same:"+bt2.sameTree(bt.root, bt2.root));
		
		BinaryTree bt3=new BinaryTree();
		bt3.iterativeInsert(bt3.root,4);
		bt3.iterativeInsert(bt3.root,2);
		bt3.iterativeInsert(bt3.root,1);
		bt3.iterativeInsert(bt3.root,3);
//		bt3.iterativeInsert(bt3.root,6);
//		bt3.iterativeInsert(bt3.root,5);
		bt3.inorderTraversal(bt3.root);
		System.out.println("Trees are same:"+bt3.sameTree(bt.root, bt3.root));
		
		System.out.println("Least Common Ancestor of 3 & 6:"+(bt.leastCommonAncestor(bt.root, 3, 6)));
		System.out.println("Least Common Ancestor of 1 & 4:"+(bt.leastCommonAncestor(bt.root, 1, 4)));
		System.out.println("Least Common Ancestor of 1 & 7:"+(bt.leastCommonAncestor(bt.root, 1, 7)));
		System.out.println("Least Common Ancestor of 1 & 3:"+(bt.leastCommonAncestor(bt.root, 1, 3)));
		
		
		TreeExecutor te=new TreeExecutor();
		MyNode newNode=te.new MyNode(10);
		te.root=newNode;
	    newNode=te.new MyNode(3);
		te.root.left=newNode;
		newNode=te.new MyNode(30);
		te.root.right=newNode;
		
		
		
		System.out.println("Tree is BST: "+te.isBST(te.root,true));
		System.out.println("Tree is Balanced: "+bt3.isBalanced(bt3.root));
		
		System.out.println("Levelwise linked lists..");
		bt.levelWiseLinkedLists(bt.root);
		
		System.out.println("Next element in in-order traversal for tree is:");
		bt.findPreviousInInorder(bt.root,null,1);
		
		System.out.println("All Paths...");
		bt.printPaths(bt.root, new int[100], 0);
		System.out.println("Printing path from 4 to 3:");
		bt.printPathsFromNode(bt.root, new int[100], 0, 3);
	}
	
	public boolean isBST(MyNode root,boolean flag){
		if(root==null){
			
		}else{
			isBST(root.left,flag);
			if((root.left!=null && root.data<root.left.data)||(root.right!=null && root.data>root.right.data)){
				System.out.println("Not a BST");
				flag=false;
			}isBST(root.right,flag);
		}
		return flag;
		
	}
	
}
