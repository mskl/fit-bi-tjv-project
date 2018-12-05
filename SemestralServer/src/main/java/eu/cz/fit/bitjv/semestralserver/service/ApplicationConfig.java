/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralserver.service;

import eu.cz.fit.bitjv.semestralserver.misc.FillDummy;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author matya
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(eu.cz.fit.bitjv.semestralserver.misc.FillDummy.class);
        resources.add(eu.cz.fit.bitjv.semestralserver.service.AutoFacadeREST.class);
        resources.add(eu.cz.fit.bitjv.semestralserver.service.MajitelFacadeREST.class);
        resources.add(eu.cz.fit.bitjv.semestralserver.service.PrujezdFacadeREST.class);
        resources.add(eu.cz.fit.bitjv.semestralserver.service.ZavoraFacadeREST.class);
    }
    
}
