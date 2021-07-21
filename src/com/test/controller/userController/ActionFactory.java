package com.test.controller.userController;

import com.test.controller.userController.action.Action;
import com.test.controller.userController.action.AuthCheckAction;
import com.test.controller.userController.action.LogOutAction;
import com.test.controller.userController.action.logInAction;

public class ActionFactory {
	
	//singleton design
	private ActionFactory() {
		
	}
	private static ActionFactory af = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return af;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if(cmd.equals("logIn")) {
			action = new logInAction();
		}else if(cmd.equals("authCheck")) {
			action = new AuthCheckAction();
		}else if(cmd.equals("logOut")) {
			action = new LogOutAction();
		}
		
		return action;
	}
	
}
