package MultiThreading.LockSupport;

public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        MyThread3 thread3 = new MyThread3();
        synchronized (thread3){
            try {
                thread3.start();
                Thread.sleep(3000);
                System.out.println("before wait");

                thread3.wait();

                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread3 extends Thread{
    public void run(){
        synchronized (this){
            System.out.println("before notify");

            notify();

            System.out.println("after notify");
        }

    }
}
