package org.ltaddey.pieces;

import org.ltaddey.board.Board;

import java.util.logging.Logger;

public class Rook implements Piece{

    private int x, y;
    private final String color;
    private boolean hasMoved;
    private static final Logger LOGGER = Logger.getLogger(Rook.class.getName());

    public Rook(int x, int y, String color) {
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

        // Check if the move is along a rank or file
        if (deltaX != 0 && deltaY != 0) {
            return false;
        }

        // Check if the path is clear
        int stepX = (toX - x) != 0 ? (toX - x) / Math.abs(toX - x) : 0;
        int stepY = (toY - y) != 0 ? (toY - y) / Math.abs(toY - y) : 0;

        int checkX = x + stepX;
        int checkY = y + stepY;

        while (checkX != toX || checkY != toY) {
            if (!board.isEmpty(checkX, checkY)) {
                return false;
            }
            checkX += stepX;
            checkY += stepY;
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
