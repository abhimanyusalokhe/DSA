package Sorting;

import java.util.Enumeration;
import java.util.Hashtable;

public class FirstRepeatedCharacterInString {
	public static String input="abcdaeccdeessssaa";
	
	public static void main(String[] args){
		boolean[] arr=new boolean[256];
		int flag=-1;
		for(int i=0;i<input.length();i++){
			if(arr[input.charAt(i)]){
				flag=i;
				break;
			}
			arr[input.charAt(i)]=true;
		}
		if(flag!=-1){
			System.out.println("First repeeated char:"+input.charAt(flag));
		}
		
		highestRepeatedChar();
	}
	
	public static void highestRepeatedChar(){
		Hashtable<String,Integer> counts=new Hashtable<String,Integer>();
		int latest=0,max=0;
		String maxChar="";
		
		for(int i=0;i<input.length();i++){
			if(counts.get(input.charAt(i))!=null){
				latest=(Integer)counts.get(input.charAt(i));
			}
			counts.put(String.valueOf(input.charAt(i)),latest++);
			if(latest>max){
				maxChar=String.valueOf(input.charAt(i));
			}
		}
		
		Enumeration<String> keys=counts.keys();
		while(keys.hasMoreElements()){
			String key=keys.nextElement();
		}
		System.out.println("Max char="+maxChar);
	}
}
