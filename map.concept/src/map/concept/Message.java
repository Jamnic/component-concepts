package map.concept;

import java.util.List;

public class Message {

	private String author;
	private List<String> content;
	private int size;

	public Message(String author, List<String> content) {
		this.size = content.size();
		this.author = author;
		this.content = content;
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
}