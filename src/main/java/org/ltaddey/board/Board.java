package org.ltaddey.board;

import org.ltaddey.pieces.*;

import java.util.logging.Logger;

public class Board {

    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());
    private Piece[][] board;
    private final String BLACK_NAME = "black";
    private final String WHITE_NAME = "white";

    public Board() {
        board = new Piece[8][8];
        //Order of black pieces
        board[0][0] = new Rook(0,0, BLACK_NAME);
        board[0][1] = new Knight(0,1, BLACK_NAME);
        board[0][2] = new Bishop(0,2, BLACK_NAME);
        board[0][3] = new Queen(0,3, BLACK_NAME);
        board[0][4] = new King(0,4, BLACK_NAME);
        board[0][5] = new Bishop(0,5, BLACK_NAME);
        board[0][6] = new Knight(0,6, BLACK_NAME);
        board[0][7] = new Rook(0,7, BLACK_NAME);
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(1,i, BLACK_NAME);
        }
        //Order of white pieces
        board[7][0] = new Rook(7,0, WHITE_NAME);
        board[7][1] = new Knight(7,1, WHITE_NAME);
        board[7][2] = new Bishop(7,2, WHITE_NAME);
        board[7][3] = new Queen(7,3, WHITE_NAME);
        board[7][4] = new King(7,4, WHITE_NAME);
        board[7][5] = new Bishop(7,5, WHITE_NAME);
        board[7][6] = new Knight(7,6, WHITE_NAME);
        board[7][7] = new Rook(7,7, WHITE_NAME);
        for(int i = 0; i < 8; i++){
            board[6][i] = new Pawn(6,i, WHITE_NAME);
        }
    }

    public Piece getPiece(int x, int y){
        return board[x][y];
    }
    public void setPiece(int x, int y, Piece piece){
        board[x][y] = piece;
    }

    public boolean isEmpty(int x, int y){
        return board[x][y] == null;
    }
    public boolean isOpponentPiece(int x, int y, String color) {
        Piece piece = getPiece(x, y);
        return piece != null && !piece.getColor().equals(color);
    }

    public void movePiece(int fromX, int fromY, int toX, int toY){
        Piece piece = getPiece(fromX, fromY);
        if (piece != null) {
            setPiece(toX, toY, piece);
            setPiece(fromX, fromY, null);
        } else {
            LOGGER.info("No piece at the initial position");
        }
    }
}
