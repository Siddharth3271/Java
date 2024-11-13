package Program2;
class mythread implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
            System.out.println("child thread:");
        }
    }
}
public class Thread2 {
    public static void main(String[] args) {
        Thread t=new Thread(new mythread());
        t.setName("Siddharth");
        t.start();
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
            System.out.println("Main thread:");
        }
    }
}
