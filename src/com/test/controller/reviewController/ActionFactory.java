package com.test.controller.reviewController;

import com.test.controller.reviewController.action.Action;
import com.test.controller.reviewController.action.DeleteLostReviewAction;
import com.test.controller.reviewController.action.RegisterLostReviewArticleAction;
import com.test.controller.reviewController.action.RegisterReviewAction;
import com.test.controller.reviewController.action.ReviewLostMainAction;
import com.test.controller.reviewController.action.SearchReviewByProvAction;
import com.test.controller.reviewController.action.ShowMyLostReviewAction;
import com.test.controller.reviewController.action.ShowSpecificReviewAction;
import com.test.controller.reviewController.action.UpdateLostReviewAction;
import com.test.controller.reviewController.action.UpdateReviewFormAction;

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

		if(cmd.equals("registerReview")) {
			action = new RegisterReviewAction();
		}else if(cmd.equals("registerLostReviewArticle")) {
			action = new RegisterLostReviewArticleAction();
		}else if(cmd.equals("reviewLostMain")) {
			//리뷰메인페이지
			action = new ReviewLostMainAction();
		}else if(cmd.equals("showSpecificReview")) {
			//상세보기
			action = new ShowSpecificReviewAction();
		}else if(cmd.equals("updateReviewForm")) {
			//후기 수정하기
			action = new UpdateReviewFormAction();
		}else if(cmd.equals("updateLostReview")) {
			//후기 수정 결과 반영
			action = new UpdateLostReviewAction();
		}else if(cmd.equals("deleteLostReview")) {
			//후기글 삭제
			action = new DeleteLostReviewAction();
		}else if(cmd.equals("searchReviewByProv")) {
			//지역별 후기 조회
			action = new SearchReviewByProvAction();
		}else if(cmd.equals("showMyLostReview")) {
			//회원별 후기 조회
			action = new ShowMyLostReviewAction();
		}
		
		return action;
	}
	

}
