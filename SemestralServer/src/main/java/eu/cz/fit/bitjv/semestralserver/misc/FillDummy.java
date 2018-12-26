/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.semestralserver.misc;

import eu.cz.fit.bitjv.semestralserver.semestralentity.Auto;
import eu.cz.fit.bitjv.semestralserver.semestralentity.Prujezd;
import eu.cz.fit.bitjv.semestralserver.semestralentity.Zavora;
import eu.cz.fit.bitjv.semestralserver.service.AutoFacadeREST;
import eu.cz.fit.bitjv.semestralserver.service.PrujezdFacadeREST;
import eu.cz.fit.bitjv.semestralserver.service.ZavoraFacadeREST;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author matya
 */
@Stateless
@Path("fill")
public class FillDummy {    

    @EJB
    private PrujezdFacadeREST p;

    @EJB
    private ZavoraFacadeREST z;

    @EJB
    private AutoFacadeREST a;
        
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String FillDummy() {        
        Auto a0 = new Auto("Nissan X-Trail", "3E0-6230");
        a.create(a0);
        Auto a1 = new Auto("Mazda MX-5", "4A2-5473");
        a.create(a1);
        Auto a2 = new Auto("Ford F-150", "2O7-4625");
        a.create(a2);      
        Auto a3 = new Auto("Mazda 5", "3J4-5682");
        a.create(a3);
        Auto a4 = new Auto("Porsche 911 GT3", "3E0-6260");
        a.create(a4);
        Auto a5 = new Auto("Jaguar E-Type V12", "5E3-0034");
        a.create(a5);
        
        
        Zavora z0 = new Zavora("Přední brána", 120);
        z.create(z0);
        Zavora z1 = new Zavora("Zadní brána", 46);
        z.create(z1);
        Zavora z2 = new Zavora("Podzemní parkoviště", 45);
        z.create(z2);
        
        Prujezd p0 = new Prujezd(new Date(), a0, z0);
        p.create(p0);
        Prujezd p1 = new Prujezd(new Date(), a5, z1);
        p.create(p1);
        Prujezd p2 = new Prujezd(new Date(), a2, z1);
        p.create(p2);
        Prujezd p3 = new Prujezd(new Date(), a2, z0);
        p.create(p3);

        return "<html><head><meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/SemestralServer/\" /></head><body></body></html>";
    }
    
}
