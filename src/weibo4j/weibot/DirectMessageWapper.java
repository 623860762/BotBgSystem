package weibo4j.weibot;

import java.util.List;

public class DirectMessageWapper {
	private List<DirectMessage> directMessages;

	private long previousCursor;

	private long nextCursor;
	
	private long totalNumber;
	
	private String hasvisible;
	
	public DirectMessageWapper(List<DirectMessage> directMessages, long previousCursor,
			long nextCursor, long totalNumber,String hasvisible) {
		this.directMessages = directMessages;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber = totalNumber;
		this.hasvisible = hasvisible;
	}

	public List<DirectMessage> getDirectMessages() {
		return directMessages;
	}

	public void setDirectMessages(List<DirectMessage> comments) {
		this.directMessages = comments;
	}

	public long getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(long previousCursor) {
		this.previousCursor = previousCursor;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getHasvisible() {
		return hasvisible;
	}

	public void setHasvisible(String hasvisible) {
		this.hasvisible = hasvisible;
	}
}
