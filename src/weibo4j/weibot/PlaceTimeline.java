package weibo4j.weibot;


import weibo4j.Weibo;
import weibo4j.model.Paging;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;



/**
 * weibo4j v1.0 版本中的 weibo4j.PlaceTimeline 类
 * 该类在v2.0版本中被移除
 * 现将该类封装到 weibo4j.weibot.PlaceTimeline
 * 以供继续使用
 * @author weibo4j
 *
 */
public class PlaceTimeline extends Weibo{

	/*----------------------------读取接口----------------------------------------*/

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 获取某个用户最新发表的微博列表
	 * 
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserPalaceTimeline() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "place/user_timeline.json"));
	}
	public StatusWapper getUserPalaceTimelineByUid(String uid) throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "place/user_timeline.json",new PostParameter[]{
			new PostParameter("uid", uid)
		}));
	}
	public StatusWapper getUserPalaceTimelineByName(String screen_name) throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "place/user_timeline.json",new PostParameter[]{
			new PostParameter("screen_name", screen_name)
		}));
	}
	/**
	 * 获取某个用户最新发表的微博列表
	 * 
	 * @param uid
	 *            需要查询的用户ID。
	 * @param screen_name
	 *            需要查询的用户昵称。
	 * @param count
	 *            单页返回的记录条数，默认为50。
	 * @param page
	 *            返回结果的页码，默认为1。
	 * @param base_app
	 *            是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
	 * @param feature
	 *            过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserPalaceTimelineByUid(String uid, Paging page,
			Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(client.get(
						WeiboConfig.getValue("baseURL")	+ "place/user_timeline.json",
						new PostParameter[] {
								new PostParameter("uid", uid),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}
	public StatusWapper getUserPalaceTimelineByName(String screen_name, Paging page,Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(client.get(
						WeiboConfig.getValue("baseURL")	+ "place/user_timeline.json",
						new PostParameter[] {
								new PostParameter("screen_name", screen_name),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}	
}
