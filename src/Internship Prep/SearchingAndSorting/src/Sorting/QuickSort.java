package Sorting;

public class QuickSort {
	ArrayManipulation array;
	int[] inputArray;
	
	public QuickSort(){
		array=new ArrayManipulation();
		inputArray=array.inputArray;
	}
	
	public int partition(int[] a,int left,int right){
		int i=left,j=right;
		int pivot=a[(i+j)/2];
		System.out.println();
		System.out.println("Left="+left+" Right="+right);
		System.out.println("Pivot="+pivot);
		while(i<=j){
			while(a[i]<pivot)
				i++;
			while(a[j]>pivot)
				j--;
			if(i<=j){
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				i++;
				j--;
			}
		}
		return i;
	}
	public void sort(int[] a,int left,int right){
		int index=partition(a,left,right);
		System.out.println("Index="+index);
		printArray(a);
		if(left<index-1){
			sort(a,left,index-1);
		}
		if(index<right){
			sort(a,index,right);
		}
	
	}
	
	public int[] sortForSearch(){
		QuickSort qs=new QuickSort();
		qs.sort(qs.inputArray,0,qs.inputArray.length-1);
		return qs.inputArray;
	}
	
	public void printArray(int[] a){
		System.out.println();
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		QuickSort qs=new QuickSort();
		qs.sort(qs.inputArray,0,qs.inputArray.length-1);
	}
}
