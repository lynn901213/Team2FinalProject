package CSE360;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/*
CSE360 Summer 2017
Kyle Sun
Jingyi Li
Lin Sun
*/

public class CompanionBrain {
   JSONObject jsonObj;
   private static java.lang.String key;
   
   /**
   * Create a GetWeather with the given API key for DarkSky.
   */
   public CompanionBrain(java.lang.String wKey) {
      key = wKey;
      try {
         setWeather(0, 0);
      } catch (IOException | JSONException e) {
         java.lang.System.out.println(e);
         java.lang.System.exit(1);
      }
   }
   /**
   * Sets the weather info to the location based on given double parameters for
   * Latitude and Longitude.
   */
   public void setWeather(double lat, double lon) throws IOException, JSONException{
      java.lang.String weaUrl = "https://api.darksky.net/forecast/" + key + "/" + lat + "," + lon;
      URL url = new URL(weaUrl);
      URLConnection wc = url.openConnection();
      BufferedReader in = new BufferedReader(
      new InputStreamReader(wc.getInputStream()));
      java.lang.String inputLine;
      java.lang.String json = "";
      // Read response and add to string
      while ((inputLine = in.readLine()) != null) {
         json = json + inputLine;
      }
      in.close();
      jsonObj = new JSONObject(json);
   }
   
   
   /**
   * Returns the current condition as a String based on DarkSky's formatting.
   */
   public java.lang.String getCondition() throws JSONException{
      return jsonObj.getJSONObject("currently").getString("icon");
   }
   
   /**
   * Returns information displayed as a Double based on DarkSky's formatting
   * and the given string of information requested.
   */
   public double getDoubleInfo(java.lang.String info) throws JSONException{
      return jsonObj.getJSONObject("currently").getDouble(info);
   }
   
   /**
   * Returns information displayed as an Integer based on DarkSky's formatting
   * and the given string of information requested.
   */
   public int getIntInfo(java.lang.String info) throws JSONException{
      return jsonObj.getJSONObject("currently").getInt(info);
   }
}

