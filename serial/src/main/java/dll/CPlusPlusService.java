package dll;

import com.sun.jna.win32.StdCallLibrary;

/**
 * Created by lsh on 2019/4/8.
 */
public interface CPlusPlusService extends StdCallLibrary {

    int add(int a,int b);

    String getstr();
}
