package weibo4j.weibot;

import java.util.List;
import java.util.ArrayList;

import weibo4j.Weibo;
import weibo4j.model.Paging;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class DirectMessages extends Weibo{
	/*----------------------------评论接口----------------------------------------*/

	private static final long serialVersionUID = -5984521691644570333L;


	/**
	 * 返回收到的新私信列表
	 * 
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://i.api.weibo.com/2/direct_messages.json">direct_messages</a>
	 * @since JDK 1.5
	 */
	public DirectMessageWapper getNewDirectMessages() throws WeiboException {
		return DirectMessage.constructWapperMessages(client.get(
				WeiboConfig.getValue("baseURL") + "direct_messages.json",
				new PostParameter[] {}));
	}

	public DirectMessageWapper getNewDirectMessages(Long since_id) throws WeiboException {
		DirectMessageWapper  dmWapper = null;
		List<DirectMessage> directMessages = new ArrayList<DirectMessage>(); 
		int page_id=1;
		boolean keep=true;
		while(keep){
			dmWapper = this.getNewDirectMessages(since_id, page_id);
			if(dmWapper.getDirectMessages().size()<200 && directMessages.size()==0 ){
				//返回少于200，且之前无内容
				keep = false;
			}else if(dmWapper.getDirectMessages().size()<200 && directMessages.size()>0 ){
				//返回少于200，但之前有内容
				dmWapper.getDirectMessages().addAll(directMessages);//追加
				keep = false;				
			}else{
				//返回达到200，无论之前有无内容
				directMessages.addAll(dmWapper.getDirectMessages());
				page_id++;
			}
		}
		return dmWapper;
	}
	
	public DirectMessageWapper getNewDirectMessages(Long since_id,int page_id) throws WeiboException {
		return DirectMessage.constructWapperMessages(client.get(
				WeiboConfig.getValue("baseURL") + "direct_messages.json",
				new PostParameter[] {
					new PostParameter("since_id", since_id.toString()),
					new PostParameter("count", "200"),
					new PostParameter("page", String.valueOf(page_id) )
					}
			));
	}
	
	
	/**
	 * 根据微博ID返回某条微博的评论列表
	 * 
	 * @param id
	 *            需要查询的微博ID
	 * @param count
	 *            单页返回的记录条数，默认为50。
	 * @param page
	 *            返回结果的页码，默认为1。
	 * @param filter_by_author
	 *            作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/direct_messages/conversation.json">direct_messages/conversation</a>
	 * @since JDK 1.5
	 */
	public DirectMessageWapper getDirectMessagesById(String id,long since_id,long max_id, int count,  Paging page,
			Integer filter_by_author) throws WeiboException {
		return DirectMessage.constructWapperMessages(client.get(
				WeiboConfig.getValue("baseURL") + "direct_messages/conversation.json",
				new PostParameter[] {
						new PostParameter("uid", id),
						new PostParameter("since_id", since_id),
						new PostParameter("max_id", max_id),
						new PostParameter("count", count),
						new PostParameter("page", page.getPage() )}, page));
	}


}
