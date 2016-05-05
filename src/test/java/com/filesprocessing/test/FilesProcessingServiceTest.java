package com.filesprocessing.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.filesprocessing.domain.Filesprocessing;
import com.filesprocessing.service.FilesProcessingService;
import java.util.Date;
import java.util.List;
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
public class FilesProcessingServiceTest {

    @Autowired
    FilesProcessingService fp;

    public FilesProcessingServiceTest() {
    }

    @Test
    public void insertDeleteTest() {
        Filesprocessing fpDomain = new Filesprocessing();
        String content = "Test content";
        fpDomain.setContent(content);
        Date creationDate = new Date();
        fpDomain.setCreationDate(creationDate);
        fp.add(fpDomain);
        List<Filesprocessing> fpFromDb = fp.getFilesProcessingByDate(creationDate);
        assertFalse("Result set can't be null", fpFromDb.size() == 0);
        Filesprocessing f = fpFromDb.get(0);
        fp.delete(f);
        assertEquals("Result set after delete must be 0.", fp.getFilesProcessingById(f.getId()).size(), 0);
    }

    @Test
    public void updateTest() {
        Filesprocessing fpDomain = new Filesprocessing();
        String content = "Test content2";
        fpDomain.setContent(content);
        Date creationDate = new Date();
        fpDomain.setCreationDate(creationDate);
        fp.add(fpDomain);

        List<Filesprocessing> fpFromDb = fp.getFilesProcessingByDate(creationDate);
        Filesprocessing f = fpFromDb.get(0);
        String updateContent = "update content";
        f.setContent(updateContent);
        fp.update(f);
        fpFromDb = fp.getFilesProcessingByDate(creationDate);
        assertFalse("Result set can't be null", fpFromDb.size() == 0);
        assertEquals("Update content must be same.", fpFromDb.get(0).getContent().trim(), updateContent);
        fp.delete(f);
    }

    @Test
    public void betweenQueryTest() {
        Filesprocessing fpDomain = new Filesprocessing();
        String content = "Test content3";
        fpDomain.setContent(content);
        Date creationDate1 = new Date();
        fpDomain.setCreationDate(creationDate1);
        fp.add(fpDomain);

        Filesprocessing fpDomain2 = new Filesprocessing();
        content = "Test content4";
        fpDomain2.setContent(content);
        Date creationDate2 = new Date();
        fpDomain2.setCreationDate(creationDate2);
        fp.add(fpDomain2);
        Date[] dateFromDateTo = {creationDate1, creationDate2};
        assertTrue("Size of resultset must be >= 2.",
                fp.getFilesProcessingBetweenDate(dateFromDateTo).size() >= 2);
        fp.delete(fpDomain);
        fp.delete(fpDomain2);
    }
}
