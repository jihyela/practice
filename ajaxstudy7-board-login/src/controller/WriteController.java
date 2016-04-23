package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardVO;
import model.MemberVO;

public class WriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("mvo");
		BoardVO vo2 = new BoardVO(title, content, vo.getId());
		BoardDAO.getInstance().write(vo2);
		
		String path = "DispatcherServlet?command=showContent&no="+vo2.getNo();
		return new ModelAndView(path);
	}

}
