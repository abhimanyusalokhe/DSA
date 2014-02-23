package Sorting;

public class SelectionSort {
	ArrayManipulation array;
	
	SelectionSort(){
		array=new ArrayManipulation();
	}
	
	public void selectionSort(){
		int[] inputArray=array.inputArray;
		int min=0,temp;
		for(int i=0;i<inputArray.length;i++){
			min=i;
			System.out.println("Minimum="+inputArray[i]);
			for(int j=i+1;j<inputArray.length;j++){
				if(inputArray[min]>inputArray[j]){
					min=j;
				}
			}
			if(min!=i){
				temp=inputArray[min];
				inputArray[min]=inputArray[i];
				inputArray[i]=temp;
			}
		}
		array.inputArray=inputArray;
		System.out.println();
		System.out.println("Array after sorting...");
		array.printArray();
	}
	
	public static void main(String[] args){
		SelectionSort ss=new SelectionSort();
		ss.selectionSort();
	}
}
