package com.lr2;

import java.util.ArrayList;

public class MiniMax {

    // 0 - пусто, 1 - нолики(человек),  2 - крестики(комп)
                           //|0  1  2 |3  4  5 |6  7  8|
    static int mainfield[] = {2, 1, 2, 2, 1, 2, 1, 0, 0};
    static int zero = 1;
    static int cross = 2;

    public static void main(String[] args) {
        int out;
        int recursincounter = 0;
        out = miniMaxFunciton(mainfield, cross, recursincounter);
        if (out < 100) System.out.println("Ходи на " + out + " клетку");
        else if (out == 401) System.out.println("Неверный размер игрового поля");
        else if (out == 402) System.out.println("Неверный формат данных в игровом поле");
        else if (out == 101) System.out.println("Нолики победили");
        else if (out == 102) System.out.println("Крестики победили");
        else if (out == 103) System.out.println("Ничья");
    }

    public static Integer miniMaxFunciton(int[] newfield, int player, int recursioncounter) {

        recursioncounter++;
        Move score = new Move(0, 0);

        if (newfield.length != 9 && recursioncounter == 1) {
            return score.index = 401; // неверный размер поля
        }
        else if (recursioncounter == 1) {
            for (int i = 0; i < newfield.length; i++) {
                if (newfield[i]!=2 && newfield[i]!=1 && newfield[i]!=0) return score.index = 402; // неверный формат данных в поле
            }
        }

        ArrayList<Integer> availspots;
        availspots = emptyIndexies(newfield);

        if ((isGameOver(newfield, zero)) && recursioncounter == 1) {
            return score.index = 101; // победа ноликов
        } else if (isGameOver(newfield, cross) && recursioncounter == 1) {
            return score.index = 102; // победа крестиков
        } else if (availspots.size() == 0 && recursioncounter == 1) {
            return score.index = 103; // ничья
        }

        if (isGameOver(newfield, zero)) {
            score.score = -10;
            return score.score;
        } else if (isGameOver(newfield, cross)) {
            score.score = 10;
            return score.score;
        } else if (availspots.size() == 0) {
            score.score = 0;
            return score.score;
        }

        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < availspots.size(); i++) {
            Move move = new Move(0, 0);

            move.index = availspots.get(i);

            newfield[availspots.get(i)] = player;

            if (player == cross) {
                move.score = miniMaxFunciton(newfield, zero, recursioncounter);
            } else {
                move.score = miniMaxFunciton(newfield, cross, recursioncounter);
            }

            newfield[availspots.get(i)] = 0;
            moves.add(move);
        }

        Move bestmove = new Move(0, 0);
        if (player == cross) {
            int bestscore = -10000;
            for (int i = 0; i < moves.size(); i++) {
                Move temp = moves.get(i);
                if (temp.score > bestscore) {
                    bestscore = temp.score;
                    bestmove = temp;
                }
            }
        } else {
            int bestscore = 10000;
            for (int i = 0; i < moves.size(); i++) {
                Move temp = moves.get(i);
                if (temp.score < bestscore) {
                    bestscore = temp.score;
                    bestmove = temp;
                }
            }
        }

        return bestmove.index;
    }

    public static ArrayList<Integer> emptyIndexies(int[] field) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            if (field[i] == 0) temp.add(i);
        }
        return temp;
    }

    public static boolean isGameOver(int[] field, int player) {
        if ((field[0] == player && field[1] == player && field[2] == player) ||
                (field[3] == player && field[4] == player && field[5] == player) ||
                (field[6] == player && field[7] == player && field[8] == player) ||
                (field[0] == player && field[3] == player && field[6] == player) ||
                (field[1] == player && field[4] == player && field[7] == player) ||
                (field[2] == player && field[5] == player && field[8] == player) ||
                (field[0] == player && field[4] == player && field[8] == player) ||
                (field[2] == player && field[4] == player && field[6] == player)
        ) {
            return true;
        } else return false;
    }
}