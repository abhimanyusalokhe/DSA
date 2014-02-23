package Searching;

import Sorting.ArrayManipulation;
import Sorting.QuickSort;

public class BinarySearch {
	public void binarySearch(int a[],int lb,int ub,int key){
		int mid=(lb+ub)/2;
		
		if(lb<=ub){
			if(a[mid]==key){
				System.out.println("Found element at position:"+mid);
				return;
			}else if(a[mid]>key){
				binarySearch(a,lb,mid-1,key);
			}else if(a[mid]<key){
			    binarySearch(a,mid+1,ub,key);
			}
		}else{
			System.out.println("Element not found");
			return;
		}
			
		
	}
	
	public void binarySearchIterative(int[] a,int key){
		int lb=0,ub=a.length-1;
		int mid=0;
		boolean flag=false;
		while(lb<=ub){
			mid=(lb+ub)/2;
			if(a[mid]==key){
				flag=true;
				break;
			}else if(a[mid]>key){
				ub=mid-1;
			}else if(a[mid]<key){
				lb=mid+1;
			}else{
				System.out.println("Some error condition occurred");
			}
		}
		if(flag){
			System.out.println("Element found at position:"+mid);
		}else{
			System.out.println("Element not found");
		}
	}
	
	public static void main(String[] args){
		ArrayManipulation array=new ArrayManipulation();
		QuickSort qs=new QuickSort();
		array.inputArray=qs.sortForSearch();
		BinarySearch bs=new BinarySearch();
		//bs.binarySearch(array.inputArray,0,array.inputArray.length-1,array.inputArray[7]);
		bs.binarySearchIterative(array.inputArray, array.inputArray[6]);
	}
}
