package com.zyj.thread;

import com.zyj.exception.ChildThreadException;

/**
 * ���߳�������
 * @author zengyuanjun
 */
public interface MultiThreadHandler {
	/**
	 * �������
	 * @param tasks ����
	 */
	void addTask(Runnable... tasks);
	/**
	 * ��������
	 * @throws ChildThreadException ���߳��쳣
	 */
	void run() throws ChildThreadException;
}
