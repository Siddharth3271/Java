package Program1;
class test{
    static int a=10;    //gets the space in memory as soon as the class loads
    int b=20;   //gets memory in runtime
    public void method(){
        System.out.println(a);
    }
    public static void display(){
//        System.out.println(b);   //error
        //non-static variable static area mey access nahi ho patey
        //static variable non-static area mey access ho jate hain
        System.out.println(a);
    }
}
public class Staticdemo {
    public static void main(String[] args) {
        test t=new test();
        test t1=new test();
        test t2=new test();
        t.method();
        t.a=100;
        t1.a=200;
        t.b=30;
        t2.b=120;
        //System.out.println(t.a);   //error
        System.out.println(test.a); //we can access by the help of class
        System.out.println(t.a);   //static has shared memory  //200 will be printed
        System.out.println(t1.a);  //200 will be printed
        System.out.println(t.b);
        System.out.println(t1.b);
        System.out.println(t2.b);

        System.out.println(staticuse.B0_DB_1);
        boolean checkit=staticuse.isEmptyornull("");
        System.out.println(checkit);

    }
}
