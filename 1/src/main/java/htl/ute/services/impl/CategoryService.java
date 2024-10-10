package htl.ute.services.impl;

import java.util.List;

import htl.ute.entity.Category;
import htl.ute.services.ICategoryService;
import htl.ute.dao.*;
import htl.ute.dao.impl.CategoryDao;

public class CategoryService implements ICategoryService {
	ICategoryDao dao = new CategoryDao();
	@Override
	public int count() {
		return dao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return dao.findAll();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		
		return dao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Category findById(int cateid) {
	
		return dao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		dao.delete(cateid);
	}

	@Override
	public void update(Category category) {
		dao.update(category);
	}

	@Override
	public void insert(Category category) {
		dao.insert(category);
	}

}
