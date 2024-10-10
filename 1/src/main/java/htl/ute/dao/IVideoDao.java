package htl.ute.dao;

import java.util.List;

import htl.ute.entity.Video;

public interface IVideoDao {
    int count();

    List<Video> findAll(int page, int pagesize);

    List<Video> findByVideotitle(String title);

    List<Video> findAll();

    Video findById(String id);

    void delete(String id) throws Exception;

    void update(Video video);

    void insert(Video video);
}