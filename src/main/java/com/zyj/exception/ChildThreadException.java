package com.zyj.exception;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zyj.exception.util.ExceptionMessageFormat;
import com.zyj.exception.util.factory.ExceptionMsgFormatFactory;

/**
 * ���߳��쳣�������̳߳����쳣ʱ�׳�
 * @author zengyuanjun
 */
public class ChildThreadException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5682825039992529875L;
	/**
	 * ���߳��쳣�б�
	 */
	private List<Exception> exceptionList;
	/**
	 * �쳣��Ϣ��ʽ������
	 */
	private ExceptionMessageFormat formatter;
	/**
	 * ��
	 */
	private Lock lock;

	public ChildThreadException() {
		super();
		initial();
	}

	public ChildThreadException(String message) {
		super(message);
		initial();
	}

	public ChildThreadException(String message, StackTraceElement[] stackTrace) {
		this(message);
		setStackTrace(stackTrace);
	}

	private void initial() {
		exceptionList = new ArrayList<Exception>();
		lock = new ReentrantLock();
		formatter = ExceptionMsgFormatFactory.getInstance().getFormatter(ExceptionMsgFormatFactory.STACK_TRACE);
	}

	/**
	 * �Ƿ����쳣
	 * @return
	 */
	public boolean hasException() {
		return exceptionList.size() > 0;
	}

	/**
	 * ����쳣�����߳��쳣�б�
	 * @param e
	 */
	public void addException(Exception e) {
		try {
			lock.lock();
			e.setStackTrace(e.getStackTrace());
			exceptionList.add(e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * ��ȡ���߳��쳣�б�
	 * @return
	 */
	public List<Exception> getExceptionList() {
		return exceptionList;
	}

	/**
	 * ������߳��쳣�б�
	 */
	public void clearExceptionList() {
		exceptionList.clear();
	}

	/**
	 * ��ȡ�������߳��쳣�б����쳣�Ķ�ջ������Ϣ
	 * @return
	 */
	public String getAllStackTraceMessage() {
		StringBuffer sb = new StringBuffer();
		for (Exception e : exceptionList) {
			sb.append(e.getClass().getName());
			sb.append(": ");
			sb.append(e.getMessage());
			sb.append("\n");
			sb.append(formatter.formate(e));
		}
		return sb.toString();
	}

	/**
	 * ��ӡ�������߳��쳣�б����쳣�Ķ�ջ������Ϣ
	 */
	public void printAllStackTrace() {
		printAllStackTrace(System.err);
	}

	/**
	 * ��ӡ�������߳��쳣�б����쳣�Ķ�ջ������Ϣ
	 * @param s
	 */
	public void printAllStackTrace(PrintStream s) {
		for (Exception e : exceptionList) {
			e.printStackTrace(s);
		}
	}

}
