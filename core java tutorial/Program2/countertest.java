package Program2;
class testing4{
    private int count=0;
    public synchronized void incre(){
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class counterThread extends Thread{
    private  testing4 counter;
    counterThread(testing4 counter){
        this.counter=counter;
    }
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println(Thread.currentThread());
            counter.incre();
        }
    }
}
public class countertest {
    public static void main(String[] args) throws InterruptedException {
        testing4 obj=new testing4();
        Thread t1=new counterThread(obj);
        Thread t2=new counterThread(obj);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
//        Thread.sleep(3000);
        System.out.println("Final Count ="+obj.getCount());
    }
}
