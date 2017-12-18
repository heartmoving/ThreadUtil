package com.zyj.exception.util;

/**
 * ��ջ������Ϣ��ʽ������
 * @author zengyuanjun
 *
 */
public class StackTraceMsgHandler implements ExceptionMessageFormat {

	private StackTraceMsgHandler() {
	}

	private static class SingletonHolder {
		private static final StackTraceMsgHandler instance = new StackTraceMsgHandler();
	}

	public static StackTraceMsgHandler getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * ��ʽ����ջ������Ϣ
	 */
	@Override
	public String formate(Exception e) {
		StackTraceElement[] stackTrace = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		for (StackTraceElement stackTraceElement : stackTrace) {
			sb.append("\tat " + stackTraceElement + "\n");
		}
		return sb.toString();
	}

}
