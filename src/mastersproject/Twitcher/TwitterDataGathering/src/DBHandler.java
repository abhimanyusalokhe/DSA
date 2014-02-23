import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DBHandler {
	private String dbUrl = "jdbc:mysql://localhost:3306/mydb?user=root&password=nazya";
	private String recordTweetQuery = "insert into tweets(id,tweettext,location,topic,status) values(?,?,?,?,?)";

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
	public void recordTweet(Tweet tweet){
		
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(recordTweetQuery);
			stmt.setString(1,tweet.tweetId);
			stmt.setString(2, tweet.tweet);
			stmt.setString(3, tweet.location);
			stmt.setString(4, tweet.topic);
			stmt.setString(5, "u");
			stmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}  //end main


}
