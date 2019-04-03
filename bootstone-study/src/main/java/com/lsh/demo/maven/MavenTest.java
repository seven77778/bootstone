package com.lsh.demo.maven;

import com.lsh.demo.bootstone.domain.object.MavenObject;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by lsh on 2019/4/2.
 *
 *[ERROR] Failed to execute goal on project lockuac-BaBai:
 *  Could not resolve dependencies for project
 *  com.futurehotel.uacclient:lockuac-BaBai:jar:1.0.0-SNAPSHOT:
 *  Failed to collect dependencies at com.futurehotel.uacclient:uacclient-common:jar:1.0.0-SNAPSHOT:
 *  Failed to read artifact descriptor for com.futurehotel.uacclient:uacclient-common:jar:1.0.0-SNAPSHOT:
 *  Failure to find com.futurehotel.uacclient:uacclient:pom:1.0.0-SNAPSHOT in http://116.62.8.239:8082/repository/3rd-party-snapshot/
 *  was cached in the local repository, resolution will not be reattempted until the update interval of tbmirror-snapshots has elapsed or updates are forced -> [Help 1]
 *
 *  同一个工程 不同moudel 需要通过pom来引用吗？
 *  需要！
 */
public class MavenTest {

    @Test
    public void test()throws Exception {
        MavenObject mavenObject = new MavenObject();
        System.out.println(mavenObject.pubName);
        Class aClass = Class.forName("com.lsh.demo.bootstone.domain.object.MavenObject");
        //public java.lang.String com.lsh.demo.bootstone.domain.object.MavenObject.pubName
        Field ss = aClass.getField("pubName");
        System.out.println(ss);
        //public java.lang.String com.lsh.demo.bootstone.domain.object.MavenObject.pubName
        System.out.println(aClass.getDeclaredField("pubName"));
        //public
        System.out.println(aClass.getDeclaredField("pubName").get(mavenObject));
        //读取private
        Field priname = aClass.getDeclaredField("name");
        System.out.println(priname);
    }

}
