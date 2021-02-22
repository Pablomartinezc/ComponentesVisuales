package es.cifpcarlos3.lenguaje.componentes;

import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var time = new tiempo("Sureste", "10", "20Cº", "225");
        var time2 = new tiempo("Oeste", "35", "-10Cº", "90");
        var time3 = new tiempo("Noreste", "20", "10Cº", "135");
        
        var cbx = new ComboBox<tiempo>();
        cbx.getItems().addAll(time, time2, time3);
        cbx.setTranslateY(-180);
        cbx.setTranslateX(-180);
        
        Barometro bar = new Barometro();
        Anemometro ane = new Anemometro();
        termometro term = new termometro();
        
        ane.posicionProperty().bind(Bindings.selectString(cbx.valueProperty(), "dir"));
        ane.speedProperty().bind(Bindings.selectString(cbx.valueProperty(), "spd"));
        term.Mercurio().bind(Bindings.selectString(cbx.valueProperty(), "tem"));
        bar.presion().bind(Bindings.selectString(cbx.valueProperty(), "pre"));
        
        ane.setPrefWidth(400);
        ane.setPrefHeight(400);
        term.setPrefWidth(400);
        term.setPrefHeight(400);
        bar.setPrefWidth(400);
        bar.setPrefHeight(400);
        
        HBox pane = new HBox();
        ane.setMaxWidth(1024);
        bar.setMaxWidth(1024);
        ane.setMaxHeight(495);
        term.setMaxHeight(512);
        bar.setMaxHeight(512);
        
        pane.setHgrow(ane, Priority.ALWAYS);
        pane.setHgrow(term, Priority.NEVER);
        pane.setHgrow(bar, Priority.ALWAYS);
        pane.setAlignment(Pos.CENTER);
        
        //ane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //term.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //bar.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        pane.getChildren().addAll(ane, term, bar);
        
        StackPane stk = new StackPane();
        
        stk.getChildren().addAll(pane, cbx);
        
        Scene scene = new Scene(stk);
        
        stage.setTitle("Componentes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public class tiempo{
        private String dir, spd, tem, pre;
        
        public tiempo(String dir, String spd, String tem, String pre){
            this.dir = dir;
            this.spd = spd;
            this.tem = tem;
            this.pre = pre;
        }
        
        public String getDir(){
            return dir;
        }
        
        public String getSpd(){
            return spd;
        }
        
        public String getTem(){
            return tem;
        }
        
        public String getPre(){
            return pre;
        }
        
        @Override
        public String toString(){
            return  dir + ", " + spd + ", " + tem + ", " + pre;
        }
    }

}