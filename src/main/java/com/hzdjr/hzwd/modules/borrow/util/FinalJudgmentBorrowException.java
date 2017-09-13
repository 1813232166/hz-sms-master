package com.hzdjr.hzwd.modules.borrow.util;

/**
 * 终审标的异常处理类 
 * @author XJin
 *
 */
public class FinalJudgmentBorrowException extends RuntimeException {

	private String intfRetnJson;
	private static final long serialVersionUID = 1L;
	
	public FinalJudgmentBorrowException(String errMessage,String intfRetnJson){
		super(errMessage);
		this.intfRetnJson = intfRetnJson;
	}

	public String getIntfRetnJson() {
		return intfRetnJson;
	}

	public void setIntfRetnJson(String intfRetnJson) {
		this.intfRetnJson = intfRetnJson;
	}
	
}
