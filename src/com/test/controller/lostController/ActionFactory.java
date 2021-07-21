package com.test.controller.lostController;

import com.test.controller.lostController.action.Action;
import com.test.controller.lostController.action.DeleteLostArticleAction;
import com.test.controller.lostController.action.DeleteReplyOnRegAction;
import com.test.controller.lostController.action.LostAnimalAction;
import com.test.controller.lostController.action.LostMainAction;
import com.test.controller.lostController.action.ReReplyLostAnimalAction;
import com.test.controller.lostController.action.RegisterLostAction;
import com.test.controller.lostController.action.RegisterLostArticleAction;
import com.test.controller.lostController.action.RegisterReplyAction;
import com.test.controller.lostController.action.SearchByProvAction;
import com.test.controller.lostController.action.ShowLostMineAction;
import com.test.controller.lostController.action.UpdateLostAnimalArticleAction;
import com.test.controller.lostController.action.UpdateLostArticleAction;
import com.test.controller.lostController.action.UpdateReplyOnRegLostAction;

public class ActionFactory {
	
	private ActionFactory() {
		super();
	}
	
	private static ActionFactory af = new ActionFactory();
	
	//singleton
	public static ActionFactory getInstance() {
		return af;
	}
	
	public Action getAction(String cmd) {
		Action action = null;

		if(cmd.equals("lostMain")) {
			action = new LostMainAction();
		}else if(cmd.equals("registerLost")) {
			action = new RegisterLostAction();
		}else if(cmd.equals("registerLostArticle")) {
			action = new RegisterLostArticleAction();
		}else if(cmd.equals("lostAnimal")) {
			action = new LostAnimalAction();
		}else if(cmd.equals("registerReply")) {
			action = new RegisterReplyAction();
		}else if(cmd.equals("reReplyLostAnimal")) {
			action = new ReReplyLostAnimalAction();
		}else if(cmd.equals("updateReplyOnRegLost")) {
			action = new UpdateReplyOnRegLostAction();
		}else if(cmd.equals("deleteReplyOnReg")) {
			action = new DeleteReplyOnRegAction();
		}else if(cmd.equals("updateLostArticle")) {
			//글 수정 작업
			action = new UpdateLostArticleAction();
		}else if(cmd.equals("updateLostAnimalArticle")) {
			action = new UpdateLostAnimalArticleAction();
		}else if(cmd.equals("deleteLostArticle")) {
			//글삭제
			action = new DeleteLostArticleAction();
		}else if(cmd.equals("searchByProv")) {
			//지역별 조회
			action = new SearchByProvAction();
		}else if(cmd.equals("showLostMine")) {
			//회원이 작성한 게시글 보여주기
			action = new ShowLostMineAction();
		}
		
		return action;
	}
	

}
