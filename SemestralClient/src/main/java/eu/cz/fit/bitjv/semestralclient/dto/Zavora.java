/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralclient.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matya
 */
@Entity
@XmlRootElement
public class Zavora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ZAVORA_ID")
    private Long id;
    
    private String umisteni;
    
    private float cenaZaPrujezd;

    public float getCenaZaPrujezd() {
        return cenaZaPrujezd;
    }

    public void setCenaZaPrujezd(float cenaZaPrujezd) {
        this.cenaZaPrujezd = cenaZaPrujezd;
    }
    
    @OneToMany(targetEntity=Prujezd.class, mappedBy="zavora", fetch=FetchType.EAGER)
    private List<Prujezd> prujezdy;

    public Zavora(String umisteni, float cenaZaPrujezd) {
        this.umisteni = umisteni;
        this.cenaZaPrujezd = cenaZaPrujezd;
    }

    public Zavora() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUmisteni() {
        return umisteni;
    }

    public void setUmisteni(String umisteni) {
        this.umisteni = umisteni;
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
        if (!(object instanceof Zavora)) {
            return false;
        }
        Zavora other = (Zavora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.fit.bitjv.semestralserver.enitity.Zavora[ id=" + id + " ]";
    }
    
}
