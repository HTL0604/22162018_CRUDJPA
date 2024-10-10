package htl.ute.services;

import java.util.List;

import  htl.ute.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVidTitle(String title);

	List<Video> findAll();

	Video findById(String id);

	void delete(String id) throws Exception;

	void update(Video video);

	void insert(Video video);
}