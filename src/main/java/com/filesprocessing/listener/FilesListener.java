/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.listener;

import com.filesprocessing.configuration.Configuration;
import com.filesprocessing.domain.Filesprocessing;
import com.filesprocessing.service.FilesProcessingService;
import static com.filesprocessing.utils.Util.moveFile;
import com.filesprocessing.utils.XMLConverter;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.annotation.PreDestroy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * sdj
 */
public class FilesListener{
    private final Pattern pattern;
    private static final Logger log = Logger.getLogger(FilesListener.class);
    private final ExecutorService pool;
    private final Configuration conf;

    private volatile int numFiles;
    @Autowired
    private FilesProcessingService fileProcService;
    @Autowired
    private XMLConverter xmlConverter;

    public FilesListener(final Configuration conf) {
        this.conf = conf;
        this.pool = Executors.newFixedThreadPool(Integer.valueOf(this.conf.getNumPoolThread()));
        pattern = Pattern.compile(conf.getFileMask());
    }

    public void extractFiles(){
        if(numFiles != 0)
            return;
        log.info("Start find files...");
        iterateFiles(new File(conf.getDirInp()));
        if (numFiles == 0) {
            log.info("No files found.");
        }
    }

    private void iterateFiles(final File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory() && file.canRead()) {
                iterateFiles(file);
            } else if (pattern.matcher(file.getName()).matches()) {
                log.info("Find a file for processing: " + file.getPath());
                ++numFiles;
                pool.submit(new FileRunnable(file));  
            }
        }
    }

    private class FileRunnable implements Runnable {
        private File file;

        public FileRunnable(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            try {
                log.info("Start processing parsing file: " + file.getName());
                Filesprocessing oFileListener = (Filesprocessing) xmlConverter.convertFromXMLToObject(file);
                log.info("End processing parsing file: " + file.getName());
                log.info("Start saving finded object into DB.");
                fileProcService.add(oFileListener);
                log.info("Save finded object to DB.");
                log.info("Moving procesed file into " + conf.getDirOut() + " directory...");
                moveFile(file, conf.getDirOut());
            } catch (Throwable e) {
                log.error("Exception when do parsing file " + file.getName(), e);
                File movedFile = moveFile(file, conf.getDirFail());
                log.info("Unparsed file " + movedFile.getName() + " move to the " + movedFile.getPath());
            }
            finally{
                --numFiles;
            }
        }
    }

    @PreDestroy
    public void shutDown() {
        log.info("~ TheadPool from FilesListener was shutdowned.");
        pool.shutdown();
    }
}
