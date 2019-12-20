package sk.tsystems.gamestudio.service;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;

public interface RatingService {

	void setRating(Rating rating);

	double getAverateRating(String game);
}
