/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcarlos3.lenguaje.componentes;

import static javafx.scene.paint.Color.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Josem
 */
public class termometro extends Region{
    private static final double tamañoMin = 8;
    private static final double tamañoMax = 1024;
    private static final double tamañoPref = 16;
    private final boolean frame = true;
    private BooleanProperty frameVisible;
    private String temp = "0Cº";
    private final StringProperty tem;
    
    public termometro(){
        tem = new SimpleStringProperty();
        Inicializar();
        Listener();
        Dibujar();
    }
    
    private void Inicializar(){
        if (getWidth() <= 0 || getHeight() <= 0 || getPrefWidth() <= 0 || getPrefHeight() <= 0) {
            setPrefSize(tamañoPref, tamañoPref);
        }
        if (getMinWidth() <= 0 || getMinHeight() <= 0) {
            setMinSize(tamañoMin, tamañoMin);
        }
        if (getMaxWidth() <= 0 || getMaxHeight() <= 0) {
            setMaxSize(tamañoMax /** 0.68*/, tamañoMax);
        }
    }
    private void Listener(){
        widthProperty().addListener(observable -> Dibujar());
        heightProperty().addListener(observable -> Dibujar());
        frameVisibleProperty().addListener(observable -> Dibujar());
        Mercurio().addListener(observable -> cambiarTemp());
    }
    
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", frame);
        }
        return frameVisible;
    }
    public final StringProperty Mercurio(){
        return tem;
    }
    
    public void cambiarTemp(){
        temp = tem.get();
        Dibujar();
    }
    
    public void Dibujar(){
        double anchura = getWidth();
        double altura = getHeight();
        if(anchura <= 0 || altura <= 0){
            return;
        }
        double centroX = anchura/2;
        double centroY = altura/2;
        double tamaño = anchura < altura ? anchura / 2: altura / 2;
        getChildren().clear();
        
        
        Rectangle r = new Rectangle();
        r.setX(centroX * 0.8);
        r.setY(centroY * 1.5);
        r.setWidth(anchura * 0.2);
        r.setHeight(altura * 0.2);
        r.setFill(TRANSPARENT);
        
        Rectangle recipiente = new Rectangle();
        recipiente.setX(centroX * 0.8);
        recipiente.setY(centroY * 0.1);
        recipiente.setWidth(anchura * 0.2);
        recipiente.setHeight(altura * 0.9);
        recipiente.setFill(TRANSPARENT);
        recipiente.setStroke(BLACK);
        
        Text txt0 = new Text();
        txt0.setText("0");
        txt0.setFont(new Font(tamaño *0.06));
        txt0.setX(centroX * 0.7);
        txt0.setY(centroY);
        Text txt10 = new Text();
        txt10.setText("10");
        txt10.setFont(new Font(tamaño *0.06));
        txt10.setX(centroX * 0.7);
        txt10.setY(centroY * 0.73);
        Text txt20 = new Text();
        txt20.setText("20");
        txt20.setFont(new Font(tamaño *0.06));
        txt20.setX(centroX * 0.7);
        txt20.setY(centroY * 0.5);
        Text txt30 = new Text();
        txt30.setText("30");
        txt30.setFont(new Font(tamaño *0.06));
        txt30.setX(centroX * 0.7);
        txt30.setY(centroY * 0.3);
        Text txt40 = new Text();
        txt40.setText("40");
        txt40.setFont(new Font(tamaño *0.06));
        txt40.setX(centroX * 0.7);
        txt40.setY(centroY * 0.1);
        Text txt_10 = new Text();
        txt_10.setText("-10");
        txt_10.setFont(new Font(tamaño *0.06));
        txt_10.setX(centroX * 1.3);
        txt_10.setY(centroY * 1.2);
        Text txt_20 = new Text();
        txt_20.setText("-20");
        txt_20.setFont(new Font(tamaño *0.06));
        txt_20.setX(centroX * 1.3);
        txt_20.setY(centroY * 1.4);
        Text txt_30 = new Text();
        txt_30.setText("-30");
        txt_30.setFont(new Font(tamaño *0.06));
        txt_30.setX(centroX * 1.3);
        txt_30.setY(centroY * 1.6);
        Text txt_40 = new Text();
        txt_40.setText("-40");
        txt_40.setFont(new Font(tamaño *0.06));
        txt_40.setX(centroX * 1.3);
        txt_40.setY(centroY * 1.8);
        
        /*ObservableList<String> items = FXCollections.observableArrayList(
            "40Cº", "30Cº", "20Cº", "10Cº", "0Cº", "-10Cº", "-20Cº", "-30Cº", "-40Cº");

        ComboBox<String> combo = new ComboBox<>(items);
        combo.setTranslateX(tamaño * 0.1);
        combo.setTranslateY(tamaño * 0.1);
        combo.setScaleX(tamaño * 0.004);
        combo.setScaleY(tamaño * 0.004);
        
        combo.valueProperty().addListener((ObservableValue<? extends String> ov, 
                String i, String temperatura) -> {
            tem.set(temperatura);
            Dibujar();
        });*/
        
        getChildren().addAll(r, recipiente,/* combo,*/ txt0, txt10, txt20, txt30, txt40,
                txt_10, txt_20, txt_30, txt_40);
        
        //if(tem.get() != null){
        //    String temperatura = tem.get();
            switch(temp){
                case "0Cº":
                    r.setY(centroY);
                    r.setHeight(altura * 0.45);
                    r.setFill(rgb(40,165,251));
                    break;
                case "10Cº":
                    r.setY(centroY * 0.7);
                    r.setHeight(altura * 0.6);
                    r.setFill(rgb(251,145,40));
                    break;
                case "20Cº":
                    r.setY(centroY * 0.5);
                    r.setHeight(altura * 0.7);
                    r.setFill(rgb(251,193,40));
                    break;
                case "30Cº":
                    r.setY(centroY * 0.3);
                    r.setHeight(altura * 0.8);
                    r.setFill(rgb(252,110,40));
                    break;
                case "40Cº":
                    r.setY(centroY * 0.1);
                    r.setHeight(altura * 0.9);
                    r.setFill(rgb(251,65,40));
                    break;
                case "-10Cº":
                    r.setY(centroY * 1.2);
                    r.setHeight(altura * 0.35);
                    r.setFill(rgb(40,81,251));
                    break;
                case "-20Cº":
                    r.setY(centroY * 1.4);
                    r.setHeight(altura * 0.25);
                    r.setFill(rgb(40,62,251));
                    break;
                case "-30Cº":
                    r.setY(centroY * 1.6);
                    r.setHeight(altura * 0.15);
                    r.setFill(rgb(59,40,251));
                    break;
                case"-40Cº":
                    r.setY(centroY * 1.8);
                    r.setHeight(altura * 0.05);
                    r.setFill(rgb(0,6,128));  
                default:
                    break;
            }
        //}
        
        getChildren().add(r);
    }
}
