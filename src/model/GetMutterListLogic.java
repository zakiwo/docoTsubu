package model;

import java.util.List;
import dao.MutterDAO;

public class GetMutterListLogic {
	//daoのfindAll処理を行い、mutterListを返す
	public List<Mutter> execute(){
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}
}
