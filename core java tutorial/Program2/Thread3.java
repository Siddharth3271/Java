package Program2;
class joinTest implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class Thread3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new joinTest(),"JoinTest");
        t1.start();
        //The join method in Java is used to pause the execution of the current thread until the thread on which join was called has finished executing
        t1.join();   //main thread
        System.out.println("Main Thread");
    }
}
