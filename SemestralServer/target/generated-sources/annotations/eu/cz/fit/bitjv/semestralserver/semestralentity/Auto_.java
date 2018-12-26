package eu.cz.fit.bitjv.semestralserver.semestralentity;

import eu.cz.fit.bitjv.semestralserver.semestralentity.Prujezd;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-25T21:21:37")
@StaticMetamodel(Auto.class)
public class Auto_ { 

    public static volatile SingularAttribute<Auto, String> nazev;
    public static volatile ListAttribute<Auto, Prujezd> prujezdy;
    public static volatile SingularAttribute<Auto, Long> id;

}