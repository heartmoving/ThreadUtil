package com.zyj.thread.parallel;

import java.util.concurrent.CountDownLatch;

import com.zyj.exception.ChildThreadException;

/**
 * �������߳�����run�������ȴ������б����������������
 * 
 * @author zengyuanjun
 *
 */
public class MultiParallelThreadHandler extends AbstractMultiParallelThreadHandler {

	/**
	 * �޲ι�����
	 */
	public MultiParallelThreadHandler() {
		super();
	}

	/**
	 * ���������б����������񣬲��ȴ��������
	 */
	@Override
	public void run() throws ChildThreadException {
		if (null == taskList || taskList.size() == 0) {
			return;
		} else if (taskList.size() == 1) {
			runWithoutNewThread();
		} else if (taskList.size() > 1) {
			runInNewThread();
		}
	}

	/**
	 * �½��߳���������
	 * 
	 * @throws ChildThreadException
	 */
	private void runInNewThread() throws ChildThreadException {
		childLatch = new CountDownLatch(taskList.size());
		childThreadException.clearExceptionList();
		for (Runnable task : taskList) {
			invoke(new MultiParallelRunnable(task, childThreadException, childLatch));
		}
		taskList.clear();
		try {
			childLatch.await();
		} catch (InterruptedException e) {
			childThreadException.addException(e);
		}
		throwChildExceptionIfRequired();
	}

	/**
	 * ����ִ�еķ���
	 * 
	 * @param command
	 */
	protected void invoke(Runnable command) {
		if(command.getClass().isAssignableFrom(Thread.class)){
			Thread.class.cast(command).start();
		}else{
			new Thread(command).start();
		}
	}

	/**
	 * ���½��̣߳�ֱ���ڵ�ǰ�߳�����
	 * 
	 * @throws ChildThreadException
	 */
	private void runWithoutNewThread() throws ChildThreadException {
		try {
			taskList.get(0).run();
		} catch (Exception e) {
			childThreadException.addException(e);
		}
		throwChildExceptionIfRequired();
	}

	/**
	 * �Ƿ����쳣��Ҫ�׳�
	 * 
	 * @throws ChildThreadException
	 */
	private void throwChildExceptionIfRequired() throws ChildThreadException {
		if (childThreadException.hasException()) {
			childExceptionHandler(childThreadException);
		}
	}

	/**
	 * ���߳��쳣����Ĭ��ֱ���׳�
	 * @param e ���߳��쳣
	 * @throws ChildThreadException
	 */
	protected void childExceptionHandler(ChildThreadException e) throws ChildThreadException {
		throw e;
	}

}
