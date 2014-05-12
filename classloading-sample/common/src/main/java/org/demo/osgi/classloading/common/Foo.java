package org.demo.osgi.classloading.common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo
 */
public class Foo {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Foo.class);

    private static AtomicInteger i = new AtomicInteger();

    static{
       logger.info("Foo class is loaded");
    }


    public static int incAndGetValue(){
        return i.incrementAndGet();
    }

}

