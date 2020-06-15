package spring20200603;

import org.springframework.stereotype.Component;

/**
 * Created by lsh on 2020-06-04.
 * 不在xml中配置，只加注解
 *
 */
@Component("noxmlbean")
public class NoXmlBean {

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public NoXmlBean(String name) {
        this.name = name;
    }

    public NoXmlBean(int age,String name) {
        this.age = age;
        this.name = name;
    }

    public NoXmlBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run(){
        System.out.println(this.name + " run...");
    }

    @Override
    public String toString() {
        return "NoXmlBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
