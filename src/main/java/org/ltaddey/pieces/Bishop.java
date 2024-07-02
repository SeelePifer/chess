package org.ltaddey.pieces;

import org.ltaddey.board.Board;

import java.util.logging.Logger;

public class Bishop implements Piece{

    private static final Logger LOGGER = Logger.getLogger(Bishop.class.getName());
    private int x, y;
    private final String color;

    private boolean hasMoved;

    public Bishop(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.hasMoved = false;
    }

    @Override
    public void move(Board board, int toX, int toY) {
        if(isValidMove(board, toX, toY)){
            board.movePiece(x,y,toX,toY);
            x = toX;
            y = toY;
            hasMoved = true;
        }else{
            LOGGER.info("Invalid move");
        }
    }
    private boolean isValidMove(Board board, int toX, int toY) {
        int deltaX = toX - x;
        int deltaY = toY - y;

        if(Math.abs(deltaX) != Math.abs(deltaY)) {
            return false;
        }
        int stepX = deltaX > 0 ? 1 : -1;
        int stepY = deltaY > 0 ? 1 : -1;

        int checkX = x + stepX;
        int checkY = y + stepY;

        while(checkX != toX && checkY != toY){
            if(!board.isEmpty(checkX, checkY)){
                return false;
            }
            checkX += stepX;
            checkY += stepY;
        }
        return board.isEmpty(toX, toY) || board.isOpponentPiece(toX, toY, color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}
