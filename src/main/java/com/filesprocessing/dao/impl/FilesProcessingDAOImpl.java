/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.dao.impl;

import com.filesprocessing.dao.FilesProcessingDAO;
import com.filesprocessing.domain.Filesprocessing;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * sdj
 */

@Repository
public class FilesProcessingDAOImpl implements FilesProcessingDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Filesprocessing> getAll() {
        return hibernateTemplate.find(" from Filesprocessing");
    }

    @Override
    public List<Filesprocessing> getFilesProcessingById(Long id) {
        return hibernateTemplate.findByNamedParam(" from Filesprocessing where id = :id", "id", id);
    }

    @Override
    public List<Filesprocessing> getFilesProcessingByDate(Date date) {
        return hibernateTemplate.findByNamedParam(" from Filesprocessing where creationdate = :creationdate", "creationdate", date);
    }

    @Override
    public List<Filesprocessing> getFilesProcessingBetweenDate(Date[] dateFromDateTo) {
        if(dateFromDateTo == null || dateFromDateTo.length <= 1)
            return new ArrayList<>();
        String[] dFromTo = {"dateFrom", "dateTo"};
        return hibernateTemplate.findByNamedParam(" from Filesprocessing where creationdate between :"+
               dFromTo[0] + " and :" + dFromTo[1],
               dFromTo,
               dateFromDateTo);
    }

    @Override
    public void add(Filesprocessing fProc) {
        hibernateTemplate.save(fProc);
        hibernateTemplate.flush();
    }

    @Override
    public void update(Filesprocessing fProc) {
        hibernateTemplate.saveOrUpdate(fProc);
        hibernateTemplate.flush();
    }

    @Override
    public void delete(Filesprocessing fProc) {
        hibernateTemplate.delete(fProc);
        hibernateTemplate.flush();
    }

}
