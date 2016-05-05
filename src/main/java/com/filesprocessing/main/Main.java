package com.filesprocessing.main;

import com.filesprocessing.listener.FilesListener;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        FilesListener fl = null;
        try {
            fl = (FilesListener) ctx.getBean("filesListener");
            log.info("File processing will run on schedule, please wait...");
            Scanner sc = new Scanner(System.in);
            String inpString;
            do {
                log.info("Press q to exit...");
                inpString = sc.next();
            } while (!inpString.equalsIgnoreCase("q"));
        } finally {
            fl.shutDown();
            System.exit(0);
        }
    }
}
