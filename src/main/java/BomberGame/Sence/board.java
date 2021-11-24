package BomberGame.Sence;

import BomberGame.Entity.Entity;
import BomberGame.Entity.Tiles.Grass;
import BomberGame.Entity.Tiles.Tile;
import BomberGame.Entity.Tiles.Wall;
import BomberGame.GameLoop;
import BomberGame.GloVariables.GloVariables;
import BomberGame.Player.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import BomberGame.GloVariables.GloVariables.*;

public class board {
    static Scene scene;
    static Group root;
    static Canvas canvas;
    static GraphicsContext gc;
    static boolean sceneStarted;
    public static int enemy;
    static Player player;

    static {
        sceneStarted = false;
    }

    static Vector<Tile> tiles = new Vector<>();
    static Vector<Entity> entities = new Vector<>();

    public static Vector<Entity> getEntities() {
        return entities;
    }

    static Comparator<Entity> layerComparator = (o1, o2) -> {
        return Integer.compare(o1.getLayer(), o2.getLayer());
    };

    public static boolean addEntityToGame(Entity e) {
        if (!entities.contains(e)) {
            entities.add(e);
            Collections.sort(entities, layerComparator);
            return true;
        } else {
            return false;
        }
    }

    private static void init() {
        root = new Group();
        scene = new Scene(root, GloVariables.SCENE_WIDTH, GloVariables.SCENE_HEIGHT);
        canvas = new Canvas(GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, GloVariables.CANVAS_WIDTH, GloVariables.CANVAS_HEIGHT);

        GameLoop.start(gc);
        //load map
        try {
            loadMap();

        } catch (IOException e) {
            System.err.println("Unable to load map");
        }
    }

    public static void loadMap() throws IOException {
        String path = "Resourses/maps/Level1.txt";
        try (BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            String line;

            int y = 0;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line.length());
                line += "c";
                for (int x = 0; x < line.length(); x++) {
                    tiles.add(new Grass(x * GloVariables.CELL_SIZE, y * GloVariables.CELL_SIZE));
                    switch (line.charAt(x)) {
                        case '#':
                            tiles.add(new Wall(x * GloVariables.CELL_SIZE, y * GloVariables.CELL_SIZE));
                            break;
                        case 'p':
                            setPlayer(new Player(x * GloVariables.CELL_SIZE, y * GloVariables.CELL_SIZE));
                            break;
                    }
                }
                y++;
            }
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

}
