/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.service;

import com.filesprocessing.dao.FilesProcessingDAO;
import com.filesprocessing.domain.Filesprocessing;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * sdj
 */
@Service
public class FilesProcessingService {
    @Autowired
    private FilesProcessingDAO filesProcDAO;
    
    @Transactional(readOnly = true)
    public List<Filesprocessing> getAll(){
        return filesProcDAO.getAll();
    }

    @Transactional(readOnly = true)
    public List<Filesprocessing> getFilesProcessingById(Long id){
        return filesProcDAO.getFilesProcessingById(id);
    }

    @Transactional(readOnly = true)
    public List<Filesprocessing> getFilesProcessingByDate(Date date){
        return filesProcDAO.getFilesProcessingByDate(date);
    }
    
    @Transactional(readOnly = true)
    public List<Filesprocessing> getFilesProcessingBetweenDate(Date[] dateFromdateTo){
        return filesProcDAO.getFilesProcessingBetweenDate(dateFromdateTo);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Filesprocessing fProc){
        filesProcDAO.add(fProc);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Filesprocessing fProc){
        filesProcDAO.update(fProc);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Filesprocessing fProc){
        filesProcDAO.delete(fProc);
    }
}
