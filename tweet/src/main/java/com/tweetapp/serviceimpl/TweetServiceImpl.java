package com.tweetapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.TweetException;
import com.tweetapp.models.Tweet;
import com.tweetapp.models.User;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);

	@Override
	public Tweet postTweet(Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet editTweet(Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet likeTweet(Tweet tweet) {
		tweet.setLikes(tweet.getLikes() + 1);
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet replyTweet(Tweet parentTweet, Tweet replyTweet) {
		tweetRepository.save(replyTweet);
		List<Tweet> parentTweetReplies = parentTweet.getReplies();
		parentTweetReplies.add(replyTweet);
		parentTweet.setReplies(parentTweetReplies);
		tweetRepository.save(parentTweet);
		return replyTweet;
	}

	@Override
	public void deleteTweet(Tweet tweet) {
		tweetRepository.delete(tweet);
	}

	@Override
	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}

	@Override
	public List<Tweet> getAllTweetsByUsername(String username) {
		return tweetRepository.findByUserUsername(username);
	}

	@Override
	public Tweet postTweetByUsername(Tweet tweet, String username) throws TweetException {
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent()) {
			throw new TweetException("user not present");
		}
		tweet.setUser(user.get());
		return tweetRepository.save(tweet);

	}

	@Override
	public void deleteTweetById(String tweetId) {
		tweetRepository.deleteById(tweetId);

	}

	@Override
	public void replyTweetById(Tweet replyTweet, String parentTweetId) throws TweetException {
		Optional<Tweet> parentTweet = tweetRepository.findById(parentTweetId);
		if (parentTweet.isEmpty()){
			throw new TweetException("Incorrect or deleted parent tweet id.");
		}
		if(parentTweet.get().getReplies()==null) {
			List<Tweet> replies=new ArrayList<>();
			replies.add(replyTweet);
			parentTweet.get().setReplies(replies);
			tweetRepository.save(parentTweet.get());
		}
		else {
			List<Tweet> replies = parentTweet.get().getReplies();
			replies.add(replyTweet);
			parentTweet.get().setReplies(replies);
			tweetRepository.save(parentTweet.get());
		}

	}

	@Override
	public void likeTweetById(String tweetId) {
		Optional<Tweet> tweet = tweetRepository.findById(tweetId);
		if (tweet.isPresent()) {
			logger.info("Tweet with Id: {} is {}", tweetId, tweet.get());
			tweet.get().setLikes(tweet.get().getLikes() + 1);
			tweetRepository.save(tweet.get());
		}

	}

}