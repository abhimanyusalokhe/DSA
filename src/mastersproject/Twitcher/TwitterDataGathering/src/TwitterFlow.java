import java.io.IOException;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterFlow {
	
	Configuration conf;
	TwitterConfiguration twitterConfDetails;
	APIRequestor ar;
	DBHandler dh;
	TwitterStream ts;
	
	
	TwitterFlow(TwitterConfiguration tc){
		ar = new APIRequestor();
		dh = new DBHandler();
		twitterConfDetails = tc;
	}
	
	public void setConfiguration(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey(twitterConfDetails.consumerKey)
	      .setOAuthConsumerSecret(twitterConfDetails.consumerSecret)
	      .setOAuthAccessToken(twitterConfDetails.accessToken)
	      .setOAuthAccessTokenSecret(twitterConfDetails.accessSecret);
	    
	    conf = cb.build(); 
	}
	
	
	public void startTweetFlow(final String trend) throws TwitterException, IOException{
		
		 StatusListener listener = new StatusListener(){
		        public void onStatus(Status status) {
		            //System.out.println(trend+"-->"+status.getUser().getLocation() + " : " + status.getText() + ":" + status.getId());
		            
		            	Tweet myTweet = new Tweet();
		            	System.out.print("Location->"+status.getUser().getLocation());
		            	if(!"".equals(status.getUser().getLocation())){
			            	String jsonResult = ar.hitAPI("geocode",status.getUser().getLocation());
			            	myTweet.location = ar.getState(jsonResult);
		            	}
		            	myTweet.tweet = status.getText();
		            	myTweet.tweetId = String.valueOf(status.getId());
		            	myTweet.topic = trend;
		            	if(!"".equals(myTweet.location)){
		            		dh.recordTweet(myTweet);
		            	
		            	
		            }
		        }
		        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
		        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
		        public void onException(Exception ex) {
		            ex.printStackTrace();
		        }
				@Override
				public void onScrubGeo(long arg0, long arg1) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onStallWarning(StallWarning arg0) {
					// TODO Auto-generated method stub
					
				}
		    };
		    
		    String trend1[] = new String[1];
		    trend1[0]=trend;
		    FilterQuery fq = new FilterQuery();
		    fq.track(trend1);
		    
		    ts = new TwitterStreamFactory(conf).getInstance();
		    ts.addListener(listener);
		    ts.filter(fq);
	}
	
	
	
	public void stopStreamingProcess(){
		if(ts!=null){
			ts.shutdown();
		}
	}
	
	   
}
