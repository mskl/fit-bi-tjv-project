/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralclient.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matya
 */
public class PrujezdBox {
    private List<Prujezd> prujezdbox = new ArrayList<Prujezd>();

    public List<Prujezd> getPrujezdbox() {
        return prujezdbox;
    }

    public void setPrujezdbox(List<Prujezd> prujezdbox) {
        this.prujezdbox = prujezdbox;
    }
    
}
