/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralclient.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matya
 */
@XmlRootElement
public class PrujezdBox {
    private List<Prujezd> prujezdbox;

    public List<Prujezd> getPrujezdbox() {
        return prujezdbox;
    }

    public void setPrujezdbox(List<Prujezd> prujezdbox) {
        this.prujezdbox = prujezdbox;
    }
    
}
