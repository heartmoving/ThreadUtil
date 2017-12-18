package com.zyj.thread.parallel;

import java.util.concurrent.CountDownLatch;

import com.zyj.exception.ChildThreadException;

/**
 * �����߳����񣬼�¼��������ʱ����Ϣ
 * 
 * @author zengyuanjun
 *
 */
public class MultiParallelRunnable implements Runnable {
	/**
	 * ���е�����
	 */
	private Runnable task;
	/**
	 * ���̵߳�������������֪ͨ���߳�ִ������
	 */
	private CountDownLatch childLatch;
	/**
	 * ���߳��쳣�����ڼ�¼���̵߳��쳣
	 */
	private ChildThreadException exception;

	/**
	 * ������
	 * @param task ����
	 * @param e ���߳��쳣
	 * @param childLatch ��������
	 */
	public MultiParallelRunnable(Runnable task, ChildThreadException e, CountDownLatch childLatch) {
		this.task = task;
		this.childLatch = childLatch;
		this.exception = e;
	}

	/**
	 * �������񣬼�¼�쳣������������
	 */
	@Override
	public void run() {
		try {
			task.run();
		} catch (Exception e) {
			e.printStackTrace();
			exception.addException(e);
		} finally {
			childLatch.countDown();
		}
	}
	
}
