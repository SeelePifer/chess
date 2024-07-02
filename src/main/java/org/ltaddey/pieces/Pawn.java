package org.ltaddey.pieces;

import org.ltaddey.board.Board;

import java.util.Scanner;
import java.util.logging.Logger;

public class Pawn implements Piece{
    private int x, y;
    private final String color;
    private boolean hasMoved;
    private static final Logger LOGGER = Logger.getLogger(Pawn.class.getName());

    public Pawn(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.hasMoved = false;
    }

    @Override
    public void move(Board board, int toX, int toY) {
        if (isValidMove(board, toX, toY)) {
            // Mover el peón
            board.movePiece(x, y, toX, toY);
            // Actualizar la posición del peón
            x = toX;
            y = toY;
            hasMoved = true;

            if(color.equals("white") && y == 7 || color.equals("black") && y == 0){
                promote(board);
            }
        } else {
            LOGGER.info("Invalid move");
        }
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    private boolean isValidMove(Board board, int toX, int toY) {
        // Verificar si el peón se está moviendo hacia adelante
        if (color.equals("white") && toY == y + 1 && toX == x) {
            // Verificar si la casilla está vacía
            return board.isEmpty(toX, toY);
        } else if (color.equals("black") && toY == y - 1 && toX == x) {
            // Verificar si la casilla está vacía
            return board.isEmpty(toX, toY);
        }

        // Verificar si el peón está capturando una pieza del oponente
        if (color.equals("white") && toY == y + 1 && Math.abs(toX - x) == 1) {
            // Verificar si hay una pieza del oponente en la casilla
            return board.isOpponentPiece(toX, toY, color);
        } else if (color.equals("black") && toY == y - 1 && Math.abs(toX - x) == 1) {
            // Verificar si hay una pieza del oponente en la casilla
            return board.isOpponentPiece(toX, toY, color);
        }

        // El movimiento no es válido
        return false;
    }
    private void promote(Board board){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Promote pawn to (Q)ueen, (R)ook, (B)ishop, or k(N)ight?");
        String input = scanner.nextLine().toUpperCase();
        switch(input) {
            case "Q":
                board.setPiece(x, y, new Queen(x, y, color));
                break;
            case "R":
                board.setPiece(x, y, new Rook(x, y, color));
                break;
            case "B":
                board.setPiece(x, y, new Bishop(x, y, color));
                break;
            case "N":
                board.setPiece(x, y, new Knight(x, y, color));
                break;
            default:
                LOGGER.info("Invalid choice, promoting to Queen by default.");
                board.setPiece(x, y, new Queen(x, y, color));
                break;
        }
    }
}
