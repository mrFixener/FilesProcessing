/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.configuration;

/**
 *
 * sdj
 */
public class Configuration {
    private String dirInp;
    private String dirOut;
    private String dirFail;
    private String monitorPeriod;
    private String fileMask;
    private String numPoolThread;
    public Configuration(String dirInp, String dirOut, String dirFail, String monitorPeriod, String fileMask, String numPoolThread) {
        this.dirInp = dirInp;
        this.dirOut = dirOut;
        this.dirFail = dirFail;
        this.monitorPeriod = monitorPeriod;
        this.fileMask = fileMask;
        this.numPoolThread = numPoolThread;
    }

    public String getDirInp() {
        return dirInp;
    }

    public String getDirOut() {
        return dirOut;
    }

    public String getDirFail() {
        return dirFail;
    }

    public String getMonitorPeriod() {
        return monitorPeriod;
    }

    public String getFileMask() {
        return fileMask;
    }

    public String getNumPoolThread() {
        return numPoolThread;
    }
    
}
