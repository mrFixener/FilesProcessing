/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filesprocessing.domain;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * sdj
 */
@Entity
@Table(name = "Filesprocessing")
public class Filesprocessing {
    private Long id;
    private String content;
    private Date creationDate;
    private Date procDate = new Date();

    public Filesprocessing() {
    }

    public Filesprocessing(Long id, String content, Date creationDate) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "content", length = 1024, nullable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "creationdate", nullable = true)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    @Column(name = "procdate", nullable = false)
    public Date getProcDate() {
        return procDate;
    }
    
    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }
    
    @Override
    public String toString() {
        return "FilesProcessing{" + "id=" + id + ", content=" + content + ", creationDate=" + creationDate + ", procDate=" + procDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.creationDate);
        hash = 79 * hash + Objects.hashCode(this.procDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filesprocessing other = (Filesprocessing) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.procDate, other.procDate)) {
            return false;
        }
        return true;
    }
    
}
