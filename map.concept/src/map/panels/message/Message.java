package map.panels.message;

import java.util.List;

public class Message {

	private String author;
	private List<String> content;
	private int size;
	private long creationTime;

	public Message(String author, List<String> content) {
		this.size = content.size();
		this.author = author;
		this.content = content;
		this.creationTime = System.currentTimeMillis();
	}

	public String getAuthor() {
		return author;
	}

	public List<String> getContent() {
		return content;
	}

	public int getSize() {
		return size;
	}

	public long getCreationTime() {
		return creationTime;
	}

}