package com.zyj.thread.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.zyj.exception.ChildThreadException;
import com.zyj.thread.MultiThreadHandler;

/**
 * �������߳�����
 * @author zengyuanjun
 */
public abstract class AbstractMultiParallelThreadHandler implements MultiThreadHandler {
	/**
	 * �����������ж��Ƿ����������߳�ִ�н���
	 */
	protected CountDownLatch childLatch;
	
	/**
	 * �����б�������������
	 */
	protected List<Runnable> taskList;
	
	/**
	 * ��¼���߳��쳣
	 */
	protected ChildThreadException childThreadException;

	public AbstractMultiParallelThreadHandler() {
		taskList = new ArrayList<Runnable>();
		childThreadException = new ChildThreadException();
	}

	public void setCountDownLatch(CountDownLatch latch) {
		this.childLatch = latch;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTask(Runnable... tasks) {
		if (null == tasks) {
			taskList = new ArrayList<Runnable>();
		}
		for (Runnable task : tasks) {
			taskList.add(task);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void run() throws ChildThreadException;

}
