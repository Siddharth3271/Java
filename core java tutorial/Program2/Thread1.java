package Program2;
class Threadtest extends Thread{
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
            System.out.println("Child thread Executing");
        }
    }
}
public class Thread1 {
    public static void main(String[] args) {
        Threadtest t=new Threadtest();
        t.start();   //necessary to start multi-threading environment
//        t.run();
        for(int i=1;i<=5;i++){
            System.out.println(Thread.currentThread());
            System.out.println("Main Thread...");
        }
    }
}
