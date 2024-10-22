package pt.upskill.projeto1.objects.enemies;

import javafx.geometry.Pos;
import pt.upskill.projeto1.objects.MovingObject;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

public class Bat extends Enemy {

    public Bat(Position position) {
        super(position);
        currentHP = maxHP = 15;
        atk = 5;
        expPoints = 5;
    }

    @Override
    public void move(Vector2D vector2D) {
        Position novaPosicao = this.getPosition().plus(vector2D);

        if (canMove(novaPosicao)) {
            this.setPosition(novaPosicao);
        } else if (isHero(novaPosicao)) {
            System.out.println("Bat atacou");
            attackHero(novaPosicao);
        }
    }

    @Override
    public boolean isTraversable(MovingObject movingObject) {
        return false;
    }

    @Override
    public String getName() {
        return "Bat";
    }

}
