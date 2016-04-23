package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardService;
import model.BoardVO;

public class UpdatePostingController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int no=Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVO vo=new BoardVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		BoardDAO.getInstance().updatePosting(vo);			
		String path="redirect:DispatcherServlet?command=showContent&no="+vo.getNo();
		return new ModelAndView(path);
	}

}
