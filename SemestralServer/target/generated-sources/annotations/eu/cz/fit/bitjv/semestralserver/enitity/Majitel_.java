package eu.cz.fit.bitjv.semestralserver.enitity;

import eu.cz.fit.bitjv.semestralserver.enitity.Auto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-04T22:44:41")
@StaticMetamodel(Majitel.class)
public class Majitel_ { 

    public static volatile SingularAttribute<Majitel, String> prijmeni;
    public static volatile SingularAttribute<Majitel, Long> id;
    public static volatile SingularAttribute<Majitel, String> jmeno;
    public static volatile ListAttribute<Majitel, Auto> auta;

}