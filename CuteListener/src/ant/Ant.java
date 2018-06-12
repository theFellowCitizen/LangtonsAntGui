package ant;

import controller.AntStarter;

import model.Tile;

public class Ant extends Thread {

    private int direction;
    private int xPos;
    private int yPos;
    public static int boardX;
    public static int boardY;

    public Ant(int direction, int xPos, int yPos) {
        this.direction = direction;
        this.xPos = xPos;
        this.yPos = yPos;
        System.out.println("Ants position: " + xPos + ":" + yPos);
    }

    public void stepForward(Ant ant) {
        // 0 = up | 1 = right | 2 = down | 3 = left
    	System.out.println(ant.xPos + " | "+ant.yPos);
        switch (direction) {
            case 0:
                if (ant.getyPos() - 1 < 0) {
                    ant.setyPos(boardY - 1);
                }
                ant.setyPos(yPos - 1);
                ant.turn(ant);
                nextColor(ant);
                break;
            case 1:
                if (ant.getxPos() + 1 > boardX - 1) {
                    ant.setxPos(0);
                }
                ant.setxPos(xPos + 1);
                ant.turn(ant);
                nextColor(ant);
                break;
            case 2:
                if (ant.getyPos() + 1 > boardY - 1) {
                    ant.setyPos(0);
                }
                ant.setyPos(yPos + 1);
                ant.turn(ant);
                nextColor(ant);
                break;
            case 3:
                if (ant.getxPos() - 1 < 0) {
                    ant.setxPos(boardX - 1);
                }
                ant.setxPos(xPos - 1);
                ant.turn(ant);
                nextColor(ant);
                break;
        }

    }
    
    public void nextColor(Ant ant){
       if (AntStarter.boardy[ant.getxPos()][ant.getyPos()].isBlack() == true) {
    	   AntStarter.boardy[ant.getxPos()][ant.getyPos()].setBlack(false);
       } else {
    	   AntStarter.boardy[ant.getxPos()][ant.getyPos()].setBlack(true);
       }
    }

    public void turn(Ant ant) {
        // if black turn to the right
        // 0 = up | 1 = right | 2 = down | 3 = left
        if (checkIfBlack(ant)) {
            if (direction == 0) {
                direction = 3;
            } else {
                direction -= 1;
            }
        } else if (direction == 3) {
            direction = 0;
        } else {
            direction += 1;
        }
    }

    public void setupBoard(int a, int i) {
        boardX = a;
        boardY = i;
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < a; x++) {
            	AntStarter.boardy[x][y] = new Tile(x, y, false);
            }
        }
        System.out.println(boardX + " : " + boardY);
    }

    public boolean checkIfBlack(Ant ant) {    	
        return AntStarter.boardy[ant.getxPos()][ant.getyPos()].isBlack();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
