package com.lsh.demo.basic.system.packages;

/**
 * Created by lsh on 2019-04-26.
 */
public class HowToPackage {

    /*
        1.jar  但是jvm参数未生效
         <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.0.1.RELEASE</version>
                <configuration>
                    <jvmArguments>
                        -Xmx60m -Xms60m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\oom
                    </jvmArguments>
                </configuration>
            </plugin>

            2.war
             <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.6</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>

     */

}
