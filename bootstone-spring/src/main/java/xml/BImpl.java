package xml;

import org.springframework.stereotype.Component;

/**
 * Created by lsh on 2019/4/10.
 */
@Component
public class BImpl implements StoneService {

    @Override
    public void happy() {
        System.out.println("this is B");
    }

    @Override
    public void eat() {

    }

    @Override
    public void drink() {

    }

    @Override
    public void play() {

    }
}
