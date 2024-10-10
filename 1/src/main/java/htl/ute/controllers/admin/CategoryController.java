package htl.ute.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import htl.ute.services.ICategoryService;
import htl.ute.services.impl.CategoryService;
import htl.ute.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import htl.ute.utils.Constant;


@MultipartConfig(fileSizeThreshold = 1024*1024,maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add" ,"/admin/category/insert","/admin/category/edit","/admin/category/update","/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ICategoryService cateService = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/categories")) {
			List<Category> list = cateService.findAll();
			request.setAttribute("listcate", list);
			request.getRequestDispatcher("/views/admin/category-list.jsp").forward(request, response);
		} else if (url.contains("/admin/category/add")) {
			request.getRequestDispatcher("/views/admin/category-add.jsp").forward(request, response);
		} else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = cateService.findById(id);
			request.setAttribute("cate", category);
			request.getRequestDispatcher("/views/admin/category-edit.jsp").forward(request, response);
		} else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/admin/categories");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();
		if(url.contains("/admin/category/insert")) {
			//lấy dữ liệu từ views
			String categoryname = request.getParameter("categoryname");
			int status =Integer.parseInt(request.getParameter("status")) ;
			String images  = request.getParameter("images");
			//đưa vào model
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(status);
			//xử lí upload file
			String fname="";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images1");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(",");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis()+ "," + ext;
					part.write(uploadPath + "/" + fname) ;
					category.setImages(fname);
				}else if(images != null) {
					category.setImages(images);
					
				}else {
					category.setImages("avartar.png");
				}
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			
			//truyền model vào insert
			cateService.insert(category);
			//Trả về views
			response.sendRedirect(request.getContextPath()+"/admin/category/add");
			
		}else if(url.contains("update")) {
			int categoryid = Integer.parseInt(request.getParameter("categoryid"));
			String categoryname = request.getParameter("categoryname");
			int status = Integer.parseInt(request.getParameter("status"));	
			Category category = new Category();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);
			// luu hinh anh cu
			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// Xu ly images
			String fname ="";
			String uploadPath = Constant.DIR;
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images");
				if(part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					category.setImages(fname);
				}else {
					category.setImages(fileold);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			cateService.update(category);
			response.sendRedirect(request.getContextPath() + "/admin/categories");			
		}
	}
}
