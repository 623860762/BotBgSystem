package actionTest;import PostTest.SendRequest;import weibo4j.org.json.JSONObject;public class CorpusKeywordsFilterTest {	public static void main(String[] args) throws Exception {		String CorGroupInfo = TestConfig.getValue("TestHost") + ",CorpusManger";		String MsgGroupInfo = TestConfig.getValue("TestHost") + ",MessageCenter";		String url = TestConfig.getValue("TestApi") + "/corpusSearch";		JSONObject json = new JSONObject();		json.put("UID", TestConfig.getValue("TestUID"));		json.put("Keywords", "知道");		json.put("CID", "2");		json.put("Page", "1");		json.put("PageSize", "10");		json.put("CorGroupInfo", CorGroupInfo);		System.out.println("参数+:"+json.toString(5));		String strRet = SendRequest.sendPost(				url,				"data=" + json.toString());						JSONObject jsonObject = new JSONObject(strRet);		System.out.println("CorpusKeywordsFilterTest:" + jsonObject.toString(5));	}}