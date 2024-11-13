package Program2;
class test1{
    static void m1(){
        System.out.println("Test1");
    }
    protected void m2(){
        System.out.println("Hello Parent");
    }
}
class test1child extends test1{
    //data hiding not overriding
    static void m1(){
        System.out.println("Test2");
    }
    //you can't reduce the access modifier of parent class
    public void m2(){
        super.m2();
    }
}
public class overridedemo {
    public static void main(String[] args) {
        test1child obj=new test1child();
        obj.m1();
    }
}
