package org.ltaddey.players;

import org.ltaddey.pieces.Piece;

import java.util.List;

public class Player {
    private String color;
    private List<Piece> pieces;

    public Player(String color, List<Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
}
