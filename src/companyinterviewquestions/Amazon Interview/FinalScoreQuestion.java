import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FinalScoreQuestion {

		 
	Map <Integer, Double> calculateFinalScores (List<TestResult> results) {
		Map <Integer,Double> finalScoreMap = new HashMap<Integer, Double>();
		
		//TestResult[] resultArray = (TestResult[])results.toArray();
		
		
 
		Collections.sort(results);
 
		printList(results);
		int studentTestLooper=0;
		int finalScore =0;
		int currentStudentId = 0;
		for(TestResult temp: results){
			if(studentTestLooper<=4){
				currentStudentId = temp.studentId;
				finalScore = finalScore + temp.testScore;
				
			}
			
			if(studentTestLooper==4){
				finalScoreMap.put(currentStudentId, finalScore/5.0);
				finalScore = 0;
			}
			
			if(temp.studentId!= currentStudentId){
					studentTestLooper = 0;
			}
			studentTestLooper ++;
			
		}
		
		
		return finalScoreMap;
	}
	
	public void printList(List<TestResult> results){
		for(int i=0;i<results.size();i++){
			System.out.println("stud "+results.get(i).studentId+" test score ="+results.get(i).testScore);
		}
	}
	
	public static void main(String[] args){
		List<TestResult> result = new ArrayList<TestResult>();
		
		TestResult tr = new TestResult(1, null,70);
		result.add(tr);
		tr = new TestResult(1, null,70);
		result.add(tr);
		tr = new TestResult(2, null,30);
		result.add(tr);
		tr = new TestResult(3, null,100);
		result.add(tr);
		tr = new TestResult(1, null,33);
		result.add(tr);
		tr = new TestResult(2, null,55);
		result.add(tr);
		tr = new TestResult(1, null,22);
		result.add(tr);
		tr = new TestResult(2, null,71);
		result.add(tr);
		tr = new TestResult(2, null,73);
		result.add(tr);
		tr = new TestResult(2, null,75);
		result.add(tr);
		tr = new TestResult(3, null,77);
		result.add(tr);
		tr = new TestResult(1, null,72);
		result.add(tr);
		tr = new TestResult(3, null,77);
		result.add(tr);
		tr = new TestResult(3, null,72);
		result.add(tr);
		tr = new TestResult(3, null,72);
		result.add(tr);
		
		FinalScoreQuestion fsq = new FinalScoreQuestion();
		
		Map <Integer,Double> finalScoreMap = fsq.calculateFinalScores(result);
		
		for(Iterator i = finalScoreMap.keySet().iterator();i.hasNext();){
			int si = (Integer)i.next();
			Double finalScore = finalScoreMap.get(si);
			System.out.println("Student id = "+si+"  has final score = "+finalScore);
			
		}
	}
		 

}
