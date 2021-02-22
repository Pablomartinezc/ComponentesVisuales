/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcarlos3.lenguaje.componentes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/**
 *
 * @author GG WP V5
 */
public class Barometro extends Region {
    
    private static final double tamMax = 1024;
    private static final double tamMin = 8;
    private static final double tamPref = 16;
    
    private final boolean visible = true;
    
    private BooleanProperty fvisible;
    private String presion = "0";
    private StringProperty preasure;
    
    public Barometro(){
        preasure = new SimpleStringProperty();
        Iniciar();
        Listener();
    }
    
    public void Iniciar(){
        
        if(getWidth()<=0 || getHeight()<=0 || getPrefWidth()<=0 || getPrefHeight()<=0){
            setPrefSize(tamPref, tamPref);
        }
        if(getMinWidth()<=0 || getMinHeight()<=0){
            setMinSize(tamMin, tamMin);
        }
        if(getMaxWidth()<=0 || getMaxHeight()<=0){
            setMaxSize(tamMax, tamMax);
        }
    }
    
    public void Listener(){
        widthProperty().addListener(observable->Dibujo());
        heightProperty().addListener(observable->Dibujo());
        fvisibleProperty().addListener(observable->Dibujo());           
        presion().addListener(observable->cambio());
    }
    
    public final BooleanProperty fvisibleProperty(){
        if(fvisible==null){
            fvisible=new SimpleBooleanProperty(this,"FrameVisible",visible);
        }
        
        return fvisible;
    } 
    
    public final StringProperty presion(){
        return preasure;
    }
    
    public void cambio(){
        presion = preasure.get();
        Dibujo();
    }
    
