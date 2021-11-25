package model;

import java.util.List;

public class PostMutterLogic {
	public void execute(Mutter mutter, List<Mutter> mutterList) {
		//つぶやきを先頭に追加
		mutterList.add(0, mutter);
	}
}
