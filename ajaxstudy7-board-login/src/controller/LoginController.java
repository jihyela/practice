package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		ModelAndView mv = null;
		MemberVO vo = MemberDAO.getInstance().login(id, password);
		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", vo);
			mv = new ModelAndView("DispatcherServlet?command=list");
		} else {
			mv = new ModelAndView("board/login_fail.jsp");
		}
		return mv;
	}

}
