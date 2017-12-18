package com.zyj.exception.util;

/**
 * Ĭ���쳣��ʽ������
 * @author zengyuanjun
 *
 */
public class DefaultExceptionMsgHandler implements ExceptionMessageFormat {

	private DefaultExceptionMsgHandler() {
	}
	
	private static class SingletonHolder{
		private static final DefaultExceptionMsgHandler instance = new DefaultExceptionMsgHandler();
	}
	
	public static DefaultExceptionMsgHandler getInstance(){
		return SingletonHolder.instance;
	}
	
	/**
	 * ��ʽ���쳣��Ϣ
	 */
	@Override
	public String formate(Exception e) {
		return e.getMessage() + "\n";
	}

}
