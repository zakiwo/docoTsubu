package model;
import dao.MutterDAO;

public class PostMutterLogic {
	//mutterを引数にcreate処理
	public void execute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
	}
}
