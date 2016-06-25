package weibo4j;

import java.util.ArrayList;
import java.util.HashMap;

import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class ShortUrl extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**  
	 * 长链接转为短链接
	 *
	 *
	 */
	public JSONObject longToShortUrl (String url_long) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/shorten.json",new PostParameter[] {
			new PostParameter("url_long",url_long)
		}).asJSONObject();
	}
	
	/**
	 * 短链接转为长链接
	 * 
	 */
	public JSONObject shortToLongUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/expand.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	
	public JSONObject shortToLongUrlList(ArrayList<String> longurls) throws WeiboException {
		PostParameter[] pars =  new PostParameter[longurls.size()];
		for(int i=0;i<longurls.size();i++){
			pars[i] = new PostParameter("url_long", longurls.get(i));
		}
		return client.post(
					WeiboConfig.getValue("baseURL")	+ "short_url/shorten.json",//http://i2.api.weibo.com/2/short_url/shorten.json
					pars
					).asJSONObject();
	}
	
	public HashMap<String, String> getShortUrlMap(ArrayList<String> longurls){
		HashMap<String, String> urlMap = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		try {
			 json = shortToLongUrlList(longurls);
			JSONArray urls = json.getJSONArray("urls");
			if(urls!=null){
				for(int i=0;i<urls.length();i++){
					JSONObject shortUrl = (JSONObject)urls.get(i);
					String url_long = shortUrl.getString("url_long"); 
					String url_short = shortUrl.getString("url_short");
					urlMap.put(url_long, url_short);
				}
			}else{
				throw new WeiboException(json.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlMap;
	}
	
	/**
	 * 获取短链接的总点击数
	 * 
	 * 
	 */
	public JSONObject clicksOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/clicks.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 获取一个短链接点击的referer来源和数量 
	 * 
	 * 
	 */
	public JSONObject referersOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/referers.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 
	 * 获取一个短链接点击的地区来源和数量 
	 * 
	 */
	public JSONObject locationsOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/locations.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 获取短链接在微博上的微博分享数 
	 * 
	 * 
	 * 
	 */
	public JSONObject shareCountsOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/share/counts.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取包含指定单个短链接的最新微博内容 
	 * 
	 * 
	 */
	public JSONObject statusesContentUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/share/statuses.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取短链接在微博上的微博评论数 
	 * 
	 * 
	 */
	public JSONObject commentCountOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/comment/counts.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取包含指定单个短链接的最新微博评论 
	 * 
	 */
	public JSONObject commentsContentUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/comment/comments.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
}
