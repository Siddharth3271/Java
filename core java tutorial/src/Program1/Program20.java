package Program1;
class c1{
    String Name1;
    int a;   //memory given at runtime
    public void func1(){
        Name1="Siddharth";
        int b;   //you should initialize local variables at time of writing code
        System.out.println(Name1);
        System.out.println(a);
//        System.out.println(b);    will give error
    }

    public void func2(){
        System.out.println(Name1);
    }
}
public class Program20 {
    static String a="I am";
    public static void main(String[] args) {
        System.out.print(a+" ");
        c1 obj=new c1();
        obj.func1();
        obj.func2();
        System.out.println(obj.Name1);
    }
}
