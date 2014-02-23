import java.util.HashMap;


public class TwitcherDataGatherer {
	public static HashMap <Integer,String> trends;
	
	public TwitcherDataGatherer() {
		trends = new HashMap<Integer, String>();
	}
	
	
	public static void loadTrends(){
		APIRequestor ar = new APIRequestor();
		String jsonResult = ar.hitAPI("twittertrend", null);
		trends=  ar.getTrend(jsonResult);
	}
	
	public static void main(String[] args){
		
		TwitterConfiguration conf = new TwitterConfiguration();
		conf.consumerKey = CommonData.consumerKey1;
		conf.consumerSecret = CommonData.consumerSecret1;
		conf.accessToken = CommonData.accessToken1;
		conf.accessSecret = CommonData.accessSecret1;
		
		TwitterFlow tf1 = new TwitterFlow(conf);
		tf1.setConfiguration();
		conf = new TwitterConfiguration();
		conf.consumerKey = CommonData.consumerKey2;
		conf.consumerSecret = CommonData.consumerSecret2;
		conf.accessToken = CommonData.accessToken2;
		conf.accessSecret = CommonData.accessSecret2;
		
		TwitterFlow tf2 = new TwitterFlow(conf);
		tf2.setConfiguration();
		try{
			while(true){
				loadTrends();
				tf1.stopStreamingProcess();
				tf1.startTweetFlow(trends.get(0));
				
				tf2.stopStreamingProcess();
				tf2.startTweetFlow(trends.get(1));
				
				Thread.sleep(1800000);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
