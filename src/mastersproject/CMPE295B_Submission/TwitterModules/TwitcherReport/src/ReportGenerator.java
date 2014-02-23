import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ReportGenerator {
	DBHandler dh = null;
	StringBuffer staticPartBeforeGeoDynamicData;
	StringBuffer staticPartAfterGeoDynamicData ;
	StringBuffer staticPartAfterPieDynamicData;
	StringBuffer masterReport;
	public ReportGenerator(){
		dh = new DBHandler();
	}
	
	public static void main(String[] args){
		try{
			ReportGenerator rg = new ReportGenerator();
			while(true){
				Map<String,Integer> trends = rg.dh.getAllTrends();
				Sentiment sentimentDetails = null;
				if(trends != null){
					rg.initiateMasterReport();
					
					for(Iterator<String> it = trends.keySet().iterator(); it.hasNext();){
						String trend = (String)it.next();
						int count = trends.get(trend);
						String title = trend+" total tweets: "+  String.valueOf(count);
						System.out.println(trend+"-->"+count);
						
						sentimentDetails = rg.dh.getStateWiseSetiments(trend);
						
						if(sentimentDetails != null){
							rg.masterReport.append("<h1><a style='color: #DB5110;' href='Report_"+trend.replace("#", "%23")+".html'>"+trend+"</a></h1>\n");
							File file = rg.createFileForTrend(trend);
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							rg.createStaticReportParts();
							
							bw.write(rg.staticPartBeforeGeoDynamicData.toString());
							
							System.out.println("Trending Topic = "+sentimentDetails.trend);
							Map<String,Integer> positiveCount= sentimentDetails.positiveCount;
							Map<String,Integer> negativeCount= sentimentDetails.negativeCount;
							Map<String,Integer> neutralCount= sentimentDetails.neutralCount;
							
							int countryPositiveCount = 0;
							int countryNegativeCount = 0;
							int countryNeutralCount = 0;
							
							int countryTotalPieChart = 0;
							
							List<String> states = rg.dh.getAllStates();
							
							StringBuffer dynamicDataGeo = new StringBuffer("[\n");
							StringBuffer dynamicDataPie = new StringBuffer("[\n");
							dynamicDataGeo.append("['State','Positive','Negative'],\n");
							dynamicDataPie.append("['Sentiment','Percentage'],\n");
							for(String state: states){
								int statePositiveCount = 0;
								int stateNegativeCount = 0;
								int stateNeutralCount  = 0;
								
								int stateTotalGeoChart = 0;
								int stateTotalPieChart = 0;
								
								if(positiveCount != null){
									if(positiveCount.containsKey(state)){
										statePositiveCount = positiveCount.get(state);
										countryPositiveCount += statePositiveCount;
									}
								}
								if(negativeCount != null){
									if(negativeCount.containsKey(state)){
										stateNegativeCount = negativeCount.get(state);
										countryNegativeCount += stateNegativeCount;
									}
								}
								if(neutralCount != null){
									if(neutralCount.containsKey(state)){
										stateNeutralCount = neutralCount.get(state);
										countryNeutralCount += stateNeutralCount;
									}
								}	
									
								
								stateTotalGeoChart = statePositiveCount + stateNegativeCount;
								stateTotalPieChart = statePositiveCount + stateNegativeCount + stateNeutralCount;
								countryTotalPieChart += stateTotalPieChart;
								
								float statePositivePercentageGeoChart = (statePositiveCount/(float)stateTotalGeoChart)*100;
								float stateNegatvePercentageGeoChart = (stateNegativeCount/(float)stateTotalGeoChart)*100;
								
								dynamicDataGeo.append("['"+state+"',"+statePositivePercentageGeoChart+","+stateNegatvePercentageGeoChart+"],\n");
								
							}
							dynamicDataGeo.append("]");
							System.out.println("-------------------------------------");
							String finalGeoData = new String(dynamicDataGeo.toString().replace("NaN", "0"));
							
							System.out.println(finalGeoData);
							System.out.println("-------------------------------------");
							bw.write(finalGeoData);
							bw.write(rg.staticPartAfterGeoDynamicData.toString());
							dynamicDataGeo=null;
							
							float countryPositivePercentagePieChart =0.00f;
							float countryNegativePercentagePieChart =0.00f;
							float countryNeutralPercentagePieChart = 0.00f;
							
							countryPositivePercentagePieChart = (countryPositiveCount/(float)countryTotalPieChart)*100;
							countryNegativePercentagePieChart = (countryNegativeCount/(float)countryTotalPieChart)*100;
							countryNeutralPercentagePieChart = (countryNeutralCount/(float)countryTotalPieChart)*100;
							System.out.println(countryPositivePercentagePieChart+"% "+countryNegativePercentagePieChart+"% "+countryNeutralPercentagePieChart);
							dynamicDataPie.append("['Positive',"+countryPositivePercentagePieChart+"],\n");
							dynamicDataPie.append("['Negative',"+countryNegativePercentagePieChart+"],\n");
							dynamicDataPie.append("['Neutral',"+countryNeutralPercentagePieChart+"],\n");
							dynamicDataPie.append("]");
							String finalPieData = new String(dynamicDataPie.toString().replace("NaN", "0"));
							System.out.println(finalPieData);
							System.out.println("-------------------------------------");
							
							
							bw.write(finalPieData);
							bw.write(rg.staticPartAfterPieDynamicData.toString().replace("TREND_NAME", title));
							
							dynamicDataPie=null;
							
							
							bw.close();
						}
						//break;
					}
					rg.finishMasterReport();
					rg.writeMasterReport();
				}
				Thread.sleep(30000);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
	
	public void initiateMasterReport(){
		masterReport=new StringBuffer();
		masterReport.append("<html>\n");
		masterReport.append("  <head>\n");
		masterReport.append("   <title>Twitcher Home</title>\n");
		masterReport.append("  </head>\n");
		masterReport.append("<style>\n");
		masterReport.append("#bgd{\n");
		//masterReport.append("position:absolute;\n");
		//masterReport.append(  "top:0;\n");
		//masterReport.append("left:0;\n");
		//masterReport.append("bottom:0;\n");
		//masterReport.append(  "right:0;\n");
		masterReport.append("background-image:url('bg.jpg');font-family:'Comic Sans MS',cursive;\n");
		//masterReport.append("opacity:.1;\n");
		masterReport.append("}\n");
		masterReport.append("</style>");
		masterReport.append("  <body id='bgd'>\n");  
		masterReport.append("  <img src='banner.jpg'>\n");
	}
	
	public void finishMasterReport(){
		masterReport.append("  </body>\n");
		masterReport.append("  </html>\n");
	}
	
	public void writeMasterReport(){
		try{
			String filePath = "Reports/TwitcherHome.html";
			File file = new File(filePath);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(masterReport.toString());
			bw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public File createFileForTrend(String trend){
		String filePath = "Reports/Report_"+trend+".html";
		
		File file = new File(filePath);
		
//		try{
//			if (file.exists()) {
//				file.delete();
//			}
//			//file.createNewFile();
//			
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		
		return file;
	}
	
	public void createStaticReportParts(){
		staticPartBeforeGeoDynamicData = new StringBuffer();
		staticPartBeforeGeoDynamicData.append("<html>\n");
		staticPartBeforeGeoDynamicData.append("  <head>\n");
		staticPartBeforeGeoDynamicData.append("    <script type='text/javascript' src='https://www.google.com/jsapi'></script>\n");
		staticPartBeforeGeoDynamicData.append("    <script type='text/javascript'>\n");
		staticPartBeforeGeoDynamicData.append("     google.load('visualization', '1', {'packages': ['geochart']});\n");
		staticPartBeforeGeoDynamicData.append("     google.setOnLoadCallback(drawMarkersMap);\n\n");
		staticPartBeforeGeoDynamicData.append("      function drawMarkersMap() {\n");
		staticPartBeforeGeoDynamicData.append("      var data = google.visualization.arrayToDataTable(\n");
		
		staticPartAfterGeoDynamicData = new StringBuffer();
		staticPartAfterGeoDynamicData.append(");\n\n");
		staticPartAfterGeoDynamicData.append("		var options = {\n");
		staticPartAfterGeoDynamicData.append("			region: 'US',\n");
		staticPartAfterGeoDynamicData.append("			resolution: 'provinces',\n");
		staticPartAfterGeoDynamicData.append("     	displayMode: 'markers',\n");
		staticPartAfterGeoDynamicData.append("     	colorAxis: {colors: ['red', 'green']}\n");
		staticPartAfterGeoDynamicData.append("		};\n\n");
		staticPartAfterGeoDynamicData.append("		var chart = new google.visualization.GeoChart(document.getElementById('geo_chart'));\n");
		staticPartAfterGeoDynamicData.append("     chart.draw(data, options);\n");
		staticPartAfterGeoDynamicData.append("     };\n");
		staticPartAfterGeoDynamicData.append("    </script>\n\n");
		staticPartAfterGeoDynamicData.append("    <script type='text/javascript'>\n");
		staticPartAfterGeoDynamicData.append("      google.load('visualization', '1', {packages:['corechart']});\n");
		staticPartAfterGeoDynamicData.append("      google.setOnLoadCallback(drawChart);\n");
		staticPartAfterGeoDynamicData.append("      function drawChart() {\n");
		staticPartAfterGeoDynamicData.append("        var data = google.visualization.arrayToDataTable(\n");
		
		staticPartAfterPieDynamicData = new StringBuffer();
		staticPartAfterPieDynamicData.append(");\n\n");
		staticPartAfterPieDynamicData.append("var options = {\n");
		staticPartAfterPieDynamicData.append("          title: 'Sentiment Break Up'\n");
		staticPartAfterPieDynamicData.append("        };\n\n");
		staticPartAfterPieDynamicData.append("        var chart = new google.visualization.PieChart(document.getElementById('pie_chart'));\n");
		staticPartAfterPieDynamicData.append("        chart.draw(data, options);\n");
		staticPartAfterPieDynamicData.append("      }\n");
		staticPartAfterPieDynamicData.append("    </script>\n");
		staticPartAfterPieDynamicData.append("  </head>\n");
		staticPartAfterPieDynamicData.append("  <body>\n");
		staticPartAfterPieDynamicData.append("  	<h1>TREND_NAME</h1>\n");
		staticPartAfterPieDynamicData.append("    <table>\n");
		staticPartAfterPieDynamicData.append("    <tr>\n");
		staticPartAfterPieDynamicData.append("    <td><div id='geo_chart' style='width: 800px; height: 500px;'></div></td>\n");
		staticPartAfterPieDynamicData.append("    <td><div id='pie_chart' style='width: 500px; height: 500px;'></div></td>\n");
		staticPartAfterPieDynamicData.append("    </tr>\n");
		staticPartAfterPieDynamicData.append("    </table>\n");
		staticPartAfterPieDynamicData.append("  </body>\n");
		staticPartAfterPieDynamicData.append("</html>\n\n");
		
	}
	
}
