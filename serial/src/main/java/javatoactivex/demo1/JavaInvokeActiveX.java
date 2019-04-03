package javatoactivex.demo1;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Created by lsh on 2019/4/2.
 * todo java 调用 activeX
 *
 */
public class JavaInvokeActiveX {

    public static void main(String[] args) {
        try{
            ActiveXComponent dotnetCom = null;
            dotnetCom = new ActiveXComponent("MF1Card.dll");
            Variant var = Dispatch.call(dotnetCom,"GetEncrypt","第一个参数","第二个参数");
            String str  = var.toString(); //返回值
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
