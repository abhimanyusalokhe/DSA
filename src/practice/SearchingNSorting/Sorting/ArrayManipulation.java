package Sorting;

import java.util.Random;

public class ArrayManipulation {
	public int inputArray[]=new int[10];
	
	public ArrayManipulation(){
		Random num=new Random();
		for(int i=0;i<10;i++){
			inputArray[i]=num.nextInt(100);
		}
		System.out.println("Input Array is :");
		printArray();
	}
	
	public void printArray(){
		for(int i=0;i<10;i++){
			System.out.print(inputArray[i]+" ");
		}
	}
}
