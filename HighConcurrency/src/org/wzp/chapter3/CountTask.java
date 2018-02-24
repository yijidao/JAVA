package org.wzp.chapter3;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
/**
 * Fork/Join 框架，分而治之
 * @author wzp
 * @date: 2018年2月24日 上午11:58:05 
 *
 */
public class CountTask extends RecursiveTask<Long>{
	public static final int THRESHOLD = 10000; // 阈
	private long start;
	private long end;
	
	public CountTask(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		long sum = 0;
		boolean canCompute = (end - start) < THRESHOLD;
		if(canCompute) {
			for(long i = start; i <= end; i++) {
				sum += i; 
			}
		}else {
			long step = (end + start) / 100;
			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = start;
			for(int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if(lastOne > end)
					lastOne = end;
				CountTask subTask = new CountTask(pos, lastOne);
				pos += step + 1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for(CountTask t : subTasks) {
				sum += t.join();
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool =  new ForkJoinPool();
		CountTask task = new CountTask(0, 200000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(task);
		long res;
		try {
			res = result.get();
			System.out.println("--sum = " + res);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
