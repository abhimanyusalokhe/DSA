package Sorting;

import java.util.Random;

public class InsertionSort {
	ArrayManipulation array;
	
	InsertionSort(){
		array=new ArrayManipulation();
	}
	
	public void insertionSort(){
		int[] inputArray=array.inputArray;
		int element;
		for(int i=1;i<inputArray.length-1;i++){
			element=inputArray[i];
			System.out.println("Element in cosideration:"+element);
			int j=i-1;
			for(;j>=0 && inputArray[j]>element;j--){
				inputArray[j+1]=inputArray[j];
			}
			inputArray[j+1]=element;
		}
		
		array.inputArray=inputArray;
		System.out.println();
		array.printArray();
	}
	
	public static void main(String[] args){
		InsertionSort is=new InsertionSort();
		is.insertionSort();
	}
}
