package Program2;
class testing1 implements Runnable{
    int sum=0;
    @Override
    public void run(){
        for(int i=1;i<=4;i++){
            sum+=i;
        }
        System.out.println("Thread Name and sum "+Thread.currentThread().getName()+" "+sum);
    }
}
public class Thread4 {
    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(new testing1(),"Sum-1");
        Thread t2=new Thread(new testing1(),"Sum-2");
        t1.start();
        t2.start();
//        System.out.println("Sum of t1 = "+testing1.sum);
//        System.out.println("Sum of t2 = "+testing1.sum);
    }
}
