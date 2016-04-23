package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

public class DeletePostingController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO.getInstance().deletePosting(no);
		return new ModelAndView("DispatcherServlet?command=list&pageNo=1");
	}

}
