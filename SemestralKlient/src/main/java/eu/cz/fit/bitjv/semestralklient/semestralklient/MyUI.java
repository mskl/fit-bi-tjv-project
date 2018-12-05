package eu.cz.fit.bitjv.semestralklient.semestralklient;

import com.googlecode.gentyref.TypeToken;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import eu.cz.fit.bitjv.semestralklient.dto.Majitel;
import eu.cz.fit.bitjv.semestralklient.rest.MajitelFacadeRESTClient;
import eu.cz.fit.bitjv.semestralklient.rest.AutoFacadeRESTClient;
import eu.cz.fit.bitjv.semestralklient.rest.ZavoraFacadeRESTClient;
import eu.cz.fit.bitjv.semestralklient.rest.PrujezdFacadeRESTClient;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
 
        MajitelFacadeRESTClient majitel_client = new MajitelFacadeRESTClient();
        AutoFacadeRESTClient auto_client = new AutoFacadeRESTClient();
        ZavoraFacadeRESTClient zavora = new ZavoraFacadeRESTClient();
        PrujezdFacadeRESTClient prujezd_client = new PrujezdFacadeRESTClient();
        
        // ListDataProvider<Majitel> majiteleDP = majitel_client.getDataProvider();
        // List<Majitel> list_majitelu = new ArrayList<Majitel>();
        TypeToken<List<Majitel>> token = new TypeToken<List<Majitel>>(){};
        
        final Label line1 = new Label("______________________________________");
        layout.addComponent(line1);
 
        Label uzivatel_label = new Label();
        uzivatel_label.setValue("Uzivatele: ");
        layout.addComponent(uzivatel_label);
 
        Button button1 = new Button("Vypis uzivatele");
        button1.addClickListener(e -> {
            uzivatel_label.setValue("Output: " + list_majitelu.toString());
        });
 
        layout.addComponent( button1 );
 
        final Label line2 = new Label("______________________________________"); 
        layout.addComponent(line2);        
 
 
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private static class ObjectMapper {

        public ObjectMapper() {
        }
    }
}
