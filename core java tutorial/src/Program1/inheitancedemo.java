package Program1;
//class p1{
//    public void m1(){
//        System.out.println("What to do");
//    }
//}
//class p2{
//    public void m1(){
//        System.out.println("What to do");
//    }
//}
interface p1{
    public void m1();
}
interface p2{
    public void m1();
}
class child implements p1,p2{
    @Override
    public void m1() {
        System.out.println("What is this");
    }
}
public class inheitancedemo {
    public static void main(String[] args) {
        child obj=new child();
        obj.m1();
    }
}
