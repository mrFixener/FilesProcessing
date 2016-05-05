/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * sdj
 */
public class Util {
    private static Logger log = Logger.getLogger(Util.class);

    public static File moveFile(final File source, String dist){
        File newFile = new File(dist + "/" + source.getName());
        try {
            if (newFile.exists()) 
            {
                String newName = filePrefix() +"_"+ source.getName();
                newFile = new File(dist + "/" + newName);
                log.warn("Find duplicate file in "+newFile.getParent()+"\\"+source.getName()+". File renamed and moved to "+newFile.getCanonicalPath());
                Files.move(source.toPath(), newFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
            }else{
                Files.move(source.toPath(), newFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
            }
        } catch (IOException ex) {
            log.error("Can't copy file "+source.getPath()+" to "+newFile.getPath(), ex);
        }
        return newFile;
    }

    public static String filePrefix() {
        return new SimpleDateFormat("yyyy-MM-dd_hhmmssms").format(new Date(System.currentTimeMillis()));
    }
}
