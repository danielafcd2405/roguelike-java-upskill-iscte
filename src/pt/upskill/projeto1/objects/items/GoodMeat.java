package pt.upskill.projeto1.objects.items;

import pt.upskill.projeto1.objects.GameObject;
import pt.upskill.projeto1.objects.Hero;
import pt.upskill.projeto1.objects.MovingObject;
import pt.upskill.projeto1.rogue.utils.Position;

public class GoodMeat extends Consumable {

    public GoodMeat(Position position) {
        super(position);
        expPoints = 5;
        restoreHP = 20;
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }

}
