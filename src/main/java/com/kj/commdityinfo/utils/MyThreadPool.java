package com.kj.commdityinfo.utils;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * 自定义线程池
 */

@Component
public class MyThreadPool {
	LinkedList<Runnable> tasks = new LinkedList<>();
	
	int threadPoolSize;
	
	public MyThreadPool() {
		threadPoolSize = 10;
		
		synchronized(tasks) {
			for(int i = 0; i < threadPoolSize; i++) {
				new TaskConsumeThread("任务消费者线程 " + i).start();
			}
		}
	}
	
	public void add(Runnable r) {
		synchronized(tasks) {
			tasks.add(r);
			//每加一个任务唤醒线程
			tasks.notifyAll();
		}
	}
	
	class TaskConsumeThread extends Thread{
		public TaskConsumeThread(String name) {
			super(name);
		}
		
		Runnable task;

		@Override
		public void run() {
			System.out.println("任务消费者线程: " + this.getName());
			while(true) {
				synchronized(tasks) {
					while(tasks.isEmpty()) {
						try {
							//被唤醒以后为可以抢锁的状态，抢到以后继续往下执行，没抢到继续等待
                            tasks.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
					}
					task = tasks.removeFirst();
					//因为加100个任务时，可能十个线程每加一个就能处理一个，能马上消化掉100就可以不要下面一句，但如果执行的慢，就存在100加完
					//进来，但10个线程只处理了15个，没有新任务加进来就无法唤醒停下工作，所以加上下面一句，就可以处理完以后继续工作
					//理完以后继续处理剩下的
					tasks.notifyAll();
				}
				System.out.println(this.getName() + " 获取到任务");
				task.run();
			}
		}
	}
}
