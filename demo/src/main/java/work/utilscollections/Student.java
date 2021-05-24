package work.utilscollections;

import lombok.Data;

@Data
public class Student {

    private String name;
    private Integer age;
    private  Integer price;

    public Student() {
    }

    public Student(String name, Integer age, Integer price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public void eat(){
        System.out.println("student eat");
    }

    public  Integer getPrice(){
        return price;
    }
}
