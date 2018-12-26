/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralclient.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matya
 */
@Entity
@XmlRootElement
public class Prujezd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datum_prujezdu;
    
    @ManyToOne(targetEntity=Auto.class, fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="AUTO_ID")
    private Auto auto;
    
    @ManyToOne(targetEntity=Zavora.class, fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="ZAVORA_ID")
    private Zavora zavora;

    public Prujezd(Date datum_prujezdu, Auto auto, Zavora zavora) {
        this.datum_prujezdu = datum_prujezdu;
        this.auto = auto;
        this.zavora = zavora;
    }
    
    public Prujezd() {

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum_prujezdu() {
        return datum_prujezdu;
    }

    public void setDatum_prujezdu(Date datum_prujezdu) {
        this.datum_prujezdu = datum_prujezdu;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Zavora getZavora() {
        return zavora;
    }

    public void setZavora(Zavora zavora) {
        this.zavora = zavora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prujezd)) {
            return false;
        }
        Prujezd other = (Prujezd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.fit.bitjv.semestralserver.enitity.Prujezd[ id=" + id + " ]";
    }
    
}
