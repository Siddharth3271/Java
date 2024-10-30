package Program1;
class class2{
    String Name;
    class2(){
        System.out.println("Happy Ending");
    }
    class2(String Name){
        this.Name=Name;
    }
    public void func3(){
        System.out.println(Name);
    }
}
class class1{
    class2 obj=new class2();
    class1(class2 obj){
        this.obj=obj;
    }
    public void func1(){
//        System.out.println("This is func1");
        obj=new class2("Siddharth");
        obj.func3();
    }
//    public void func2(int a,int b){
//        System.out.println(a+b);
//    }
    class1(){
//        class2 obj2=new class2();
        System.out.println("Constructor is Running");
    }
//    class1(char[] arr){
////        for(int i=0;i<arr.length;i++){
////            System.out.print(arr[i]);
////        }
////        System.out.println();
//    }
}
public class Program16 {
    public static void main(String[] args){
        class2 obj4=new class2();
//        char[] arr={'a','b','c','d'};
//        String s=new String(arr);
//        System.out.println(s);

        //default constructor
        class1 obj=new class1();
        obj.func1();
//        obj.func2(6,7);

//        class1 obj2=new class1(arr);
//        class2 obj3=new class2("Siddharth");
//        obj3.func3();
        class1 newobj=new class1(obj4);
    }
}
