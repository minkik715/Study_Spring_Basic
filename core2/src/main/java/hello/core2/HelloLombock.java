package hello.core2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombock {
    private int age;
    private String name;

    public static void main(String[] args) {
        HelloLombock helloLombock = new HelloLombock();
        helloLombock.setAge(1);
        helloLombock.setName("minki");

        System.out.println("helloLombock.getName() = " + helloLombock.getName());
        System.out.println("helloLombock.getAge() = " + helloLombock.getAge());
    }
}
