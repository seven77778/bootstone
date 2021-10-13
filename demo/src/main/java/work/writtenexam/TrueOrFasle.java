package work.writtenexam;

import org.junit.Test;

public class TrueOrFasle {

    @Test
    public void test(){
        /**
         * && 中第一个条件为FALSE，第二个不执行
         */
        if(false && get()){
            System.out.println("go on");
        }

        if(true && get()){
            System.out.println("go on");
        }
    }

    public boolean get(){
        System.out.println(123);
        return true;
    }
}
