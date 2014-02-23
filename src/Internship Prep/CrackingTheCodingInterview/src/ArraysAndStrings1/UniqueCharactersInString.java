package ArraysAndStrings1;

public class UniqueCharactersInString {
	public static String inputString="hello";
	
	public static boolean hasUniqueChars(){
		char[] input=inputString.toCharArray();
		int val=0;
		for(int i=0;i<input.length;i++){
			val=input[i]-'a';
			System.out.println("Char at "+(i+1)+"="+(1<<val));
		}
		
		return true;
	}
	
	public static void reverseCStyleString(){
		char[] input={'h','e','l','l','o','\n'};
		char temp;
		for(int i=0;i<(input.length)/2;i++){
			temp=input[(input.length-2)-i];
			System.out.println("Swapping "+temp+" and "+input[i]);
			input[(input.length-2)-i]=input[i];
			input[i]=temp;
		}
		for(int i=0;i<=input.length;i++)
			System.out.println(""+input[i]);
	}
	
	
	public static void removeDuplicateCharacters(){
		String input="aaaabcdefgg";
		
		char[] inputC=input.toCharArray();
		char curr;
		boolean flag=false;
		int k=1;
		for(int i=1;i<inputC.length;i++){
			curr=inputC[i];
			System.out.println("Curr="+curr);
			flag=false;
			for(int j=0;j<i;j++){
				if(inputC[j]==curr){
					flag=true;
				}
			}
			System.out.println("Flag="+flag);
			if(!flag){
				inputC[k++]=curr;
				
			}
			for(int m=0;m<inputC.length;m++)
				System.out.println(""+inputC[m]);
		}
		inputC[k]='\n';
		
		for(int i=0;i<inputC.length;i++)
			System.out.println(""+inputC[i]);
	}
	
	public static void replaceSpaces(){
		String input="This is Test";
		char[] inputC=input.toCharArray();
		int count=0;
		for(int i=0;i<inputC.length;i++){
			if(inputC[i]==' '){
				count++;
			}
		}
		char[] outputC=new char[inputC.length+(count*2)];
		int k=outputC.length-1;
		for(int i=inputC.length-1;i>=0;i--){
			if(inputC[i]==' '){
				outputC[k--]='0';
				outputC[k--]='2';
				outputC[k--]='%';
			}else{
				outputC[k--]=inputC[i];
			}
		}
		System.out.println("");
		for(int i=0;i<outputC.length;i++)
			System.out.print(outputC[i]);
	}
	
	
	public static void main(String[] args){
		//hasUniqueChars();
		//reverseCStyleString();
		//removeDuplicateCharacters();
		replaceSpaces();
	}
}
