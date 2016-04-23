package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardService;
import model.BoardVO;

public class ShowContentController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int no=Integer.parseInt(request.getParameter("no"));
		BoardVO vo = BoardService.getInstance().getPostingByNoNotHit(no);		
		return new ModelAndView("board/show_content.jsp","bvo", vo);
	}
}






