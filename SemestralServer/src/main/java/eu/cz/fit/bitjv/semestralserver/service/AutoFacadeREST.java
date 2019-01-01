/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralserver.service;

import eu.cz.fit.bitjv.semestralserver.semestralentity.Auto;
import eu.cz.fit.bitjv.semestralserver.semestralentity.AutoBox;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author matya
 */
@Stateless
@Path("auto")
public class AutoFacadeREST extends AbstractFacade<Auto> {

    @PersistenceContext(unitName = "FitDBPersistenceUnit")
    private EntityManager em;

    public AutoFacadeREST() {
        super(Auto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Auto entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Auto entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Auto find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    /*
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Auto> findAll() {
        return super.findAll();
    }
    */
    
    @GET
    @Path("search/{searched}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AutoBox searchAuto(@PathParam("searched") String searched) {
        List<Auto> allAutos = super.findAll();
        AutoBox results = new AutoBox();

        for (Auto auto : allAutos) {
            if (auto.getNazev().toLowerCase().contains(searched.trim().toLowerCase())) {
                results.getAutobox().add(auto);
            }
        }

        return results;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AutoBox findAllAuta() {
        AutoBox auta = new AutoBox();
        auta.setAutobox(super.findAll());
        return auta;
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Auto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
