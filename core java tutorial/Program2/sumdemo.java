package Program2;
class testing implements Runnable{
    static int sum=0;
    @Override
    public void run(){
        for(int i=1;i<=4;i++){
            sum+=i;
        }
    }
}
public class sumdemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new testing(),"Sum");
        t1.start();
        t1.join();
//        testing t=new testing();
//        t.run();
        System.out.println("Sum is = "+testing.sum);
    }
}
