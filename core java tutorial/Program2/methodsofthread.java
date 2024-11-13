package Program2;

class yieldTest1 implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+": "+i);
            Thread.yield();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}class yieldTest implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
    }
}
public class methodsofthread {
    public static void main(String[] args) {
        Thread t1=new Thread(new yieldTest(),"Thread-0");
        Thread t2=new Thread(new yieldTest1(),"Thread-1");
        t1.start();
        t2.start();
    }
}
