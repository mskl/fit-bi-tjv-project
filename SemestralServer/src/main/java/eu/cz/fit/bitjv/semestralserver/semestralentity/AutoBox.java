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
public class AutoBox {
    private List<Auto> autobox = new ArrayList<Auto>();

    public List<Auto> getAutobox() {
        return autobox;
    }

    public void setAutobox(List<Auto> autobox) {
        this.autobox = autobox;
    }
}
