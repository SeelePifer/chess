package org.ltaddey.pieces;

import org.ltaddey.board.Board;

import java.util.logging.Logger;

public class King implements Piece{

    private int x, y;
    private final String color;
    private boolean hasMoved;
    private static final Logger LOGGER = Logger.getLogger(King.class.getName());

    public King(int x, int y, String color) {
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
        } else if (canCastle(board, toX, toY)) {
            castle(board, toX, toY);
        } else {
            LOGGER.info("Invalid move");
        }
    }

    private boolean isValidMove(Board board, int toX, int toY) {
        int deltaX = Math.abs(toX - x);
        int deltaY = Math.abs(toY - y);

        // Check if the move is one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        // Check if the destination square is empty or contains an opponent's piece
        return board.isEmpty(toX, toY) || board.isOpponentPiece(toX, toY, color);
    }
    private boolean canCastle(Board board, int toX, int toY) {
        // Check if the king and the rook have not moved
        if (hasMoved || board.getPiece(toX, toY).hasMoved()) {
            return false;
        }

        // Check if there are no pieces between the king and the rook
        int step = toX > x ? 1 : -1;
        for (int i = x + step; i != toX; i += step) {
            if (!board.isEmpty(i, y)) {
                return false;
            }
        }

        // Check if the king is not in check and does not pass through a square that is attacked
        // This requires a method to check if the king is in check, which is not included in this code

        return true;
    }

    private void castle(Board board, int toX, int toY) {
        // Move the king
        board.movePiece(x, y, toX, toY);
        x = toX;

        // Move the rook
        int rookX = toX > x ? toX + 1 : toX - 1;
        board.movePiece(rookX, y, x + (toX > x ? -1 : 1), y);

        hasMoved = true;
    }

    @Override
    public String getColor() {
        return color;
    }
    public boolean hasMoved() {
        return hasMoved;
    }
}
