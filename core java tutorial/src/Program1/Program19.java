package Program1;
class checking{
    //participates in inheritance but can't be overridden
    final public void m1(){
        System.out.println("Hello Everyone");
    }
}

class child2 extends checking{

}
public class Program19 {
    public static void main(String[] args) {
        child2 obj=new child2();
        obj.m1();
    }
}
