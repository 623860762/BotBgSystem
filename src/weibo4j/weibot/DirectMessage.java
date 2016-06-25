package weibo4j.weibot;

import weibo4j.http.Response;
import weibo4j.model.Comment;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.model.WeiboResponse;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






public class DirectMessage extends WeiboResponse implements java.io.Serializable {
	private static final long serialVersionUID = 2372011191310628589L;
	private Date createdAt;                    //私信时间
	private long id;                           //私信id
	private String mid;						   //私信id
	private String idstr;					   //私信id
	private String text;                       //私信内容
	private String source;                     //内容来源
	private Comment replycomment = null;       //回复的私信内容
	private User sender = null;                  //发送者用户对象
	private User recipient = null;               //接受者用户对象
	private Status status = null;              //Status对象
	//-------------------
	private String geo;                                  //地理信息，保存经纬度，没有时不返回此字段
	private double latitude = -1;                        //纬度
	private double longitude = -1;                       //经度


	/*package*/
	public DirectMessage(Response res) throws WeiboException {
		JSONObject json =res.asJSONObject();
		try {
			id = json.getLong("id");
			mid = json.getString("mid");
			idstr = json.getString("idstr");
			text = json.getString("text");
			source = json.getString("source");
			createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
			if(!json.isNull("sender"))
				sender = new User(json.getJSONObject("sender"));
			if(!json.isNull("recipient"))
				recipient = new User(json.getJSONObject("recipient"));
			if(!json.isNull("status"))
				status = new Status(json.getJSONObject("status"));
			if(!json.isNull("reply_comment"))
				replycomment = (new Comment(json.getJSONObject("reply_comment")));
			geo= json.getString("geo");
			if(geo!=null &&!"".equals(geo) &&!"null".equals(geo)){
				getGeoInfo(geo);
			}

		} catch (JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}	
	
	private void getGeoInfo(String geo) {
		StringBuffer value= new StringBuffer();
		for(char c:geo.toCharArray()){
			if(c>45&&c<58){
				value.append(c);
			}
			if(c==44){
				if(value.length()>0){
					latitude=Double.parseDouble(value.toString());
					value.delete(0, value.length());
				}
			}
		}
		longitude=Double.parseDouble(value.toString());
	}
	
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	

	public DirectMessage(JSONObject json)throws WeiboException, JSONException{
		id = json.getLong("id");
		mid = json.getString("mid");
		idstr = json.getString("idstr");
		text = json.getString("text");
		source = json.getString("source");
		createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
		if(!json.isNull("sender"))
			sender = new User(json.getJSONObject("sender"));
		if(!json.isNull("recipient"))
			recipient = new User(json.getJSONObject("recipient"));

		if(!json.isNull("status"))
			status = new Status(json.getJSONObject("status"));	
		if(!json.isNull("reply_comment"))
			replycomment = (new Comment(json.getJSONObject("reply_comment")));
		geo= json.getString("geo");
		if(geo!=null &&!"".equals(geo) &&!"null".equals(geo)){
			getGeoInfo(geo);
		}
		
	}

	public DirectMessage(String str) throws WeiboException, JSONException {
		// StatusStream uses this constructor
		super();
		JSONObject json = new JSONObject(str);
		id = json.getLong("id");
		mid = json.getString("mid");
		idstr = json.getString("idstr");
		text = json.getString("text");
		source = json.getString("source");
		createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
		if(!json.isNull("sender"))
			sender = new User(json.getJSONObject("sender"));
		if(!json.isNull("recipient"))
			recipient = new User(json.getJSONObject("recipient"));
		if(!json.isNull("status"))
			status = new Status(json.getJSONObject("status"));	
		if(!json.isNull("reply_comment"))
			replycomment = (new Comment(json.getJSONObject("reply_comment")));
		geo= json.getString("geo");
		if(geo!=null &&!"".equals(geo) &&!"null".equals(geo)){
			getGeoInfo(geo);
		}
		
	}

	public static DirectMessageWapper constructWapperMessages(Response res) throws WeiboException {
		JSONObject json = res.asJSONObject(); //asJSONArray();
		try {
			JSONArray messages = json.getJSONArray("direct_messages");
			int size = messages.length();
			List<DirectMessage> message = new ArrayList<DirectMessage>(size);
			for (int i = 0; i < size; i++) {
				message.add(new DirectMessage(messages.getJSONObject(i)));
			}
			long previousCursor = json.getLong("previous_curosr");
			long nextCursor = json.getLong("next_cursor");
			long totalNumber = json.getLong("total_number");
			String hasvisible = json.getString("hasvisible");
			return new DirectMessageWapper(message, previousCursor, nextCursor,totalNumber,hasvisible);
		} catch (JSONException jsone) {
			throw new WeiboException(jsone);
		}
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}

	public Comment getReplycomment() {
		return replycomment;
	}

	public User getSender() {
		return this.sender;
	}

	public Status getStatus() {
		return status;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setReplycomment(Comment replycomment) {
		this.replycomment = replycomment;
	}

	public void setUser(User sender) {
		this.sender = sender;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectMessage other = (DirectMessage) obj;
		if (this.id != other.id)
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "DirectMessage [createdAt=" + createdAt + ", id=" + id 
				+ ", mid="+ mid + ", idstr=" + idstr + ", text=" + text 
				+ ", source=" + source + ", replycomment=" + replycomment 
				+ ", sender=" + sender + ", recipient=" + this.recipient
				+ ", status=" + status +"]";
	}

}