    public void Dibujo(){
        double ancho = getWidth();
        double alto = getHeight();
        
        if(ancho <= 0 || alto <=0){
            return;
        }
        
        double centerX = ancho /2;
        double centerY = alto / 2;
        
        double tamaño = ancho < alto ? ancho /2 : alto /2;
        
        getChildren().clear();
        
        /*ObservableList<String> items = FXCollections.observableArrayList(
            "0", "45","90","135", "180","225","270","315","360");

        ComboBox<String> box = new ComboBox<>(items);
        box.setTranslateX(tamaño * 0.1);
        box.setTranslateY(tamaño * 0.1);
        box.setScaleX(tamaño * 0.004);
        box.setScaleY(tamaño * 0.004);
        
        box.valueProperty().addListener((ObservableValue<? extends String> ov, 
                String i, String presion) -> {
            preasure.set(presion);
            Dibujo();
        });*/
        Rotate rotar90 = new Rotate();
        
        rotar90.setAngle(90);
        rotar90.setPivotX(centerX);
        rotar90.setPivotY(centerY);

        Rotate rotar45 = new Rotate();

        rotar45.setAngle(45);
        rotar45.setPivotX(centerX);
        rotar45.setPivotY(centerY);
        
        Rotate rotar180 = new Rotate();
        
        rotar180.setAngle(180);
        rotar180.setPivotX(centerX);
        rotar180.setPivotY(centerY);
        
        Rotate rotar22 = new Rotate();
        
        rotar22.setAngle(22.5);
        rotar22.setPivotX(centerX);
        rotar22.setPivotY(centerY);

        /*Arc border = new Arc(centerX, centerY, centerX*0.8, centerY*0.8, 0, 360);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(5);
        border.setFill(Color.TRANSPARENT);*/
        
        Circle border = new Circle(centerX,centerY,tamaño*0.8);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(5);
        border.setFill(Color.TRANSPARENT);        
        
        Line linea = new Line(centerX,centerY,centerX,centerY*0.4);
        
        linea.setStroke(Color.BLACK);
        
        Line linea0 = new Line(centerX,centerY*0.3,centerX,centerY*0.1);
        
        linea0.setStroke(Color.RED);
        
        Line linea22 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea22.setStroke(Color.BLACK);
        linea22.getTransforms().addAll(rotar22);
        
        Line linea45 = new Line(centerX,centerY*0.2,centerX,centerY*0.1);
        
        linea45.setStroke(Color.FUCHSIA);
        linea45.getTransforms().addAll(rotar45);
        
        Line linea67 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea67.setStroke(Color.BLACK);
        linea67.getTransforms().addAll(rotar45,rotar22);
        
        Line linea90 = new Line(centerX*1.7,centerY,centerX*1.9,centerY);
        
        linea90.setStroke(Color.RED);
        
        Line linea112 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea112.setStroke(Color.BLACK);
        linea112.getTransforms().addAll(rotar90,rotar22);
        
        Line linea135 = new Line(centerX,centerY*0.2,centerX,centerY*0.1);
        
        linea135.setStroke(Color.FUCHSIA);
        linea135.getTransforms().addAll(rotar90,rotar45);
        
        Line linea157 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea157.setStroke(Color.BLACK);
        linea157.getTransforms().addAll(rotar90,rotar45,rotar22);
        
        Line linea180 = new Line(centerX,centerY*1.7,centerX,centerY*1.9);
        
        linea180.setStroke(Color.RED);
        
        Line linea202 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea202.setStroke(Color.BLACK);
        linea202.getTransforms().addAll(rotar180,rotar22);
        
        Line linea225 = new Line(centerX,centerY*0.2,centerX,centerY*0.1);
        
        linea225.setStroke(Color.FUCHSIA);
        linea225.getTransforms().addAll(rotar180,rotar45);
        
        Line linea247 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea247.setStroke(Color.BLACK);
        linea247.getTransforms().addAll(rotar180,rotar45,rotar22);
   
        Line linea270 = new Line(centerX*0.1,centerY,centerX*0.3,centerY);
        
        linea270.setStroke(Color.RED);
        
        Line linea292 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea292.setStroke(Color.BLACK);
        linea292.getTransforms().addAll(rotar180,rotar90,rotar22);
        
        Line linea315 = new Line(centerX,centerY*0.2,centerX,centerY*0.1);
        
        linea315.setStroke(Color.FUCHSIA);
        linea315.getTransforms().addAll(rotar180,rotar90,rotar45);
        
        Line linea337 = new Line(centerX,centerY*0.15,centerX,centerY*0.1);
        
        linea337.setStroke(Color.BLACK);
        linea337.getTransforms().addAll(rotar180,rotar90,rotar45,rotar22);

        Text text0 = new Text();
        
        text0.setX(centerX);
        text0.setY(centerY*0.04);
        text0.setText("0");
        text0.setFill(Color.AQUA);
        text0.setFont(new Font(tamaño*0.06));
        
        Text text45 = new Text();
        
        text45.setX(centerX);
        text45.setY(centerY*0.04);
        text45.setText("45");
        text45.setFill(Color.AQUA);
        text45.setFont(new Font(tamaño*0.06));
        text45.getTransforms().addAll(rotar45);
        
        Text text90 = new Text();
        
        text90.setX(centerX*1.95);
        text90.setY(centerY);
        text90.setText("90");
        text90.setFill(Color.AQUA);
        text90.setFont(new Font(tamaño*0.06));
        
        Text text135 = new Text();
        
        text135.setX(centerX);
        text135.setY(centerY*0.04);
        text135.setText("135");
        text135.setFill(Color.AQUA);
        text135.setFont(new Font(tamaño*0.06));
        text135.getTransforms().addAll(rotar90,rotar45);
        
        Text text180 = new Text();
        
        text180.setX(centerX);
        text180.setY(centerY*1.95);
        text180.setText("180");
        text180.setFill(Color.AQUA);
        text180.setFont(new Font(tamaño*0.06));
        
        Text text225 = new Text();
        
        text225.setX(centerX);
        text225.setY(centerY*0.04);
        text225.setText("225");
        text225.setFill(Color.AQUA);
        text225.setFont(new Font(tamaño*0.06));
        text225.getTransforms().addAll(rotar180,rotar45);

        Text text270 = new Text();
        
        text270.setX(centerX*0.04);
        text270.setY(centerY);
        text270.setText("270");
        text270.setFill(Color.AQUA);
        text270.setFont(new Font(tamaño*0.06));
        
        Text text315 = new Text();
        
        text315.setX(centerX);
        text315.setY(centerY*0.04);
        text315.setText("315");
        text315.setFill(Color.AQUA);
        text315.setFont(new Font(tamaño*0.06));
        text315.getTransforms().addAll(rotar180,rotar90,rotar45);
        
        Text textMax = new Text();
        
        textMax.setX(centerX*0.7);
        textMax.setY(centerY);
        textMax.setText("MAX");
        textMax.setFill(Color.AQUA);
        textMax.setFont(new Font(tamaño*0.06));
        
        Text textMin = new Text();
        
        textMin.setX(centerX*1.3);
        textMin.setY(centerY);
        textMin.setText("MIN");
        textMin.setFill(Color.AQUA);
        textMin.setFont(new Font(tamaño*0.06));
        
        getChildren().addAll(border,
                linea, linea0, linea180, linea90, linea270, linea45, linea135, 
                linea225, linea315, linea22, linea67, linea112, linea157, 
                linea202, linea247, linea292, linea337, text0, text180, text270,
                text90, textMax, textMin, text45, text135, text225, text315/*, box*/);
        
        // if(preasure.get() != null){
        //    String posicion = preasure.get();
            switch(presion){
                case "0":
                case "360":
                    //Posicion inicial
                    break;
                case "45":
                    linea.getTransforms().addAll(rotar45);                   
                    break;
                case "90":
                    linea.getTransforms().addAll(rotar90);
                    break;
                case "135":
                    linea.getTransforms().addAll(rotar90, rotar45);
                    break;
                case "180":
                    linea.getTransforms().addAll(rotar180);
                    break;
                case "225":
                    linea.getTransforms().addAll(rotar180,rotar45);
                    break;
                case "270":
                    linea.getTransforms().addAll(rotar180,rotar90);
                    break;
                case "315":
                    linea.getTransforms().addAll(rotar180,rotar90,rotar45);
                    break;
            }
        //}
        getChildren().add(linea);
    }    
}
