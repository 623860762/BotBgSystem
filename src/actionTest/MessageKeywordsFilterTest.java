package actionTest;import PostTest.SendRequest;import weibo4j.org.json.JSONObject;public class MessageKeywordsFilterTest {	/**	 * @param args	 */	public static void main(String[] args) throws Exception{		String CorGroupInfo = TestConfig.getValue("OnLineHost")+",CorpusManger";		String MsgGroupInfo = TestConfig.getValue("OnLineHost")+",MessageCenter";		String url = TestConfig.getValue("OnLineApi")+"/messageSearch";						JSONObject json = new JSONObject();		json.put("UID", "2714901047");		json.put("Keywords", "放假");		json.put("StartTime", "20130101");		json.put("EndTime", "20130603");		json.put("CID", "0");		json.put("Page", "1");		json.put("PageSize", "20");		json.put("MsgGroupInfo", MsgGroupInfo);				System.out.println("参数:"+json.toString(5));				String strRet = SendRequest.sendPost(url, "data="+json.toString());				JSONObject jsonObject = new JSONObject(strRet);		System.out.println("MessageKeywordsFilterTest:"+jsonObject.toString(5));	}}