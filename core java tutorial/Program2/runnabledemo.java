package Program2;
class myrunnable implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<=5;i++){
            System.out.println("runnable");
        }
    }
}
public class runnabledemo {
    public static void main(String[] args) {
        myrunnable r=new myrunnable();
        Thread t=new Thread(r);
        t.start();
        for(int i=1;i<=5;i++){
            System.out.println("Main");
        }
    }
}
