package weibo4j.weibot;

import java.util.ArrayList;
import java.util.List;

import weibo4j.Weibo;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class GeoPosition  extends Weibo{
	
	
	
	private static final long serialVersionUID = 9212415887996015620L;

	public ArrayList<Geo> getAddressByGeo(double pLongitude,double pLatitude) throws WeiboException {
		ArrayList<Geo> geoarr = new ArrayList<Geo>();
		String geo = String.valueOf(pLongitude)+","+String.valueOf(pLatitude);
		constructGeoArr(client.get(WeiboConfig
				.getValue("interURL") + "location/geo/geo_to_address.json",new PostParameter[]{
						new PostParameter("coordinate", geo)
						}),geoarr
		);
		return geoarr;
	}
	public ArrayList<Geo> getGeoByAddress(String address) throws WeiboException {
		ArrayList<Geo> geoarr = new ArrayList<Geo>();
		//String geo = String.valueOf(pLatitude)+","+String.valueOf(pLongitude);
		constructGeoArr(client.get(WeiboConfig
				.getValue("interURL") + "location/geo/address_to_geo.json",new PostParameter[]{
						new PostParameter("address", address)
						}),geoarr
		);
		return geoarr;
	}

	private int constructGeoArr(Response res,ArrayList<Geo> geos) throws WeiboException {
		JSONObject jsonRes = res.asJSONObject(); //asJSONArray();
		JSONArray jsonGeos = null;
		try {
			if(!jsonRes.isNull("geos")){				
				jsonGeos = jsonRes.getJSONArray("geos");
			}
			if(jsonGeos!=null){
				for(int i=0;i<jsonGeos.length();i++){
					geos.add(new Geo(jsonGeos.getJSONObject(i)));
				}
			}
			
			return geos.size();
		} catch (JSONException jsone) {
			throw new WeiboException(jsone);
		}
	}
}
