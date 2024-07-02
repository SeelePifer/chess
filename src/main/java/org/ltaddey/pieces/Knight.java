package org.ltaddey.pieces;

import org.ltaddey.board.Board;

import java.util.logging.Logger;

public class Knight implements Piece{
    private int x, y;
    private final String color;
    private boolean hasMoved;
    private static final Logger LOGGER = Logger.getLogger(Knight.class.getName());

    public Knight(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.hasMoved = false;
    }

    @Override
    public void move(Board board, int toX, int toY) {
        if (isValidMove(board, toX, toY)) {
            board.movePiece(x, y, toX, toY);
            x = toX;
            y = toY;
            hasMoved = true;
        } else {
            LOGGER.info("Invalid move");
        }
    }

    private boolean isValidMove(Board board, int toX, int toY) {
        int deltaX = Math.abs(toX - x);
        int deltaY = Math.abs(toY - y);

        // Check if the move is two squares in one direction and one square in the other direction
        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }

        // Check if the destination square is empty or contains an opponent's piece
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
