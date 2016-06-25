package weibo4j.weibot;

import weibo4j.model.Source;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.Visible;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class Geo {
	double longitude = -1;
	double latitude = -1;
	int city=-1;
	int province = -1;
	String city_name="";
	String province_name="";
	String address = "";
	
	private void constructJson(JSONObject json) throws WeiboException {
		try {
			longitude = json.getDouble("longitude");
			latitude = json.getDouble("latitude");
			city = json.getInt("city");
			province = json.getInt("province");
			
			city_name = json.getString("city_name");
			province_name = json.getString("province_name");
			address = json.getString("address");

		} catch (JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}
	
	public Geo(JSONObject json)throws WeiboException, JSONException{
		constructJson(json);
	}
	public Geo(String str) throws WeiboException, JSONException {
		JSONObject json = new JSONObject(str);
		constructJson(json);
	}
	
	public double getLongitude(){
		return this.longitude;
	}
	public double getLatitude(){
		return this.latitude;
	}
	public int getCity(){
		return this.city;
	}
	public int getProvince(){
		return this.province;
	}
	public final String getCityName(){
		return this.city_name;
	}
	public final String getProvinceName(){
		return this.province_name;
	}
	public final String getAddress(){
		return this.address;
	}
	
}
