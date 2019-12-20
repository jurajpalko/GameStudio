package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;

@Component
@Transactional
public class RatingServiceJPA implements RatingService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setRating(Rating rating) {
		try {
			Rating dbRating= (Rating)entityManager
					.createQuery("select r from Rating r where r.owner = :owner and r.game = :game")
					.setParameter("owner", rating.getOwner())
					.setParameter("game", rating.getGame())
					.getSingleResult();
			dbRating.setValue(rating.getValue());
		} catch (NoResultException e) {
			entityManager.persist(rating);
		}
		
	}

	@Override
	public double getAverateRating(String game) {
		double result = 0.0;
		try {
			result = (double)entityManager
					.createQuery("select round(avg(r.rating),1) from Rating r where r.game= :game")
					.setParameter("game", game).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
