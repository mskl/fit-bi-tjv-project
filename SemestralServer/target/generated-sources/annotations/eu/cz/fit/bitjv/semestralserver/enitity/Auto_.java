package eu.cz.fit.bitjv.semestralserver.enitity;

import eu.cz.fit.bitjv.semestralserver.enitity.Majitel;
import eu.cz.fit.bitjv.semestralserver.enitity.Prujezd;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-04T22:44:41")
@StaticMetamodel(Auto.class)
public class Auto_ { 

    public static volatile SingularAttribute<Auto, String> nazev;
    public static volatile ListAttribute<Auto, Prujezd> prujezdy;
    public static volatile SingularAttribute<Auto, Long> id;
    public static volatile SingularAttribute<Auto, Majitel> majitel;

}