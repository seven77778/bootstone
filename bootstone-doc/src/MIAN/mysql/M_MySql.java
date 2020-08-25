package mysql;

import java.text.SimpleDateFormat;
import java.util.Date;

public class M_MySql {

    /**
     * mysql优化
     *
     * 1.选择最合适的属性
     * ①在定义邮政编码这个字段时，如果将其设置为CHAR(255),使用VARCHAR这种类型也是多余的，
     * CHAR(6)就可以
     * ②另外一个提高效率的方法是在可能的情况下，尽量把字段设置为NOT NULL，数据库不用去比较NULL值。
     * ③对于某些文本字段，例如“省份”或者“性别”，我们可以将它们定义为ENUM类型。
     * 在MySQL中，ENUM类型被当作数值型数据来处理，而数值型数据被处理起来的速度要比文本类型快得多
     * --不推荐，用int也可以
     *
     * 2.join代替子查询
     * join更有效果，因为不需要创建临时表
     *
     * 3.使用union代替手动创建的临时表
     *
     * 4.索引
     * ①不要在大量重复的字段上使用索引，尽量用在JOIN,WHERE判断和ORDERBY排序的字段上
     * ②索引不会包含有null的，复合索引中只要有一列含有NULL值，那么这一列对于此复合索引就是无效的
     *
     * 5.like
     * 不推荐使用，如果要用，like 'aaa%'是会使用索引的
     *
     * 6.不要在列中进行计算
     * select * from users where YEAR(adddate)<2007;
     * 将在每个行上进行运算，这将导致索引失效而进行全表扫描，因此我们可以改成
     * select * from users where adddate<‘2007-01-01';
     *
     * 7.不使用NOT IN和<>操作
     * NOT IN和<>操作都不会使用索引将进行全表扫描。
     * NOT IN可以NOT EXISTS代替，id<>3则可使用id>3 or id<3来代替。
     *
     * 8.时区的bug -- cst 查出来是cdt
     * useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8
     *
     * mysql-connector-java 升级6.0.3
     */


        public static void main(String[] args) {
            long milliSecond = 620665200000L;
            Date date = new Date();
            date.setTime(milliSecond);
            System.out.println(new SimpleDateFormat().format(date));
        }

}
