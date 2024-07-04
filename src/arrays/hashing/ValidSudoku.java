package arrays.hashing;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class ValidSudoku {
    public static void main(String[] args) {
        assert isValidSudoku2(new char[][] {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });
        assert !isValidSudoku2(new char[][] {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });
        assert !isValidSudoku2(new char[][] {
                {'7', '.', '.', '.', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '8', '6', '5', '.', '.', '.'},
                {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '9', '.', '.', '.'},
                {'.', '.', '.', '.', '5', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        });
    }

    //Runtime 2-3 ms, Beats 81.66-60.04%
    //Memory beats 46.66-73.10%
    public static boolean isValidSudoku(char[][] board) {
        int n = board.length;
        HashSet<Character>[][] boxesSet = new HashSet[][]{
                {new HashSet<Character>(), new HashSet<Character>(), new HashSet<Character>()},
                {new HashSet<Character>(), new HashSet<Character>(), new HashSet<Character>()},
                {new HashSet<Character>(), new HashSet<Character>(), new HashSet<Character>()}
        };
        HashSet<Character> rowSet = new HashSet<>();
        HashSet<Character> columnSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    if (boxesSet[i/3][j/3].contains(board[i][j]) || rowSet.contains(board[i][j])) {
                        return false;
                    }
                    boxesSet[i/3][j/3].add(board[i][j]);
                    rowSet.add(board[i][j]);
                }
                if (board[j][i] != '.') {
                    if (columnSet.contains(board[j][i])) {
                        return false;
                    }
                    columnSet.add(board[j][i]);
                }
            }
            columnSet.clear();
            rowSet.clear();
        }
        return true;
    }

    //Прикольно вышло, но эффективнее не стало. Есть ещё вариант кодирования при помощи String. Это красиво, но крайне не эффективно.
    public static boolean isValidSudoku2(char[][] board) {
        int n = board.length;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    if (seen.contains(board[i][j] + (i / 3) * 10 + (j / 3) * 100) || seen.contains(board[i][j] * 10 + i*100)) {
                        //(0-2)(0-2)(1-9) //(0-9)(1-9)(0)
                        return false;
                    }
                    seen.add(board[i][j] * 10 + i*100);
                    seen.add(board[i][j] + (i / 3) * 10 + (j / 3) * 100);
                }
                if (board[j][i] != '.') {
                    //(0-9)(1-9)(0000)
                    if (seen.contains(board[j][i] * 10000 + i*100000)) {
                        return false;
                    }
                    seen.add(board[j][i] * 10000 + i*100000);
                }
            }
        }
        return true;
    }
}
