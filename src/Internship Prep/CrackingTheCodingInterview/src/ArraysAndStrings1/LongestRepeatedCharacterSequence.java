package ArraysAndStrings1;

public class LongestRepeatedCharacterSequence {
	public static void main(String[] args){
		String str="abccsassaaadffff";
		char maxSeqChar='\n';
		int maxCount=0;
		int count=1;
		char prevChar=str.charAt(0);
		for(int i=1;i<=str.length();i++){
			System.out.println("Previous char="+prevChar);
			if(i!=str.length() && str.charAt(i)==prevChar){
				count++;
			}else{
				System.out.println("Max count="+maxCount);
				System.out.println("Current count="+count);
				if(maxCount<count){
					maxSeqChar=prevChar;
					maxCount=count;
				}
				count=1;
				if(i!=str.length())
					prevChar=str.charAt(i);
			}
		}
		System.out.println("Max sequence");
		for(int i=0;i<maxCount;i++){
			System.out.print(maxSeqChar);
		}
	}
}
