package work.utilscollections;

import lombok.Data;

import java.util.Date;

@Data
public class Money {

    private String name;
    private int price;
    private Date date;
    private String status;


    public Money(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Money(String name, int price, Date date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Money(String name, int price,  String status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Money() {
    }
}
