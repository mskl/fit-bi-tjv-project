/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralserver.semestralentity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matya
 */
public class ZavoraBox {
    private List<Zavora> zavorabox = new ArrayList<Zavora>();

    public List<Zavora> getZavorabox() {
        return zavorabox;
    }

    public void setZavorabox(List<Zavora> zavorabox) {
        this.zavorabox = zavorabox;
    }
}
