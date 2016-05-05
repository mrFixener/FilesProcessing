/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.dao;

import com.filesprocessing.domain.Filesprocessing;
import java.util.Date;
import java.util.List;

/**
 *
 * sdj
 */
public interface FilesProcessingDAO {
    public List<Filesprocessing> getAll();

    public List<Filesprocessing> getFilesProcessingById(Long id);

    public List<Filesprocessing> getFilesProcessingByDate(Date date);
    
    public List<Filesprocessing> getFilesProcessingBetweenDate(Date[] dateFromdateTo);

    public void add(Filesprocessing fProc);

    public void update(Filesprocessing fProc);

    public void delete(Filesprocessing fProc);
}
