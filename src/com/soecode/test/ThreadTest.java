package com.soecode.test;

public class ThreadTest {

	public static void main(String[] args) {
		ThreadTest2 t = new ThreadTest2();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
	}
}


class ThreadTest2 implements Runnable {
	private int tickets = 10;
	
	public void run() {
		synchronized (this) {
			while (true) {
				if (tickets > 0) {
					System.out.println(Thread.currentThread().getName()
							+ " is saling ticket " + tickets--);
				} else {
					break;
				}
			}
		}
	}
}
