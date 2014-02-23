import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class APIRequestor {
	 private static final String GEOCODE_REQUEST_URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false";
	 
	 
	 private static final String TWITTER_TREND_REQUEST_URL = "https://api.twitter.com/1/trends/23424977.json";
	 
	 
	 private static HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
	     
	     
	    public String hitAPI(String apiType,String parameter){
	    	String result = "";
	    	try{
	    		StringBuilder urlBuilder = null;
	    		
	    		if("twittertrend".equals(apiType)){
	    			urlBuilder = new StringBuilder(TWITTER_TREND_REQUEST_URL);
	    			
	    		}else if ("geocode".equals(apiType)){
	    			urlBuilder = new StringBuilder(GEOCODE_REQUEST_URL);
	    			parameter.replace("\\W", "");
	    			urlBuilder.append("&address=").append(URLEncoder.encode(parameter,"UTF-8"));
	    		}
	            
	            final GetMethod getMethod = new GetMethod(urlBuilder.toString());
	            try {
	                httpClient.executeMethod(getMethod);
	                Reader reader = new InputStreamReader(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
	                 
	                int data = reader.read();
	                char[] buffer = new char[1024];
	                Writer writer = new StringWriter();
	                while ((data = reader.read(buffer)) != -1) {
	                        writer.write(buffer, 0, data);
	                }
	                result = writer.toString();
	            }catch(Exception ex){
	            	ex.printStackTrace();
	            }
	 
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    	
	    	return result;
	    }
	    
	    
	    public String getState(String jsonResult){
	    	String location = "";
	    	// Creating JSON Parser instance
	    	//System.out.println(jsonResult);
	    	String [] lines = jsonResult.split("\n");
	    	for(int i=0;i<lines.length;i++){
	    		//System.out.println(lines[i]);
	    		if(lines[i].contains("administrative_area_level_1")){
	    			location = lines[i-2];
	    			break;
	    		}
	    	}
	    	
	    	location = location.replace("\"", "");
	    	location = location.replace("\\s+", " ");
	    	location = location.replace("long_name", "");
	    	location = location.replace(",", "");
	    	location = location.replace(":", "");
	    	location = location.trim();
	    	System.out.println("= "+location);
//			JSONParser jParser = new JSONParser();
//			try {
//				JSONObject json = (JSONObject)jParser.parse(jsonResult);
//
//				// Getting Array of Items
//				JSONArray items = (JSONArray)json.get("results");
//				if(items!=null && items.size()>0){
//					JSONArray addressComponents = (JSONArray)items.get(0);
//					for(int i=0;i<addressComponents.size();i++){
//						JSONObject ac = (JSONObject)addressComponents.get(i);
//						JSONArray types = (JSONArray)ac.get("types");
//						String type = (String)types.get(0);
//						String shortName ="";
//						if("administrative_area_level_1".equals(type)){
//							shortName = String.valueOf(ac.get("short_name"));
//							location = shortName;
//						}
//						
//					}
//				}
//				
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return location;
	    }
	    
	    public HashMap<Integer,String> getTrend(String jsonResult){
	    	String trend="";
	    	HashMap<Integer,String> trendMap = new HashMap<Integer, String>();
	    	jsonResult = jsonResult.replace("]}]", "]}");
	    	System.out.println(jsonResult);
	    	JSONParser jParser = new JSONParser();
			try {
				JSONObject json = (JSONObject)jParser.parse(jsonResult);
				// Getting Array of Items
				JSONArray items = (JSONArray)json.get("trends");
				if(items!=null && items.size()>0){
					for(int i=0;i<items.size()&&i<10;i++){
						JSONObject trendingTopic = (JSONObject)items.get(i);
						trend =(String)trendingTopic.get("name");
						trendMap.put(i, trend);
					}
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
	    	return trendMap;
	    }
	     
	
}
