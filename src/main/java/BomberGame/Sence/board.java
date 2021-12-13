package BomberGame.Sence;
import BomberGame.Entity.BombAndFlame.Bomb;
import BomberGame.Entity.Enemy.Balloon;
import BomberGame.Entity.Enemy.Oneal;
import BomberGame.Entity.Entity;
import BomberGame.Entity.Tiles.*;
import BomberGame.GameLoop;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Player.Player;
import BomberGame.GameController.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;

public class board {
    static Scene scene;
    static Group root;
    static Canvas canvas;
    static GraphicsContext gc;
    static boolean sceneStarted;
    public static int enemy;
    static Player player;
    static int CELL_SIZE = GloVariables.CELL_SIZE;
    static Label textLEVEL;
    static Label textPOINT;
    static Label textNumberOfEnemy;

    static {
        sceneStarted = false;
    }
    public static Vector<Tile> tiles = new Vector<>();
    public static Vector<Entity> entities = new Vector<>();
    public static Vector<Balloon> balloons = new Vector<>();

    public static Vector<Entity> getEntities() {
        return entities;
    }
    public static Vector<Tile> getTiles() { return tiles;}

    static Comparator<Entity> layerComparator = Comparator.comparingInt(Entity::getLayer);

    public static void addEntityToGame(Entity e) {
        if (!entities.contains(e)) {
            entities.add(e);
            entities.sort(layerComparator);
        }
    }

    private static void init() {
        root = new Group();
        scene = new Scene(root, GloVariables.SCENE_WIDTH, GloVariables.SCENE_HEIGHT);
        canvas = new Canvas(GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_HEIGHT);
        canvas.setLayoutX(0);
        canvas.setLayoutY(48);
        textLEVEL = new Label("LEVEL: " + GloVariables.Level);
        textLEVEL.setLayoutX(0);
        textLEVEL.setLayoutY(0);
        textLEVEL.setFont(Font.font("Bauhaus 93", 32));

        textPOINT = new Label("POINT: " + GloVariables.point);
        textPOINT.setLayoutX(144);
        textPOINT.setLayoutY(0);
        textPOINT.setFont(Font.font("Bauhaus 93", 32));

        textNumberOfEnemy = new Label("Number of Enemy: " + board.enemy);
        textNumberOfEnemy.setLayoutX(288);
        textNumberOfEnemy.setLayoutY(0);
        textNumberOfEnemy.setFont(Font.font("Bauhaus 93", 32));


        root.getChildren().addAll(textLEVEL,textPOINT,textNumberOfEnemy);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0,0, GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_HEIGHT);
        GameLoop.start(gc);
        try {
            loadMap();
        } catch (IOException e) {
            System.err.println("Unable to load map");
        }
        Event.attachEventHandlers(scene);
    }

    public static void loadMap() throws IOException {
        String path = "Resourses/maps/Level" + GloVariables.Level +".txt";
        // nếu Glovariables mà lớn hơn 5 thì tự động sinh map
        try (BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            String line;
            int y = 0;
            while ((line = inputStream.readLine()) != null) {
                line += "c";
                for (int x = 0; x < line.length(); x++) {
                    tiles.add(new Grass(x * GloVariables.CELL_SIZE, y * GloVariables.CELL_SIZE));
                    switch (line.charAt(x)) {
                        case '#' -> tiles.add(new Wall(x * CELL_SIZE, y * CELL_SIZE));
                        case 'p' -> setPlayer(new Player(x * CELL_SIZE, y * CELL_SIZE));
                        case '*' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, -1));
                        case '1' -> balloons.add(new Balloon(x * CELL_SIZE, y * CELL_SIZE));
                        case 'x' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, 0));
                        case 'f' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, 1));
                        case 'b' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, 2));
                        case '2' -> balloons.add(new Oneal(x * CELL_SIZE, y * CELL_SIZE));
                        case 's' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, 3));
                        case 'i' -> tiles.add(new Brick(x * CELL_SIZE, y * CELL_SIZE, 4));
                    }
                }
                y++;
            }
        }
        enemy = balloons.size();
        for (Balloon balloon : balloons) {
            addEntityToGame(balloon);
        }
        for (Tile tile : tiles) {
            addEntityToGame(tile);
        }
        System.gc();
    }

    public static void setupScene() {
        if (!sceneStarted) {
            init();
            sceneStarted = true;
        }
    }

    public static void NewGame() {
        entities.clear();
        balloons.clear();
        tiles.clear();
        if (!GloVariables.passLevel) {
            Player.step = 4;
            Player.bombCount = 1;
            Bomb.radius = 1;
        }
        try {
            loadMap();
        } catch (IOException e) {
            System.err.println("Unable to load map file.");
            System.exit(1);
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static void setPlayer(Player p) {
        player = p;
        addEntityToGame(player);
    }

    public static Player getPlayer() {
        return player;
    }

    public static Vector<Balloon> getBalloons() {
        return balloons;
    }

    public static void removeBrick(int x, int y) {
        for(Tile t : tiles) {
            if(t instanceof Brick) {
                if((t.positionX == x) && (t.positionY == y)){
                    tiles.remove(t);
                }
            }
        }
    }

    public static void setTextBoard(int s) {
        textLEVEL.setText("LEVEL: " + s);
        textLEVEL.setFont(Font.font("Bauhaus 93", 32));
    }

    public static void setTextPOINT(int s) {
        textPOINT.setText("POINT: " + s);
        textPOINT.setFont(Font.font("Bauhaus 93", 32));
    }

    public static void setTextNumberOfEnemy(int s) {
        textNumberOfEnemy.setText("Number Of Enemy: " + s);
        textNumberOfEnemy.setFont(Font.font("Bauhaus 93", 32));
    }

    public static int getEnemy() {
        return balloons.size();
    }


}