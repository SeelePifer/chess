package org.ltaddey.pieces;

import org.ltaddey.board.Board;

public interface Piece {
    void move(Board board, int toX, int toY);
    String getColor();
    boolean hasMoved();
}
