package Program2;
/*
A constructor is used to initializing non-static data members and as there are no non-static data members in the interface, there is no need of constructor
Methods present in the interface are only declared not defined, As there is no implementation of methods, there is no need of making objects for calling methods in the interface and thus no point of having constructor in it.
If we try to create a constructor inside the interface, the compiler will give a compile-time error.
*/
interface interfacetest{
    //cannot have object because it has not constructor
    void display();   //by default, abstract,public
    int a=10; //by default will be public ,static,final
    //abstract: i will depend on my child
}
class test implements interfacetest{
    public void display(){
        System.out.println("Hello World");
    }
}
public class interfacedemo {
    public static void main(String[] args) {
        test t=new test();
        t.display();
        System.out.println(t.a);
    }
}
