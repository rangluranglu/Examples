package MultiThreading.LockSupport;

import java.util.concurrent.locks.LockSupport;

public class parkExample {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());

        myThread.start();
        System.out.println("before park   "+ Thread.currentThread().getId());

        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");

        System.out.println("after park    "+ Thread.currentThread().getId());
    }
}

class MyThread extends Thread{
    private Object object;

    public MyThread(Object object){
        this.object = object;
    }

    public void run(){
        System.out.println("before unpark    "+ Thread.currentThread().getId());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取blocker
        System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

        // 释放许可
        LockSupport.unpark((Thread) object);

        // 休眠500ms，保证先执行park 中的setBlocker(t, null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Blocker info" + LockSupport.getBlocker((Thread) object) + "    "+ Thread.currentThread().getId());

        System.out.println("after unpark    "+ Thread.currentThread().getId());

    }
}


