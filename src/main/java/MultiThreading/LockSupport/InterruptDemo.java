package MultiThreading.LockSupport;

import java.util.concurrent.locks.LockSupport;

public class InterruptDemo {

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2(Thread.currentThread());
        myThread2.start();

        System.out.println("before park");

        LockSupport.park("ParkAndUnparkDemo");

        System.out.println("after park");


    }
}

class MyThread2 extends Thread{
    private Object object;

    public MyThread2(Object object){
        this.object = object;
    }

    public void run(){
        System.out.println("before interrupt");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = (Thread) object;

        thread.interrupt();

        System.out.println("after interrupt");
    }
}