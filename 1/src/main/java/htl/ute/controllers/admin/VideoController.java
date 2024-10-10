package  htl.ute.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import htl.ute.entity.*;
import htl.ute.services.ICategoryService;
import htl.ute.services.impl.CategoryService;
import htl.ute.services.impl.VideoService;
import htl.ute.utils.Constant;
import htl.ute.services.IVideoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/edit", "/admin/video/update", "/admin/video/insert",
		"/admin/video/add", "/admin/video/delete" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IVideoService vidService = new VideoService();
	ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();

		if (url.contains("videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listvid", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {
			String id = req.getParameter("id");
			Video video = vidService.findById(id);
			List<Category> list = cateService.findAll();

			req.setAttribute("listcate", list);

			req.setAttribute("vid", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {
			List<Category> list = cateService.findAll();

			req.setAttribute("listcate", list);

			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id");
			try {
				vidService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();

		if (url.contains("/admin/video/update")) {
			String videoid = req.getParameter("videoid");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int views = Integer.parseInt(req.getParameter("views"));
			int active = Integer.parseInt(req.getParameter("active"));

			Video video = new Video();
			video.setVideoId(videoid);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(views);
			video.setActive(active);

			// Lưu hình cũ
			Video vidold = vidService.findById(videoid);
			String fileold = vidold.getPoster();

			// Xử lý images
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					video.setPoster(fname);
				} else {
					video.setPoster(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			vidService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("/admin/video/insert")) {
			String videoId = req.getParameter("videoId");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int views = Integer.parseInt(req.getParameter("views"));
			int active = Integer.parseInt(req.getParameter("active"));

			Video video = new Video();
			video.setVideoId(videoId);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(views);
			video.setActive(active);

			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					video.setPoster(fname);
				} else {
					video.setPoster("avata.png"); // Hoặc giá trị mặc định khác
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			vidService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
}