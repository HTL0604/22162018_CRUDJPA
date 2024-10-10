package htl.ute.dao.impl;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import htl.ute.config.JPAConfig;
import htl.ute.dao.IVideoDao;
import htl.ute.entity.Video;

public class VideoDao implements IVideoDao {

@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT count(v) FROM Video v";

		Query query = enma.createQuery(jpql);

		return ((Long) query.getSingleResult()).intValue();

	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		query.setFirstResult(page * pagesize);

		query.setMaxResults(pagesize);

		return query.getResultList();
	}

	@Override
	public List<Video> findByVideotitle(String title) {
		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT v FROM Video v WHERE v.title like :title";

		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);

		query.setParameter("vidtitle", "%" + title + "%");

		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		return query.getResultList();
	}

	@Override
	public Video findById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();

		Video video = enma.find(Video.class, id);

		return video;
	}

	@Override
	public void delete(String id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();

			Video video = enma.find(Video.class, id);

			if (video != null) {
				enma.remove(video);
			} else {
				throw new Exception("Không tìm thấy video với ID: " + id);
			}

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			// gọi phuong thức để insert, update, delete

			enma.merge(video);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}

	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			// gọi phuong thức để insert, update, delete

			// Persist video
			enma.persist(video);
			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}

	}
	}