package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//アプリケーションスコープからつぶやきリストを取得
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		//つぶやきリストがなければ、新規に作成してアプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}
		
		//セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			//ログインしていなければ、トップページにリダイレクト
			response.sendRedirect("/docoTsubu");
		}else {
			//ログインしていれば、mainページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータからつぶやきを取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		if(text != null && text.length() != 0) {
			//アプリケーションスコープのつぶやきリストを取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
			
			//セッションスコープのユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			
			//つぶやきtextをつぶやきリストmutterListに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, mutterList);
			
			//アプリケーションスコープにつぶやきリストを保存
			application.setAttribute("mutterList", mutterList);
		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		
		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
