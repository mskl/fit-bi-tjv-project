/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralserver.enitity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matya
 */
@Entity
@XmlRootElement
public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nazev;
    
    // targetEntity = Majitel.class, optional=false
    //@ManyToOne 
    //@JoinColumn(name = "auto_majitel")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "majitel_id")
    private Majitel majitel;
    
    @OneToMany(targetEntity = Prujezd.class) // mappedBy = "auto"
    private List<Prujezd> prujezdy;

    public Auto(String nazev, Majitel majitel) {
        this.nazev = nazev;
        this.majitel = majitel;
    }
    
     public Auto(String nazev) {
        this.nazev = nazev;
    }
    
    public Auto() {
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Majitel getMajitel() {
        return majitel;
    }

    public void setMajitel(Majitel majitel) {
        this.majitel = majitel;
    }

    @XmlTransient
    public List<Prujezd> getPrujezdy() {
        return prujezdy;
    }

    public void setPrujezdy(List<Prujezd> prujezdy) {
        this.prujezdy = prujezdy;
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
        if (!(object instanceof Auto)) {
            return false;
        }
        Auto other = (Auto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.fit.bitjv.semestralserver.enitity.Auto[ id=" + id + " ]";
    }
    
}
