package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.application.Application.launch;

public class Snake2 {



    static int jedzenieX = 0;
    static int jedzenieY = 0;
    static int predkosc = 0;
    static int kolorjedzenia = 0;
    static int szerokosc = 25;
    static int wysokosc = 25;
    static int cornersize = 25 ; // Corners are the squares of which snake consists
    static List<Corner> waz = new ArrayList<>();

    static controls kierunek = controls.lewo;
    static boolean koniecgry = false;
    static Random rand = new Random();
    public enum controls {
        lewo,prawo,gora,dol
    }
    public static class Corner {
        int x;
        int y;

        public Corner(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }


    public void start(Stage primaryStage, int predkosc) {
        try {

            noweJedzenie();
            VBox root = new VBox();
            Canvas background = new Canvas(szerokosc*cornersize,wysokosc*cornersize); // canvas as background,graphic context to paint snake
            GraphicsContext jakis = background.getGraphicsContext2D();
            root.getChildren().add(background);
            Scene scene = new Scene(root,szerokosc*cornersize,wysokosc*cornersize);


            scene.getStylesheets().add(Snake2.class.getResource("application.css").toExternalForm());
            new AnimationTimer() {  // allows to create a timer and must override handle
                long wartoscPredkosci = 0;
                public void handle (long teraz) {
                    if ( wartoscPredkosci ==0 )
                    {
                        wartoscPredkosci = teraz;
                        tik(jakis);
                        return;
                    }
                    if (teraz - wartoscPredkosci > 1000000000/predkosc) {
                        wartoscPredkosci = teraz;
                        tik(jakis);
                    }
                }
            }.start();


            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> { // set the keyboard controls to W,A,S,D
                if (key.getCode() == KeyCode.W) {
                    if(kierunek != controls.dol) {
                        kierunek = controls.gora;
                    }
                }
                if (key.getCode() == KeyCode.A) {
                    if (kierunek != controls.prawo) {
                        kierunek = controls.lewo;
                    }
                }
                if (key.getCode() == KeyCode.S) {
                    if( kierunek != controls.gora) {
                        kierunek = controls.dol;
                    }
                }
                if (key.getCode() == KeyCode.D) {
                    if (kierunek != controls.lewo) {
                        kierunek = controls.prawo;
                    }
                }

            });

            waz.add(new Corner(szerokosc/2,wysokosc/2));         // set snake to 3 parts (corners ) in the beggining
            waz.add(new Corner(szerokosc/2,wysokosc/2));
            waz.add(new Corner(szerokosc/2,wysokosc/2));

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public static void tik (GraphicsContext gc) {
        if (koniecgry) {
            gc.setFill(Color.DARKRED);
            gc.setFont(new Font("",40));
            gc.fillText("KONIEC GRY",200,320);
            return;

        }
        for (int i= waz.size()-1 ; i >=1 ; i--) {
            waz.get(i).x = waz.get(i-1).x;
            waz.get(i).y = waz.get(i-1).y;
        }
        switch (kierunek) {
            case gora:
                waz.get(0).y--;
                if (waz.get(0).y < 0) {
                    koniecgry = true;
                }
                break;
            case dol:
                waz.get(0).y++;
                if (waz.get(0).y > wysokosc) {
                    koniecgry = true;           // koniec gry gdy uderzy w border
                }
                break;
            case lewo:
                waz.get(0).x--;
                if (waz.get(0).x < 0) {
                    koniecgry = true;
                }
                break;
            case prawo:
                waz.get(0).x++;
                if (waz.get(0).x > szerokosc) {
                    koniecgry = true;
                }
                break;
        }

        if (jedzenieX == waz.get(0).x && jedzenieY == waz.get(0).y) {
            waz.add(new Corner(-1,-1));
            noweJedzenie();
        }
        for (int i =1 ; i < waz.size();i++) {
            if (waz.get(0).x == waz.get(i).x && waz.get(0).y == waz.get(i).y) {
                koniecgry = true;
            }
        }
        //fill
        //background


        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,szerokosc*cornersize,wysokosc*cornersize);

        //punktacja
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("",30));
        gc.fillText("score:"+(predkosc-1),10,30);


        //losowy kolor jedzenia
        Color cc = Color.WHITE;

        switch (kolorjedzenia) {
            case 0: cc = Color.YELLOW;
                break;
            case 1: cc = Color.DARKBLUE;
                break;
            case 2: cc = Color.DARKGOLDENROD;
                break;
        }

        gc.setFill(cc);
        gc.fillOval(jedzenieX*cornersize,jedzenieY*cornersize,cornersize,cornersize);

        //waz
        for (Corner c: waz) {  // namalowanie weza
            gc.setFill(Color.LAVENDER);
            gc.fillRect(c.x*cornersize,c.y*cornersize,cornersize-1,cornersize-1);
            gc.setFill(Color.DARKSLATEBLUE);
            gc.fillRect(c.x*cornersize,c.y*cornersize,cornersize-2,cornersize-2);
        }


    }

    //jedzenie
    public static void noweJedzenie() {
        start: while (true) {
            jedzenieX = rand.nextInt(szerokosc);
            jedzenieY = rand.nextInt(wysokosc);
            for (Corner c : waz) {
                if (c.x == jedzenieX && c.y == jedzenieY) {
                    continue start;
                }
            }
            kolorjedzenia = rand.nextInt(5);
            predkosc ++;
            break;
        }

    }
}
