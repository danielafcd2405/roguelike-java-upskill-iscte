package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.game.Dungeon;
import pt.upskill.projeto1.game.Engine;
import pt.upskill.projeto1.game.StatusBar;
import pt.upskill.projeto1.gui.ImageMatrixGUI;
import pt.upskill.projeto1.gui.ImageTile;
import pt.upskill.projeto1.objects.enemies.Enemy;
import pt.upskill.projeto1.rogue.utils.Position;
import pt.upskill.projeto1.rogue.utils.Vector2D;

import java.util.List;

public class Hero extends MovingObject {

    private int points = 50;
    private final int maxHP = 80;
    private int currentHP;
    private final int baseATK = 10;
    private int totalATK; // Calculado com base no baseATK + bonus de armas

    public Hero(Position position) {
        super(position);
        this.currentHP = maxHP;
        this.totalATK = baseATK;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
        if (this.points < 0) {
            this.points = 0;
        }
        System.out.println("Points: " + getPoints() + " (" + points + ") | ");
    }

    public int getBaseATK() {
        return baseATK;
    }

    public int getTotalATK() {
        return totalATK;
    }

    public void setTotalATK(int totalATK) {
        this.totalATK = totalATK;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (currentHP > maxHP) {
            this.currentHP = maxHP;
        }
        // chamar método para alterar a Status Bar
        StatusBar.updateStatusBar(this.maxHP, this.currentHP);
    }

    @Override
    public String getName() {
        return "Hero";
    }

    public void move(Vector2D vector2D) {
        Position novaPosicao = this.getPosition().plus(vector2D);

        // Se a novaPosicao estiver fora das coordenadas da sala, avança para uma nova sala
        if (novaPosicao.getX() < 0 || novaPosicao.getX() > 9 || novaPosicao.getY() < 0 || novaPosicao.getY() > 9) {
            Dungeon.changeRoom(this.getPosition());
            return;
        }

        if (canMove(novaPosicao)) {
            this.setPosition(novaPosicao);
            this.setPoints(-1); // cada movimento remove 1 ponto
            Engine.mensagensStatus += "Pontuação: " + getPoints() + " | ";
        } else if (isEnemy(novaPosicao)) {
            System.out.println("Hero atacou");
            Engine.mensagensStatus += "Ataque!! | ";
            attackEnemy(novaPosicao);
        } else {
            Engine.mensagensStatus += "Ouch! Esta parede é sólida. | ";
        }

    }

    private boolean canConsume(Position position) {

        return false;
    }

    private void consumeItem(Position position) {

    }

    private boolean canPickUp(Position position) {

        return false;
    }

    private void pickUpItem(Position position) {

    }

    private boolean isEnemy(Position position) {
        List<ImageTile> tiles = Dungeon.getDungeonMap().get(Dungeon.getCurrentRoom());

        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(position) && tile instanceof Enemy) {
                return true;
            }
        }
        return false;
    }

    private void attackEnemy(Position position) {
        ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
        List<ImageTile> tiles = Dungeon.getDungeonMap().get(Dungeon.getCurrentRoom());

        for (ImageTile tile : tiles) {
            if (tile.getPosition().equals(position) && tile instanceof Enemy) {
                ((Enemy) tile).setCurrentHP(((Enemy) tile).getCurrentHP() - this.getTotalATK());
                System.out.println("Inimigo recebeu dano. currentHP: " + ((Enemy) tile).getCurrentHP());
                // Se o HP ficar a 0, o inimigo é removido da sala e o hero recebe os pontos correspondentes
                if (((Enemy) tile).getCurrentHP() <= 0) {
                    this.setPoints(((Enemy) tile).getExpPoints());
                    tiles.remove(tile);
                    // Tenho que remover do dungeonMap e dos tiles que estão na gui
                    // Se remover só do dungeonMap, o inimigo fica visível após ser removido
                    // É impossível interagir com ele e após sair e voltar entrar na sala ele desaparece de vez
                    // Removendo da gui também, ele dasaparece logo após ser derrotado
                    gui.removeImage(tile);
                    System.out.println("Inimigo derrotado");
                    Engine.mensagensStatus += "Inimigo derrotado! + " + ((Enemy) tile).getExpPoints() + " pontos | ";
                    return;
                }
            }
        }
    }


    @Override
    public boolean isTraversable(MovingObject movingObject) {
        return false;
    }
}
