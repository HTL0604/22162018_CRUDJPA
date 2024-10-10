package htl.ute.services.impl;

import java.util.List;

import htl.ute.dao.impl.VideoDao;
import htl.ute.dao.IVideoDao;
import htl.ute.entity.Video;
import htl.ute.services.IVideoService;

public class VideoService implements IVideoService{

	IVideoDao vidDao = new VideoDao();

	public int count() {
		return vidDao.count();

	}

	public List<Video> findAll(int page, int pagesize) {
		return vidDao.findAll(page, pagesize);

	}

	public List<Video> findByVidTitle(String title) {
		return vidDao.findByVideotitle(title);

	}

	public List<Video> findAll() {
		return vidDao.findAll();

	}

	public Video findById(String id) {
		return vidDao.findById(id);

	}

	public void delete(String id) throws Exception {
		vidDao.delete(id);
		
	}

	public void update(Video video) {
		vidDao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		vidDao.insert(video);
		
	}


}