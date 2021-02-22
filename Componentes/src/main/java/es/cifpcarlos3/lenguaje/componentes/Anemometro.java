package es.cifpcarlos3.lenguaje.componentes;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Anemometro extends Region{
    private static final double   PREFERRED_SIZE = 16;
    private static final double   MINIMUM_SIZE = 8;
    private static final double   MAXIMUM_SIZE = 1024;
    
    private boolean _frameVisible = true;
    private BooleanProperty frameVisible;
    public String direccion = "Norte";
    public String velocidad = "0";
    private StringProperty direction;
    private StringProperty speed;
    
    public Anemometro(){
        direction = new SimpleStringProperty();
        speed = new SimpleStringProperty();
        init();
        registerListener();
        draw();
    }
    
    private void init() {
        if (getWidth() <= 0 || getHeight() <= 0 ||
            getPrefWidth() <= 0 || getPrefHeight() <= 0) {
            setPrefSize(PREFERRED_SIZE, PREFERRED_SIZE);
        }
        if (getMinWidth() <= 0 || getMinHeight() <= 0) {
            setMinSize(MINIMUM_SIZE, MINIMUM_SIZE);
        }
        if (getMaxWidth() <= 0 || getMaxHeight() <= 0) {
            setMaxSize(MAXIMUM_SIZE , MAXIMUM_SIZE);
        }
    }
    
    private void registerListener(){
        widthProperty().addListener(observable -> draw());
        heightProperty().addListener(observable -> draw());
        frameVisibleProperty().addListener(observable -> draw());
        posicionProperty().addListener(observable -> changePosicion());
        speedProperty().addListener(observable -> changeSpeed());
    }
    
    public void changePosicion(){
        direccion = direction.get();
        draw();
    }
    
    public void changeSpeed(){
        velocidad = speed.get();
        draw();
    }
    
    public final BooleanProperty frameVisibleProperty() {
        if (null == frameVisible) {
            frameVisible = new SimpleBooleanProperty(this, "frameVisible", _frameVisible);
        }
        return frameVisible;
    }
    
    public final boolean isFrameVisible(){
        return null == frameVisible ? _frameVisible : frameVisible.get();
    }
    
    public final void setFrameVisible(final boolean FRAME_VISIBLE){
        if(null == frameVisible){
            _frameVisible = FRAME_VISIBLE;
        }
        else{
            frameVisible.set(FRAME_VISIBLE);
        }
    }
    
    public final StringProperty posicionProperty(){
        return direction;
    }
    
    public final StringProperty speedProperty(){
        return speed;
    }
    
    private void draw(){
        double ancho = getWidth();
        double alto = getHeight();
        
        if(ancho <= 0 || alto <= 0){
            return;
        }
        
        double centroX = ancho/2;
        double centroY = alto/2;
        
        double tamaño = ancho < alto ? ancho/2 : alto/2;
        
        //Limpia la region y dibuja de nuevo
        getChildren().clear();
        
        //ComboBox
        /*ObservableList<String> items = FXCollections.observableArrayList(
            "Norte", "Sur", "Este", "Oeste", "Noreste", "Noroeste", "Sureste", "Suroeste");

        ComboBox<String> cbx = new ComboBox<>(items);
        cbx.setTranslateX(tamaño * 0.1);
        cbx.setTranslateY(tamaño * 0.1);
        cbx.setScaleX(tamaño * 0.004);
        cbx.setScaleY(tamaño * 0.004);*/

        //Forma
        Circle marco = new Circle();
        marco.setCenterX(centroX);
        marco.setCenterY(centroY);
        marco.setRadius(tamaño * 0.80);

        Circle fondo = new Circle();
        fondo.setCenterX(centroX);
        fondo.setCenterY(centroY);
        fondo.setRadius(tamaño * 0.78);

        //Aguja
        Polygon aguja = new Polygon();
        aguja.getPoints().addAll(new Double[] {
            centroX * 1, centroY * 0.4,
            centroX * 1.08, centroY * 0.6,
            centroX * 1, centroY * 1.6,
            centroX * 0.92, centroY * 0.6});

        //Centro
        Circle centro1 = new Circle();
        centro1.setCenterX(centroX);
        centro1.setCenterY(centroY);
        centro1.setRadius(tamaño * 0.08);

        Circle centro2 = new Circle();
        centro2.setCenterX(centroX);
        centro2.setCenterY(centroY);
        centro2.setRadius(tamaño * 0.06);

        Circle centro3 = new Circle();
        centro3.setCenterX(centroX);
        centro3.setCenterY(centroY);
        centro3.setRadius(tamaño * 0.02);

        //Posiciones
        Text n = new Text("N");
        n.setFont(new Font(tamaño * 0.06));
        n.setX(centroX * 0.98);
        n.setY(centroY * 0.27);

        Text s = new Text("S");
        s.setFont(new Font(tamaño * 0.06));
        s.setX(centroX * 0.98);
        s.setY(centroY * 1.77);

        Text e = new Text("E");
        e.setFont(new Font(tamaño * 0.06));
        e.setY(centroY * 1);
        e.setX(centroX * 1.73);

        Text w = new Text("W");
        w.setFont(new Font(tamaño * 0.06));
        w.setY(centroY * 1);
        w.setX(centroX * 0.23);
        
        //Velocidad
        Text vel = new Text("Kms/h: ");
        vel.setFont(new Font(tamaño * 0.07));
        vel.setY(centroY * 1.6);
        vel.setX(centroX * 1.6);
        
        Text spd = new Text("");
        spd.setFont(new Font(tamaño * 0.07));
        spd.setY(centroY * 1.6);
        spd.setX(centroX * 1.83);

        //Estrella
        Polygon ns = new Polygon();
        ns.getPoints().addAll(new Double[]{
            centroX * 1, centroY * 0.6,
            centroX * 1.08, centroY * 1,
            centroX * 1, centroY * 1.4,
            centroX * 0.92, centroY * 1});

        Polygon we = new Polygon();
        we.getPoints().addAll(new Double[]{
            centroX * 1, centroY * 0.6,
            centroX * 1.08, centroY * 1,
            centroX * 1, centroY * 1.4,
            centroX * 0.92, centroY * 1});

        Polygon nesw = new Polygon();
        nesw.getPoints().addAll(new Double[] {
            centroX * 1, centroY * 0.7,
            centroX * 1.08, centroY * 1,
            centroX * 1, centroY * 1.3,
            centroX * 0.92, centroY * 1});

        Polygon nwse = new Polygon();
        nwse.getPoints().addAll(new Double[] {
            centroX * 1, centroY * 0.7,
            centroX * 1.08, centroY * 1,
            centroX * 1, centroY * 1.3,
            centroX * 0.92, centroY * 1});

        aguja.setFill(RED);
        fondo.setFill(WHITE);
        marco.setFill(AQUA);
        ns.setFill(LIGHTGRAY);
        we.setFill(LIGHTGRAY);
        nesw.setFill(LIGHTGRAY);
        nwse.setFill(LIGHTGRAY);
        centro1.setFill(GRAY);
        centro2.setFill(WHITE);
        centro3.setFill(DARKGRAY);
        n.setFill(RED);
        s.setFill(RED);
        e.setFill(RED);
        w.setFill(RED);
        vel.setFill(AQUA);
        spd.setFill(AQUA);

        Rotate rotar90 = new Rotate();

        rotar90.setAngle(90);
        rotar90.setPivotX(centroX);
        rotar90.setPivotY(centroY);

        Rotate rotar45 = new Rotate();

        rotar45.setAngle(45);
        rotar45.setPivotX(centroX);
        rotar45.setPivotY(centroY);
        
        we.getTransforms().add(rotar90);
        nesw.getTransforms().add(rotar45);
        nwse.getTransforms().addAll(rotar45, rotar90);

        /*cbx.valueProperty().addListener((ObservableValue<? extends String> ov, 
                String antigua, String posicion) -> {
            setPosicion(posicion);
            /*switch(posicion){
                case "Norte":
                    //Posicion inicial, apuntando al norte
                    speed.set("10");
                    break;
                case "Sur":
                    speed.set("180");
                    break;
                case "Este":
                    speed.set("90");
                    break;
                case "Oeste":
                    speed.set("270");
                    break;
            };
            draw();
        });*/
        
        getChildren().addAll(marco, fondo, ns, we, nesw, nwse, n, s, w, e, 
                aguja, centro1, centro2, centro3,/* cbx,*/ vel, spd);
        
        switch(direccion){
            case "Norte":
                //Posicion inicial, apuntando al norte
                spd.setText(velocidad);
                break;
            case "Sur":
                aguja.getTransforms().addAll(rotar90, rotar90);
                spd.setText(velocidad);
                break;
            case "Este":
                aguja.getTransforms().addAll(rotar90);
                spd.setText(velocidad);
                break;
            case "Oeste":
                aguja.getTransforms().addAll(rotar90, rotar90, rotar90);
                spd.setText(velocidad);
                break;
            case "Noreste":
                aguja.getTransforms().addAll(rotar45);
                spd.setText(velocidad);
                break;
            case "Noroeste":
                aguja.getTransforms().addAll(rotar90, rotar90, rotar90, rotar45);
                spd.setText(velocidad);
                break;
            case "Sureste":
                aguja.getTransforms().addAll(rotar90, rotar45);
                spd.setText(velocidad);
                break;
            case "Suroeste":
                aguja.getTransforms().addAll(rotar90, rotar90, rotar45);
                spd.setText(velocidad);
                break;
            default:
                break;
        }
        
        getChildren().add(aguja);
    }
}