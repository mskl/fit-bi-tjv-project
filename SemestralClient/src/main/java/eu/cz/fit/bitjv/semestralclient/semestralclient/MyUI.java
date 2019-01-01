package eu.cz.fit.bitjv.semestralclient.semestralclient;


import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import eu.cz.fit.bitjv.semestralclient.dto.Auto;
import eu.cz.fit.bitjv.semestralclient.dto.AutoBox;
import eu.cz.fit.bitjv.semestralclient.dto.Prujezd;
import eu.cz.fit.bitjv.semestralclient.dto.PrujezdBox;
import eu.cz.fit.bitjv.semestralclient.dto.Zavora;
import eu.cz.fit.bitjv.semestralclient.dto.ZavoraBox;
import eu.cz.fit.bitjv.semestralclient.rest.AutoRESTClient;
import eu.cz.fit.bitjv.semestralclient.rest.PrujezdRESTClient;
import eu.cz.fit.bitjv.semestralclient.rest.ZavoraRESTClient;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
        layout.setMargin(false);
        
        /*
            Create the REST clients
        */
        AutoRESTClient ac = new AutoRESTClient();
        ZavoraRESTClient zc = new ZavoraRESTClient();
        PrujezdRESTClient pc = new PrujezdRESTClient();
        
        /*
            The top navigation bar
        */
        // Auto layout
        VerticalLayout autoLayout = new VerticalLayout();
        autoLayout.setSizeFull();
        autoLayout.setVisible(true);
        layout.addComponent(autoLayout);
        // Zavora layout
        VerticalLayout zavoraLayout = new VerticalLayout();
        zavoraLayout.setSizeFull();
        zavoraLayout.setVisible(false);
        layout.addComponent(zavoraLayout);
        // Prujezd layout
        VerticalLayout prujezdLayout = new VerticalLayout();
        prujezdLayout.setSizeFull();
        prujezdLayout.setVisible(false);
        layout.addComponent(prujezdLayout);
        
        // Menu bar navigation
        MenuBar barmenu = new MenuBar();
        layout.addComponentAsFirst(barmenu);
        MenuItem autoMenu = barmenu.addItem("Auta");
        autoMenu.setCheckable(true);
        autoMenu.setChecked(true); // auto is selected at the beginning
        MenuItem zavoraMenu = barmenu.addItem("Závory");
        zavoraMenu.setCheckable(true);
        MenuItem prujezdMenu = barmenu.addItem("Průjezdy");
        prujezdMenu.setCheckable(true);
        barmenu.setSizeFull();
        
        autoMenu.setCommand(menu -> {
            autoMenu.setChecked(true);
            zavoraMenu.setChecked(false);
            prujezdMenu.setChecked(false);
            autoLayout.setVisible(true);
            zavoraLayout.setVisible(false);
            prujezdLayout.setVisible(false);
        });
        zavoraMenu.setCommand(menu -> {
            autoMenu.setChecked(false);
            zavoraMenu.setChecked(true);
            prujezdMenu.setChecked(false);
            autoLayout.setVisible(false);
            zavoraLayout.setVisible(true);
            prujezdLayout.setVisible(false);
        });
        prujezdMenu.setCommand(menu -> {
            autoMenu.setChecked(false);
            zavoraMenu.setChecked(false);
            prujezdMenu.setChecked(true);
            autoLayout.setVisible(false);
            zavoraLayout.setVisible(false);
            prujezdLayout.setVisible(true);
        });
        
        /*
            The global grid definitions
        */
        AutoBox auta = ac.findAllAuta_JSON(AutoBox.class);
        Grid<Auto> autoGrid = initAutoGrid(auta.getAutobox(), ac);
        // Search Bar
        TextField autoSearchBar = new TextField("search");
        autoLayout.addComponent(autoSearchBar); 
        autoLayout.addComponent(autoGrid);
        autoLayout.setExpandRatio(autoGrid, 0.8f);
        ZavoraBox zavory = zc.findAllZavory_JSON(ZavoraBox.class);
        Grid<Zavora> zavoraGrid = initZavoraGrid(zavory.getZavorabox(), zc);
        zavoraLayout.addComponent(zavoraGrid);
        zavoraLayout.setExpandRatio(zavoraGrid, 0.8f);
        PrujezdBox prujezdy = pc.findAllPrujezdy_JSON(PrujezdBox.class);
        Grid<Prujezd> prujezdGrid = initPrujezdGrid(prujezdy.getPrujezdbox(), pc);
        prujezdLayout.addComponent(prujezdGrid);
        prujezdLayout.setExpandRatio(prujezdGrid, 0.8f);
        
        /*
            Auto search bar
        */
        autoSearchBar.setWidth("100%");
        autoSearchBar.addValueChangeListener(event -> {
            if(autoSearchBar.isEmpty()) {
                fillAutoGrid(autoGrid, ac);
            }
            else {
                AutoBox searchResults = ac.searchAuto_JSON(AutoBox.class, autoSearchBar.getValue());
                autoGrid.setItems(searchResults.getAutobox());
            }
        });
        
        /*
        ========================================================================\
            Auta grid layout
        */
        // The grid showing all cars
        
        // The form to edit the cars
        FormLayout autoForm = new FormLayout();
        autoForm.setMargin(false);
        autoForm.setWidth("38.2%");
        TextField autoIdField = new TextField("ID");
        autoIdField.setEnabled(false);
        TextField autoNazevField = new TextField("Název");
        autoNazevField.setWidth("100%");
        TextField autoSPZField = new TextField("SPZ");
        autoSPZField.setWidth("100%");
        
        Button aEdit = new Button("Vytvořit");
        aEdit.addClickListener(e -> {
            if ("".equals(autoIdField.getValue())) {
                // Create a new auto
                Auto auto = new Auto(autoNazevField.getValue(), autoSPZField.getValue());
                ac.create_JSON(auto);
                autoNazevField.clear();
                autoSPZField.clear();
            } else {
                // Edit an existing auto
                Auto eAuto = ac.find_XML(Auto.class, autoIdField.getValue());
                if (eAuto != null) {
                    eAuto.setNazev(autoNazevField.getValue());
                    eAuto.setSPZ(autoSPZField.getValue());
                    ac.edit_XML(eAuto, autoIdField.getValue());
                }
            }

            fillAutoGrid(autoGrid, ac);
            fillPrujezdGrid(prujezdGrid, pc);
        });
        VerticalLayout autoEditBar = new VerticalLayout(autoIdField, autoNazevField, autoSPZField, aEdit);
        autoForm.addComponents(autoEditBar);
        autoLayout.addComponent(autoForm);
        
        autoGrid.addSelectionListener(event -> {
           if (event.getAllSelectedItems().size() == 1) {
               Auto vybrane = event.getAllSelectedItems().stream().findFirst().get();
               autoIdField.setValue(vybrane.getId().toString());
               autoNazevField.setValue(vybrane.getNazev());
               autoSPZField.setValue(vybrane.getSPZ());
               aEdit.setCaption("Editovat");
           }
           else {
               autoIdField.clear();
               autoNazevField.clear();
               autoSPZField.clear();
               aEdit.setCaption("Vytvořit");
           }
        });
        
        /*
        ========================================================================
            Zavory layout
        */
        
        // The form to edit zavory
        FormLayout zavoraForm = new FormLayout();
        zavoraForm.setMargin(false);
        zavoraForm.setWidth("38.2%");
        TextField zavoraIdField = new TextField("ID");
        zavoraIdField.setEnabled(false);
        TextField zavoraUmisteniField = new TextField("Umístění");
        zavoraUmisteniField.setWidth("100%");
        TextField zavoraCenaField = new TextField("Cena za průjezd");
        zavoraCenaField.setWidth("100%");
        
        Button zEdit = new Button("Vytvořit");
        zEdit.addClickListener(e -> {
            if ("".equals(zavoraIdField.getValue())) {
                // Create a new zavora
                Zavora zavora = new Zavora(zavoraUmisteniField.getValue(), 
                        Float.parseFloat(zavoraCenaField.getValue()));
                
                zc.create_JSON(zavora);
                zavoraUmisteniField.clear();
                zavoraCenaField.clear();
            } else {
                // Edit an existing zavora
                Zavora eZavora = zc.find_XML(Zavora.class, zavoraIdField.getValue());
                if (eZavora != null) {
                    eZavora.setUmisteni(zavoraUmisteniField.getValue());
                    eZavora.setCenaZaPrujezd(Float.parseFloat(zavoraCenaField.getValue()));
                    zc.edit_XML(eZavora, zavoraIdField.getValue());
                }
            }
            
            fillZavoraGrid(zavoraGrid, zc);
            fillPrujezdGrid(prujezdGrid, pc);
        });
        VerticalLayout zavoraEditBar = new VerticalLayout(zavoraIdField, zavoraUmisteniField, zavoraCenaField, zEdit);
        zavoraForm.addComponents(zavoraEditBar);
        zavoraLayout.addComponent(zavoraForm);
        
        zavoraGrid.addSelectionListener(event -> {
           if (event.getAllSelectedItems().size() == 1) {
               Zavora vybrana = event.getAllSelectedItems().stream().findFirst().get();
               zavoraIdField.setValue(vybrana.getId().toString());
               zavoraUmisteniField.setValue(vybrana.getUmisteni());
               zavoraCenaField.setValue(Float.toString(vybrana.getCenaZaPrujezd()));
               zEdit.setCaption("Editovat");
           }
           else {
               zavoraIdField.clear();
               zavoraUmisteniField.clear();
               zavoraCenaField.clear();
               zEdit.setCaption("Vytvořit");
           }
        });
        
        /*
        ========================================================================
            Prujezdy layout
        */

        // The form to edit zavory
        FormLayout prujezdForm = new FormLayout();
        prujezdForm.setMargin(false);
        prujezdForm.setWidth("38.2%");
        TextField prujezdIdField = new TextField("ID");
        prujezdIdField.setEnabled(false);
        zavoraIdField.setEnabled(false);
        // Auto combo box
        ComboBox<Auto> prujezdAutoComboBox = new ComboBox<Auto>("Auto");
        prujezdAutoComboBox.setItemCaptionGenerator(p -> p.getNazev());
        AutoBox listAut = ac.findAllAuta_JSON(AutoBox.class);
        prujezdAutoComboBox.setItems(listAut.getAutobox());
        // Umisteni combo box
        ComboBox<Zavora> prujezdZavoraComboBox = new ComboBox<Zavora>("Umístění");
        prujezdZavoraComboBox.setItemCaptionGenerator(p -> p.getUmisteni());
        ZavoraBox listZavor = zc.findAllZavory_JSON(ZavoraBox.class);
        prujezdZavoraComboBox.setItems(listZavor.getZavorabox());
        // The date picker
        DateTimeField prujezdDatumField = new DateTimeField("Datum průjezdu");
        // Edit button
        Button pEdit = new Button("Vytvořit");
        pEdit.addClickListener(e -> {
            if ("".equals(prujezdIdField.getValue())) {
                // Create a new prujezd
                // might throw errors if the window is empty
                try {
                    Auto a = prujezdAutoComboBox.getSelectedItem().get();
                    Zavora z = prujezdZavoraComboBox.getSelectedItem().get();
                    Date d = Date.from(prujezdDatumField.getValue().
                            atZone(ZoneId.systemDefault()).toInstant());
                    Prujezd p = new Prujezd(d, a, z);
                    pc.create_JSON(p);
                    // clear the fields
                    prujezdIdField.clear();
                    prujezdAutoComboBox.clear();
                    prujezdZavoraComboBox.clear();
                    prujezdDatumField.clear();
                } 
                catch (Exception ex) {
                    Notification.show("Failed to create prujezd", 
                            Notification.Type.HUMANIZED_MESSAGE);
                }
            } else {
                // Edit an existing prujezd
                Prujezd ePrujezd = pc.find_XML(Prujezd.class, prujezdIdField.getValue());
                if (ePrujezd != null) {
                    try {
                        Auto a = prujezdAutoComboBox.getSelectedItem().get();
                        Zavora z = prujezdZavoraComboBox.getSelectedItem().get();
                        Date d = Date.from(prujezdDatumField.getValue().
                            atZone(ZoneId.systemDefault()).toInstant());
                        
                        ePrujezd.setAuto(a);
                        ePrujezd.setZavora(z);
                        ePrujezd.setDatum_prujezdu(d);
                        pc.edit_XML(ePrujezd, prujezdIdField.getValue());
                        fillPrujezdGrid(prujezdGrid, pc);
                    } catch (Exception ex) {
                        Notification.show("Failed to edit prujezd", 
                                Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
            }
            
            fillPrujezdGrid(prujezdGrid, pc);
        });
        
        VerticalLayout prujezdEditBar = new VerticalLayout(prujezdIdField, 
                prujezdAutoComboBox, prujezdZavoraComboBox, prujezdDatumField, pEdit);
        prujezdForm.addComponents(prujezdEditBar);
        prujezdLayout.addComponent(prujezdForm);
        
        prujezdGrid.addSelectionListener(event -> {
            if (event.getAllSelectedItems().size() == 1) {
                // Set the value fields based on the selection
                Prujezd vybrany = event.getAllSelectedItems().stream().findFirst().get();
                prujezdIdField.setValue(vybrany.getId().toString());
                prujezdAutoComboBox.setSelectedItem(vybrany.getAuto());
                prujezdZavoraComboBox.setSelectedItem(vybrany.getZavora());
                LocalDateTime ldt = LocalDateTime.ofInstant(vybrany.getDatum_prujezdu().toInstant(), 
                        ZoneId.systemDefault());
                prujezdDatumField.setValue(ldt);
                pEdit.setCaption("Editovat");
           }
           else {
               pEdit.setCaption("Vytvořit");
               prujezdIdField.clear();
               prujezdAutoComboBox.clear();
               prujezdZavoraComboBox.clear();
               prujezdDatumField.clear();
           }
        });
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    private Grid<Auto> initAutoGrid(List<Auto> auta, AutoRESTClient ac) {
        Grid<Auto> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(auta);
        grid.addColumn(Auto::getId).setCaption("ID");
        grid.addColumn(Auto::getNazev).setCaption("Název");
        grid.addColumn(Auto::getSPZ).setCaption("SPZ");
        // Delete button
        grid.addComponentColumn(auto -> {
            Button del = new Button();
            del.setIcon(VaadinIcons.TRASH);
            setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
            del.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            del.addClickListener(e -> {
                ac.remove(auto.getId().toString());  
                AutoBox tmp = ac.findAllAuta_JSON(AutoBox.class);
                grid.setItems(tmp.getAutobox());
                
            });
            
            return del;
        }).setWidth(120).setCaption("Odstranit");
        return grid;
    }
    
    private Grid<Zavora> initZavoraGrid(List<Zavora> zavory, ZavoraRESTClient zc) {
        Grid<Zavora> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(zavory);
        grid.addColumn(Zavora::getId).setCaption("ID");
        grid.addColumn(Zavora::getUmisteni).setCaption("Umístění");
        grid.addColumn(Zavora::getCenaZaPrujezd).setCaption("Cena za průjezd");
        // Delete button
        grid.addComponentColumn(zavora -> {
            Button del = new Button();
            del.setIcon(VaadinIcons.TRASH);
            setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
            del.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            del.addClickListener(e -> {
                zc.remove(zavora.getId().toString());  
                ZavoraBox tmp = zc.findAllZavory_JSON(ZavoraBox.class);
                grid.setItems(tmp.getZavorabox());
            });
            
            return del;
        }).setWidth(120).setCaption("Odstranit");
        return grid;
    }
    
    private Grid<Prujezd> initPrujezdGrid(List<Prujezd> prujezdy, PrujezdRESTClient pc) {
        Grid<Prujezd> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(prujezdy);
        grid.addColumn(Prujezd::getId).setCaption("ID");
        grid.addColumn(Prujezd::getDatum_prujezdu).setCaption("Datum průjezdu");
        grid.addColumn((autoEntity) -> {
            return autoEntity.getAuto().getNazev();
        }).setCaption("Auto");
        grid.addColumn((zavoraEntity) -> {
            return zavoraEntity.getZavora().getUmisteni();
        }).setCaption("Umístění");
        // Delete button
        grid.addComponentColumn(prujezd -> {
            Button del = new Button();
            del.setIcon(VaadinIcons.TRASH);
            setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
            del.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            del.addClickListener(e -> {
                pc.remove(prujezd.getId().toString());  
                PrujezdBox tmp = pc.findAllPrujezdy_JSON(PrujezdBox.class);
                grid.setItems(tmp.getPrujezdbox());
            });
            
            return del;
        }).setWidth(120).setCaption("Odstranit");
        return grid;
    }
    
    private void fillAutoGrid(Grid<Auto> autoGrid, AutoRESTClient ac) {
        AutoBox tmp = ac.findAllAuta_JSON(AutoBox.class);
        autoGrid.setItems(tmp.getAutobox());
    }
    
    private void fillZavoraGrid(Grid<Zavora> zavoraGrid, ZavoraRESTClient zc) {
        ZavoraBox tmp = zc.findAllZavory_JSON(ZavoraBox.class);
        zavoraGrid.setItems(tmp.getZavorabox());
    }
    
    private void fillPrujezdGrid(Grid<Prujezd> prujezdGrid, PrujezdRESTClient pc) {
        PrujezdBox tmp = pc.findAllPrujezdy_JSON(PrujezdBox.class);
        prujezdGrid.setItems(tmp.getPrujezdbox());
    }
    
    
}
