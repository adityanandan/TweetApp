package com.tweetapp.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweet {
	@Id
	private String id;
	@Size(min=2,max=140)
	private String tweetInfo;
	@CreatedDate
	private LocalDateTime postDate;
	private long likes;
	private User user;
	private List<Tweet> replies;
	@Size(max=50)
	private String tweetTag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTweetInfo() {
		return tweetInfo;
	}

	public void setTweetInfo(String tweetInfo) {
		this.tweetInfo = tweetInfo;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tweet> getReplies() {
		return replies;
	}

	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}

	public String getTweetTag() {
		return tweetTag;
	}

	public void setTweetTag(String tweetTag) {
		this.tweetTag = tweetTag;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetInfo=" + tweetInfo + ", postDate=" + postDate + ", likes=" + likes
				+ ", user=" + user + ", replies=" + replies + ", tweetTag=" + tweetTag + "]";
	}

}
