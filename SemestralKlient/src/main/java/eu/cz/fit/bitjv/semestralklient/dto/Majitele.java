/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralklient.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matya
 */
public class Majitele {
    List<Majitel> majitele;

    public List<Majitel> getCardReader() {
        return majitele;
    }

    public void setCardReader(List<Majitel> majitele) {
        this.majitele = majitele;
    }
}
