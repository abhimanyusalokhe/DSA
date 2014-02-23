import java.util.Date;


public  class TestResult implements Comparable<TestResult>{
	
	   int studentId;
	   Date testDate;
	   int testScore;
	
	   public TestResult(int id, Date tD, int score){
		   studentId = id;
		   testDate = tD;
		   testScore = score;
	   }
	
	
//	public int compareTo(TestResult o1, TestResult o2) {
//		 int result = Integer.valueOf(o1.studentId).compareTo(Integer.valueOf(o2.studentId));
//	        if (result != 0)
//	        {
//	            return result;
//	        }
//	      result = Integer.valueOf(o1.testScore).compareTo(Integer.valueOf(o2.testScore));
//	        if (result != 0)
//	        {
//	            return result;
//	        }
//	      return 0;  
//	}


	@Override
	public int compareTo(TestResult tr) {
		// TODO Auto-generated method stub
		int compareId = ((TestResult) tr).studentId; 
		 
		//ascending order
		if((this.studentId - compareId)==0){
			int compareScore = ((TestResult) tr).testScore;
			return compareScore - this.testScore ;
		}else{
			return this.studentId - compareId;
		}
	}

	
	
	

}
