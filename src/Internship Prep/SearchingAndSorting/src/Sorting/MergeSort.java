package Sorting;

public class MergeSort {
	ArrayManipulation array;
	int[] inputArray;
	
	MergeSort(){
		array=new ArrayManipulation();
		inputArray=array.inputArray;
	}
	
	public void mergeSort(int[] inputArray,int low,int high){
		if(low<high){
			int mid=(low+high)/2;
			mergeSort(inputArray,low,mid);
			mergeSort(inputArray,mid+1,high);
			merge(inputArray,low,mid,high);
		}
	}
	
	public void merge(int[] inputArray,int low,int mid,int high){
		int[] outputArray=new int[10];
		for(int i=0;i<inputArray.length;i++){
			outputArray[i]=inputArray[i];
		}
		
		int i=low,j=mid+1,k=low;
		while(i<=mid && j<=high){
			if(outputArray[i]<outputArray[j]){
				inputArray[k++]=outputArray[i++];
			}else{
				inputArray[k++]=outputArray[j++];
			}
		}
		while(i<=mid){
			inputArray[k++]=outputArray[i++];
		}
		while(j<=high){
			inputArray[k++]=outputArray[j++];
		}
		System.out.println();
		
		for(int iLoop=0;iLoop<= high;iLoop++){
			System.out.print(" "+inputArray[iLoop]);
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		MergeSort ms=new MergeSort();
		ms.mergeSort(ms.inputArray, 0,ms.inputArray.length-1);
	}
}
