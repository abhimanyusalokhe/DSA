package ArraysAndStrings1;

import java.util.Stack;

public class LongestPalindrome {
	String input;
	String longestPalindrome;
	StringBuffer temp;
	LongestPalindrome(){
		input="dsdseabhihbaklss";
	}
	
	public void findLongestPalindrome(){
		for(int i=0;i<input.length();i++){
			isPalindrome(i);
		}
	}
	
	private void isPalindrome(int center){
		int left=center-1;
		int right=center+1;
		temp=new StringBuffer();
		Stack st=new Stack();
		
		while(left>=0 && right<=input.length()-1){
			if(input.charAt(left)==input.charAt(right)){
				st.push(input.charAt(left));
			}else{
				break;
			}
			left--;right++;
		}
		if(!st.isEmpty()){
			temp.append(st.pop());
		}
		int i=temp.length()-1;
		temp.append(input.charAt(center));
		while(i>=0){
			temp.append(temp.charAt(i--));
		}
		
		if(longestPalindrome==null){
			longestPalindrome=temp.toString();
		}else if(longestPalindrome.length()<temp.toString().length()){
			longestPalindrome=temp.toString();
		}
		System.out.println("Longest Palindrome="+longestPalindrome);
		
	}
	
	public static void main(String[] args){
		LongestPalindrome lp=new LongestPalindrome();
		lp.findLongestPalindrome();
	}
}
