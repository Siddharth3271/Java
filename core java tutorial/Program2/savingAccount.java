package Program2;
class synchronizationacc{
    public synchronized void withdraw(String Name){
        for(int i=0;i<5;i++){
            synchronized (this){
                System.out.print("paise nikal rhe hain = ");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Name);
        }
        System.out.println("****Transaction Completed****");
    }
}
class mythreadAcccount extends Thread{
    synchronizationacc s;
    String Name;
    mythreadAcccount(synchronizationacc s,String Name){
        this.s=s;
        this.Name=Name;
    }

    @Override
    public void run() {
        s.withdraw(Name);
    }
}
public class savingAccount {
    public static void main(String[] args) {
        synchronizationacc obj=new synchronizationacc();
        mythreadAcccount t1=new mythreadAcccount(obj,"husband");
        mythreadAcccount t2=new mythreadAcccount(obj,"wife");
        t1.start();
        t2.start();
    }
}
