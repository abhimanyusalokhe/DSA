/*
 * Approach : The algorithm copies over all elements of shorter list to a HashSet.
 * 			  Then iterates over longer list and checks if each element of the list exists in the HashSet.
 * 			  If it does not exist, removes that element from the iterating list.
 *			  Finally returns the modified longer list.
 *
 * Complexity : Time - O(m) + O(n) = O(n)  The algorithm iterates over the lists linearly one element at a time.
 * 				Space - O(n)  Need an additional space for the all elements in shorter list
 * 
 * Additional memory : Used a HashSet to hold elements from the shorter list 
 * 					   HashSet is used to take advantage of the "contains" method of the HashSet class
 * 					   that has time complexity O(1) as compared to O(n) complexity in case of "contains" method of the list. 
 * 
 */

package com.amazon.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AmazonQuestion {
	
	public static List<Integer> intersection (List<Integer> a, List<Integer> b) {
		// If any either of the list is empty return an empty list
		if(a==null || b==null || a.size()==0 || b.size()==0)
			return Collections.emptyList();
		
		// Decide which list is smaller to declare additional memory
		List<Integer> small = a;
		List<Integer> big = b;
		
		if(small.size() > big.size()){
			big = a;
			small = b;
		}
		
		// Declare temporary container for 1st list.
		HashSet<Integer> container = new HashSet<Integer>(small);
		
		// Iterator over 2nd list to check if elements exist in container. If not remove from the list
		Iterator<Integer> it = big.iterator();
		while(it.hasNext()){
			int num = it.next();
			if(!container.contains(num)){
				it.remove();
			}
		}
		
		// Return modified 2nd list
		return big;
	}
	
	
}
