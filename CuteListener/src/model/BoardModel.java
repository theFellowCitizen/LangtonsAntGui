/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.AntStarter;

/**
 *
 * @author ramin
 */
public class BoardModel {
    
    private int width;
    private int height;
    private boolean black;

    public BoardModel(int width, int height, boolean black) {
        this.width = width;
        this.height = height;
        this.black = black;
    }
    
    public void setupBoard(int a, int i) {
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < a; x++) {
            	AntStarter.boardy[x][y] = new Tile(x, y, false);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}
    
}
