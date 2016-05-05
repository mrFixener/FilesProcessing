/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.test;

import com.filesprocessing.configuration.Configuration;
import com.filesprocessing.domain.Filesprocessing;
import com.filesprocessing.listener.FilesListener;
import com.filesprocessing.utils.XMLConverter;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * sdj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testSpring.xml")
public class FilesListenerTest {

    private File correctFile;
    private File incorrectFile;
    private File outFile;
    private File outFailedFile;
    @Autowired
    private FilesListener fl;
    @Autowired
    private Configuration configuration;
    @Autowired
    XMLConverter converter;

    public FilesListenerTest() {
    }

    @Before
    public void setUp() {
        correctFile = new File(configuration.getDirInp() + "/inp.xml");
        incorrectFile = new File(configuration.getDirInp() + "/inp_incorrect.xml");
        outFile = new File(configuration.getDirOut() + "/" + correctFile.getName());
        outFailedFile = new File(configuration.getDirFail() + "/" + incorrectFile.getName());
    }

    @Test
    public void handlerCorrectFileTest() throws IOException, InterruptedException {
        Filesprocessing fileProc = new Filesprocessing(null, "Some text", null);
        converter.convertFromObjectToXML(fileProc, correctFile);
        fl.extractFiles();
        Thread.sleep(500);
        assertTrue("File " + correctFile.getCanonicalPath() + " must was proccesed and moved to " + configuration.getDirOut(),
                !correctFile.exists());
        assertTrue("File " + correctFile.getCanonicalPath() + " must was proccesed and moved to " + configuration.getDirOut(),
                outFile.exists());
    }

    @Test
    public void handlerIncorrectFileTest() throws IOException, InterruptedException {
        Filesprocessing fileProc = new Filesprocessing(null, "Some incorrect creationDate format", new Date());
        converter.convertFromObjectToXML(fileProc, incorrectFile);
        //Thread.sleep(1000);
        fl.extractFiles();
        Thread.sleep(500);
        assertTrue("File " + incorrectFile.getCanonicalPath() + " must was unproccesed and moved to " + configuration.getDirFail(),
                !incorrectFile.exists());
        assertTrue("File " + incorrectFile.getCanonicalPath() + " must was unproccesed and moved to " + configuration.getDirFail(),
                outFailedFile.exists());
    }

    @After
    public void tearDown() {
        outFailedFile.delete();
        outFile.delete();
    }

}
