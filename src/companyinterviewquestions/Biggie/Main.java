import java.util.Arrays;


public class Amazon {
	public static void main(String args[]){
		 
		TestResult[] resutls = new TestResult[5];
 
		TestResult pineappale = new TestResult(1, null,70);
		TestResult pineappale2 = new TestResult(1, null,50);
		TestResult apple = new TestResult(2,null,100); 
		TestResult orange = new TestResult(3, null,80); 
		TestResult banana = new TestResult(4, null,90); 
 
		resutls[0]=pineappale;
		resutls[1]=apple;
		resutls[2]=orange;
		resutls[3]=banana;
		resutls[4]=pineappale2;
 
		Arrays.sort(resutls);
 
		int i=0;
		for(TestResult temp: resutls){
		   System.out.println("TestResult " + ++i + " : " + temp.studentId + ", Score : " + temp.testScore);
		}
 
	}	
}
