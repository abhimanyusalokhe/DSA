package Sorting;

public class HeapSort {
	int[] inputArray;
	int n;
	ArrayManipulation array;
	
	HeapSort(){
		array=new ArrayManipulation();
		inputArray=array.inputArray;
		n=inputArray.length;
	}
	
	public void downHeap(int v){
		System.out.println("Downheaping from element:"+inputArray[v]);
		int w=v*2+1;
		System.out.println("Children are:"+inputArray[w]+":"+((w+1)<n?inputArray[w+1]:-1));
		while(w<n){
			if((w+1)<n){
				if(inputArray[w+1]>inputArray[w]){
					w++;
				}
			}
			
			if(inputArray[w]>inputArray[v]){
				exchange(w,v);
				v=w;
				w=v*2+1;
			}else{
				return;
			}
		}
	}
	
	public void exchange(int i,int j){
		int temp=inputArray[i];
		inputArray[i]=inputArray[j];
		inputArray[j]=temp;
	}
	
	public void buildHeap(){
		for(int i=n/2-1;i>=0;i--){
			downHeap(i);
		}
	}
	
	public void heapSort(){
		buildHeap();
//		while(n>1){
//			n--;
//			exchange(0,n);
//			downHeap(0);
//		}
		array.inputArray=inputArray;
		System.out.println("Array after sorting..");
		array.printArray();
	}
	
	public static void main(String[] args){
		HeapSort hs=new HeapSort();
		hs.heapSort();
	}
	
}
