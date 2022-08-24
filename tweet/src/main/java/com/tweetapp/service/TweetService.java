package com.tweetapp.service;

import java.util.List;

import com.tweetapp.exception.TweetException;
import com.tweetapp.models.Tweet;

public interface TweetService {
	
	Tweet postTweet(Tweet tweet);

	Tweet postTweetByUsername(Tweet tweet, String username) throws TweetException;

	Tweet editTweet(Tweet tweet);

	Tweet likeTweet(Tweet tweet);

	Tweet replyTweet(Tweet parentTweet, Tweet replyTweet);

	void deleteTweet(Tweet tweet);

	List<Tweet> getAllTweets();

	List<Tweet> getAllTweetsByUsername(String username);

	void replyTweetById(Tweet replyTweet, String parentTweetId) throws TweetException;

	void deleteTweetById(String tweetId);

	void likeTweetById(String tweetId);

}
