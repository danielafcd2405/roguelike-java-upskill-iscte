package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.enemies.Skeleton;
import pt.upskill.projeto1.objects.stationary.DoorClosed;
import pt.upskill.projeto1.objects.stationary.Wall;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Hero extends MovingObject {

    private Position position;

    public Hero(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void move(Vector2D vector2D) {
        Position novaPosicao = this.getPosition().plus(vector2D);

        if (canMove(novaPosicao)) {
            this.setPosition(novaPosicao);
        }

    }

    @Override
    public boolean isTraversable(MovingObject movingObject) {
        return false;
    }
}
