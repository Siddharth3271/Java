package Program2;
class testclass implements Runnable{
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
//            System.out.println("child thread:");
        }
    }
}
public class Prioritydemo {
    public static void main(String[] args) {
        Thread t1=new Thread(new testclass(),"Thread-1");
        Thread t2=new Thread(new testclass(),"Thread-2");
        System.out.println("t1 ki Priority: "+t1.getPriority());
        System.out.println("t2 ki Priority: "+t2.getPriority());
        t1.setPriority(Thread.MIN_PRIORITY);
        System.out.println("t1 ki Priority: "+t1.getPriority());
        t1.start();
        t2.start();
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
//            System.out.println("Main Thread");
        }

    }
}
