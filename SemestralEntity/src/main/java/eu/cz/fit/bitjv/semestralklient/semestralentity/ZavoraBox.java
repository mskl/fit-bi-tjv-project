/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralklient.semestralentity;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matya
 */
@XmlRootElement
public class ZavoraBox {
    private List<Zavora> zavorabox;

    public List<Zavora> getZavorabox() {
        return zavorabox;
    }

    public void setZavorabox(List<Zavora> zavorabox) {
        this.zavorabox = zavorabox;
    }
}
