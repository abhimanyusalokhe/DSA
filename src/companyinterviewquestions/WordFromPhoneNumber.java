/*
 * Filename 	: WordFromPhoneNumber.java
 * Author		: Abhimanyu Salokhe
 * Date			: 04-17-2012
 * Description	: Contains functions related to creating all possible combinations of words out of a phone number
 * 				  based on the standard phone keypad.
 * */

import java.util.Scanner;


public class WordFromPhoneNumber {
	
	// Conversion table used to convert keypad digits to appropriate letter
	private char[][] conversionTable={ 	{'_'},				//mapping for '0'
										{'_'},				//mapping for '1'
										{'a','b','c'},		//mapping for '2'
										{'d','e','f'},		//mapping for '3'
										{'g','h','i'},		//mapping for '4'
										{'j','k','l'},		//mapping for '5'
										{'m','n','o'},		//mapping for '6'
										{'p','q','r','s'},	//mapping for '7'
										{'t','u','v'},		//mapping for '8'
										{'w','x','y'},		//mapping for '9'
									  };
	
	/*
	 * Function name	: createWordFromPhoneNumber
	 * Description		: Function to create word from phone number
	 * Input param		: phoneNumber array, arraySize, currentIndex
	 * Return type		: void
	 * */ 
	
	public void createWordFromPhoneNumber
		(char[] phoneNumber,int arraySize,int currentIndex){
		
		if(currentIndex < arraySize){
			
			char charDigit = phoneNumber[currentIndex];
			int digit = Integer.parseInt(String.valueOf(charDigit));
			
			for(int loopIndex=0 ; loopIndex < 4 ; loopIndex++){
				try{
					char letter = conversionTable[digit][loopIndex];
					
					phoneNumber[currentIndex] = letter;
					createWordFromPhoneNumber(phoneNumber, arraySize, currentIndex+1);
					phoneNumber[currentIndex] = charDigit;
				
				}catch(IndexOutOfBoundsException iOBEx){}
				
			}
		}else{
			printCreatedWord(phoneNumber);
		}
	}
	
	/*
	 * Function name	: printCreatedWord
	 * Description		: Funciton used to print the character array word formed at each step
	 * Input param		: word array
	 * Return type		: void 
	 * */ 
	private void printCreatedWord(char[] word){
		System.out.print("");
		for(int loopIndex = 0 ; loopIndex < word.length ; loopIndex++){
			System.out.print(word[loopIndex]);
		}
		System.out.print(",");
		
	}
	
	
	/*
	 * Function name	:	main
	 * Description		:	Starting point of the program from where the cerateWordFromPhoneNumber function is invoked
	 * */
	public static void main(String[] args){
		WordFromPhoneNumber obj=new WordFromPhoneNumber();
		Scanner scanObj=new Scanner(System.in);
		
		System.out.print("Enter phone number:");
		String phoneNumber=scanObj.next();
		System.out.println("String is:"+phoneNumber);
		System.out.println("List of words formed is:");
		obj.createWordFromPhoneNumber(phoneNumber.toCharArray(), phoneNumber.length(), 0);
	}
	
}


