package work.utilscollections;

import lombok.Data;

import java.util.Date;

@Data
public class Money {

    private String name;
    private int price;
    private Date date;
    private Date endDate;
    private Date beginDate;

    public Money(String name, int price, String begin, String end, String status) {
        this.name = name;
        this.price = price;
        this.begin = begin;
        this.end = end;
        this.status = status;
    }

    private String begin;
    private String end;

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

    public Money(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Money(String name, Date endDate, Date beginDate) {
        this.name = name;
        this.endDate = endDate;
        this.beginDate = beginDate;
    }

    public Money(String name, String begin, String end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Money() {
    }
}
