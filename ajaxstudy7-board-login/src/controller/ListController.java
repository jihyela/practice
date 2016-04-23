package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardService;
import model.BoardVO;
import model.ListVO;
import model.PagingBean;

public class ListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int total = BoardDAO.getInstance().totalCount();
		int pageNo= 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		ArrayList<BoardVO> list = BoardService.getInstance().getPostingList(request.getParameter("pageNo"));
		PagingBean pagingBean = new PagingBean(total, pageNo);
		ListVO lvo = new ListVO(list, pagingBean);
		return new ModelAndView("board/list.jsp","lvo", lvo);
	}

}
