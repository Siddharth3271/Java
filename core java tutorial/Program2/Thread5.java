package Program2;

public class Thread5 {
    public static void main(String[] args) throws InterruptedException {
        Thread6 obj=new Thread6();
        obj.start();
        synchronized (obj){
            System.out.println("Main thread calling wait method");
            obj.wait();
            System.out.println("Main thread got notification");
            System.out.println("Total is = "+obj.total);
        }
    }
}

class Thread6 extends Thread{
    int total=0;

    @Override
    public void run() {
        synchronized (this){
            System.out.println("Child thread starts cal :");
            for(int i=0;i<100;i++){
                total=total+i;
            }
            System.out.println("Child Thread giving notification call");
            this.notify();
        }
    }
}