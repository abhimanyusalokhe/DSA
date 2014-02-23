package com.whitepages.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrayHopper {

	private String filepath;
	
	public void setFilepath(String inputFile){
		filepath = inputFile;
	}
	
	/*
	 * Function 	:	printMinimumHopPath
	 * Description 	:	This function calculates the minimum number of hops required to pass go out of bounds of the array limit
	 * 					given that each element holds the max number of hops allowed from the index of the element. It also prints the path comprising of
	 * 					minimum number of hops from start to out.
	 * Input Param	:	int[] - input array 
	 * Returns		:	void
	 * Logic		:	We maintain a minimumNumberOfHops Hash containing minimum number of hops required to go out of bounds
	 * 					corresponding to each element index in the given input array
	 * 					Calculate from end of the input array using 3 conditions
	 * 					1. If element at index is 0 -> Impossible to go out of bounds, so minHopsToReachEnd = Integer.MAX_VALUE
	 * 					2. If current index + element at index is greater than length-1 of input array, minHopsToReachEnd = 1
	 * 					3. Else find minimum value from minHopsToReachEnd hash for indexes it can reach from the current index and add 1 to it
	 * 					4. Also if a minimumHopsToReachEnd is calculated for current index insert the index and the next index it will reach from here
	 * 					into a result hashmap.
	 * 					5. At the end if the minimumHopsToReachEnd contains a legitimate value for the starting index, it means a path exists
	 * 					6. Print the path by traversing the result hashmap containing from - to pairs.
	 */
	
	public void printMinimumHopPath(ArrayList<Integer> input){
		if(input == null || input.size() == 0 || input.get(0) == 0){
			System.out.println("failure");
			return;
		}
		
		ArrayList<Integer> minPathIndexes = null;
		Map<Integer,Integer> minPath = new HashMap<Integer,Integer>();
		
		// Initilize the hash to contain minimum number of hops required from each position  
		Map<Integer, Integer> minHopsToReachEnd = new HashMap<Integer, Integer>();
		
		// Traverse given array from end to start
		for(int i=input.size()-1 ; i>=0 ; i--){
			if(input.get(i) == 0){
				minHopsToReachEnd.put(i, Integer.MAX_VALUE);
			}else if(i+input.get(i) > input.size()-1){
				minHopsToReachEnd.put(i, 1);
				minPath.put(i, -88);
			}else{
				// Check hops required to reach end for all indexes we can reach from this index
				// get the minimum of all and add 1 to it.
				int minimumHop = Integer.MAX_VALUE;
				int pathIndex = -99;
				for(int j=i+1 ; j<=i+input.get(i) ; j++){
					if(minimumHop > minHopsToReachEnd.get(j)){
						minimumHop = minHopsToReachEnd.get(j);
						pathIndex = j;
					}
				}
				
				if(minimumHop == Integer.MAX_VALUE){
					minHopsToReachEnd.put(i, minimumHop);
				}else{
					// If minimumHop is calculated, enter the from - to pair in the result path hashmap
					minPath.put(i, pathIndex);
					minHopsToReachEnd.put(i, minimumHop+1);
				}
			}
		}
		
		if(minHopsToReachEnd.get(0) == Integer.MAX_VALUE){
			System.out.println("failure");
		}else{
			// print the result hashmap by traversing from - to pairs
			int index = minPath.keySet().iterator().next();
			while(index != -88){
				System.out.print(index+", ");
				index = minPath.get(index);
			}
			System.out.println("out");
		}
	}
	
	private ArrayList<Integer> readFileIntoMemory()
			throws IOException, NumberFormatException{
		
		ArrayList<Integer> inputArray = new ArrayList<Integer>();
		BufferedReader buffRead = new BufferedReader(new FileReader(filepath));
		String currLine = buffRead.readLine();
		
		while (currLine != null) {
			inputArray.add(Integer.parseInt(currLine.trim()));
			currLine = buffRead.readLine();
		}
		
		buffRead.close();
		return inputArray;
	}
	
	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("failure-- No input array file passed");
			return;
		}
		
		ArrayHopper hopper = new ArrayHopper();
		hopper.setFilepath(args[0]);
		
		ArrayList<Integer> input = null;
		try{
			input = hopper.readFileIntoMemory();
		}catch(IOException ioEx){
			System.out.println("failure--input file error");
			return;
		}catch(NumberFormatException nfEx){
			System.out.println("failure--invalid number in input file");
			return;
		}

		hopper.printMinimumHopPath(input);
	}
}
