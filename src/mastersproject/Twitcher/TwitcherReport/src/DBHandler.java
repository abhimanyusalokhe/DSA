import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class DBHandler {
	private String dbUrl = "jdbc:mysql://localhost:3306/mydb?user=root&password=nazya";
	private String recordTweetQuery = "insert into tweets(id,tweettext,location,topic) values(?,?,?,?)";
	private String getTrends = "select topic,count(tweettext) from tweets where status = 'p' and lower(location) in (select lower(state) from states) group by topic order by count(tweettext) desc";
	private String getStates = "select state from states";
	private String getSentimentCount = 	"select location,count(sentiment) as count  " +
										"from tweets " +
										"where status = 'p' and lower(location) in (select lower(state) from states) " +
										"and topic = ? " +
										"and sentiment = ?" + 
										"group by location";
	private Connection getConnection(){
		Connection con = null;
		try{
			if(con==null){
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection (dbUrl);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return con;
	}
	
	public Map<String,Integer> getAllTrends(){
		Map<String,Integer> trends = null;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(getTrends);
			ResultSet rs =stmt.executeQuery();
			if(rs!= null){
				trends = new HashMap<String,Integer>();
				while(rs.next()){
					trends.put(rs.getString("topic"), rs.getInt("count(tweettext)"));
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return trends;
	}
	
	public List<String> getAllStates(){
		List<String> states = null;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(getStates);
			ResultSet rs =stmt.executeQuery();
			if(rs!= null){
				states = new ArrayList<String>();
				while(rs.next()){
					states.add(rs.getString("state"));
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return states;
	}
	
	
	public Sentiment getStateWiseSetiments(String trend){
		Sentiment sentimentDetails = new Sentiment();
		sentimentDetails.trend=trend;
		Map<String,Integer> positiveCount= null;
		Map<String,Integer> negativeCount= null;
		Map<String,Integer> neutralCount= null;
		
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(getSentimentCount);
			stmt.setString(1, trend);
			stmt.setString(2, "positive");
			ResultSet rs =stmt.executeQuery();
			if(rs!= null){
				positiveCount = new TreeMap<String,Integer>();
				while(rs.next()){
					positiveCount.put(rs.getString("location"), rs.getInt("count"));
				}
				
				sentimentDetails.positiveCount=positiveCount;
			}
			
			stmt = con.prepareStatement(getSentimentCount);
			stmt.setString(1, trend);
			stmt.setString(2, "negative");
			rs =stmt.executeQuery();
			if(rs!= null){
				negativeCount = new TreeMap<String,Integer>();
				while(rs.next()){
					negativeCount.put(rs.getString("location"), rs.getInt("count"));
				}
				sentimentDetails.negativeCount=negativeCount;
			}
			
			stmt = con.prepareStatement(getSentimentCount);
			stmt.setString(1, trend);
			stmt.setString(2, "neutral");
			rs =stmt.executeQuery();
			if(rs!= null){
				neutralCount = new TreeMap<String,Integer>();
				while(rs.next()){
					neutralCount.put(rs.getString("location"), rs.getInt("count"));
				}
				sentimentDetails.neutralCount=neutralCount;
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sentimentDetails;
	}
	
	
	public void recordTweet(Tweet tweet){
		
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(recordTweetQuery);
			stmt.setString(1,tweet.tweetId);
			stmt.setString(2, tweet.tweet);
			stmt.setString(3, tweet.location);
			stmt.setString(4, tweet.topic);
			stmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}  //end main


}
