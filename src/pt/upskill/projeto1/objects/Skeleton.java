package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Skeleton implements ImageTile {

    private Position position;

    public Skeleton(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Skeleton";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void move(Vector2D vector2D) {
        Position novaPosicao = this.getPosition().plus(vector2D);

        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        List<ImageTile> tiles = gui.getImages();

        // Lista dos tiles na novaPosição
        // Pode existir mais do que 1, Floor e Skeleton, por exemplo
        List<ImageTile> tilesInNovaPosicao = new ArrayList<>();

        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(novaPosicao)) {
                tilesInNovaPosicao.add(tile);
            }
        }

        // Verifica se algum desses tiles é um tile para onde não se pode mover
        boolean podeMover = true;
        for (ImageTile tile : tilesInNovaPosicao) {
            if (tile instanceof Wall || tile instanceof Skeleton || tile instanceof Hero || tile instanceof DoorOpen) {
                podeMover = false;
            }
        }

        if (podeMover) {
            this.setPosition(novaPosicao);
        }

    }
}
