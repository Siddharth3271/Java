package Program1;
class Animal{
    String Name;
}
class Dog extends Animal{
    String Name;
    public void print(){
        //this-->current
        //super-->parent
        super.Name="Animal";
        this.Name="Jipsy";
        System.out.println("Parent Name:"+super.Name);
        System.out.println("Child Name:"+this.Name);
    }
}
public class thisdemo {
    public static void main(String[] args) {
        Dog d=new Dog();
        d.print();
    }
}
