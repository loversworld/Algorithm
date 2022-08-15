package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import java.util.ArrayList;

/**
 * created by adan on 2018/7/9.
 */
public class ShuDu {

//    private int[][] res;

    private int count = 0;

    public void solveSudoku(int[][] shudu) {
        getResult(shudu);
        System.out.println("结束 count = " + count);
        System.out.println("校验结果 = " + checkRight(shudu));
    }

    private boolean checkRight(int[][] shudu) {
        for (int i = 0; i < 9; i++) {
            int countX = 0;
            int countY = 0;
            for (int j = 0; j < 9; j++) {
                countX += shudu[i][j];
                countY += shudu[j][i];
            }
            if (countX != 45) {
                return false;
            }
            if (countY != 45) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int countB = 0;
                countB += shudu[i * 3][j * 3];
                countB += shudu[i * 3][j * 3 + 1];
                countB += shudu[i * 3][j * 3 + 2];

                countB += shudu[i * 3 + 1][j * 3];
                countB += shudu[i * 3 + 1][j * 3 + 1];
                countB += shudu[i * 3 + 1][j * 3 + 2];

                countB += shudu[i * 3 + 2][j * 3];
                countB += shudu[i * 3 + 2][j * 3 + 1];
                countB += shudu[i * 3 + 2][j * 3 + 2];

                if (countB != 45) {
                    return false;
                }
            }
        }
        return true;
    }

    private void getResult(int[][] shudu) {
        // 循环计算每一个值
        ArrayList<Integer>[][] resultSet = new ArrayList[9][9];
        loop(shudu, resultSet);
    }

    /**
     * 返回 false出现异常 true结束
     *
     * @param res
     * @param resultSet
     * @return
     */
    private void loop(int[][] res, ArrayList<Integer>[][] resultSet) {

        for (int i = 0; i < 9; i++) { // 行
            for (int j = 0; j < 9; j++) { // 列
                if (res[i][j] == 0) {
                    ArrayList<Integer> result = getNums(res, i, j);
                    if (result.size() == 0) {
                        // 异常
                    } else if (result.size() == 1) {
                        System.out.println("1 i = " + i + " , j = " + j + " result = " + result);
                        System.out.println(" count = " + count);
                    } else {
                        resultSet[i][j] = result;
                        ArrayList<Integer> a1 = getJiaoSet(result, getNextLines(res, i, j));
                        if (a1.size() == 1) {
                            result = a1;
                            System.out.println("2 i = " + i + " , j = " + j + " result = " + a1);
                            System.out.println(" count = " + count);
                        } else {
                            a1 = getJiaoSet(result, getNextRows(res, i, j));
                            if (a1.size() == 1) {
                                result = a1;
                                System.out.println("3 i = " + i + " , j = " + j + " result = " + a1);
                                System.out.println(" count = " + count);
                            }
                        }
                    }

                    // 当计算出一个值来，回到开始重新计算
                    if (result != null && result.size() == 1) {
                        res[i][j] = result.get(0);
                        count++;
                        loop(res, resultSet);
                    }
                }
            }
        }
    }

    private boolean choose(int[][] shudu) {
        int minSize = 0;
        int minX = 0;
        int minY = 0;
        ArrayList<Integer> minResult = null;
        for (int i = 0; i < 9; i++) { // 行
            for (int j = 0; j < 9; j++) { // 列
                ArrayList<Integer> result = null;
                if (shudu[i][j] == 0) {
                    result = getNums(shudu, i, j);
                }
                if (result == null || result.size() < 2) {
                    return false;
                } else if (result.size() == 2) {
                    // 尝试
                    minSize = result.size();
                    minX = i;
                    minY = j;
                    minResult = result;
                    break;
                } else {
                    if (minSize != 0 && result.size() < minSize) {
                        minSize = result.size();
                        minX = i;
                        minY = j;
                        minResult = result;
                        // 尝试
                    }
                }
            }
        }

        if (minSize > 0) {
            test(shudu, minSize, minX, minY, minResult);
        }
        return true;

    }

    private void test(int[][] shudu, int minSize, int minX, int minY, ArrayList<Integer> minResult) {
        // 备份数据
        int[][] temp = new int[9][9];
        System.out.println("new int[][] temp minSize = " + minSize);
        for (int x = 0; x < 9; x++) { // 行
            for (int y = 0; y < 9; y++) { // 列
                temp[x][y] = shudu[x][y];
            }
        }

        // 多选
        for (int index = 0; index < minSize; index++) {
            temp[minX][minY] = minResult.get(index);
            count++;
            System.out.println("多选 选择 " + index + " i = " + minX + " , j = " + minY + " result = " + minResult);
            System.out.println(" count = " + count);
            getResult(temp);
            if (check(temp)) {
                for (int x = 0; x < 9; x++) { // 行
                    for (int y = 0; y < 9; y++) { // 列
                        shudu[x][y] = temp[x][y];
                    }
                }
                System.out.println("多选 正确 复制数据");
                break;
            } else {
                System.out.println("多选 错误 放弃");
            }
        }
    }

    private boolean check(int[][] res) {
        for (int i = 0; i < 9; i++) { // 行
            for (int j = 0; j < 9; j++) { // 列
                if (res[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> getNums(int[][] res, int i, int j) {
        ArrayList<Integer> result = getAll();
        getRowNonexistent(result, res, i);
        getLineNonexistent(result, res, j);
        getBoxNonexistent(result, res, i, j);
        return result;
    }

    private ArrayList<Integer> getNextRows(int[][] res, int row, int line) {

        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(4);
        result.add(5);
        result.add(6);
        result.add(7);
        result.add(8);
        result.add(9);

        // 相邻第一行
        int x1 = row / 3 * 3;
        if (x1 == row) {
            x1++;
        }
        // 相邻第二行
        int x2 = x1 + 1;
        if (x2 == row) {
            x2++;
        }
        ArrayList row1 = new ArrayList();
        for (int y = 0; y < line / 3 * 3; y++) {
            if (res[x1][y] > 0) {
                row1.add(res[x1][y]);
            }
        }
        for (int y = line / 3 * 3 + 3; y < 9; y++) {
            if (res[x1][y] > 0) {
                row1.add(res[x1][y]);
            }
        }
        result = getJiaoSet(result, row1);

        ArrayList row2 = new ArrayList();
        for (int y = 0; y < line / 3 * 3; y++) {
            if (res[x2][y] > 0) {
                row2.add(res[x2][y]);
            }
        }
        for (int y = line / 3 * 3 + 3; y < 9; y++) {
            if (res[x2][y] > 0) {
                row2.add(res[x2][y]);
            }
        }
        result = getJiaoSet(result, row2);

        if (result.size() == 0) {
            return result;
        }


        // 相邻第一列
        int y1 = line / 3 * 3;
        if (y1 == line) {
            y1++;
        }
        // 相邻第二列
        int y2 = y1 + 1;
        if (y2 == line) {
            y2++;
        }
        if (res[row][y1] == 0) {
            ArrayList line1 = new ArrayList();
            for (int t = 0; t < row / 3 * 3; t++) {
                if (res[t][y1] > 0) {
                    line1.add(res[t][y1]);
                }
            }
            for (int t = row / 3 * 3 + 3; t < 9; t++) {
                if (res[t][y1] > 0) {
                    line1.add(res[t][y1]);
                }
            }
            result = getJiaoSet(result, line1);
        }

        if (res[row][y2] == 0) {
            ArrayList line2 = new ArrayList();
            for (int t = 0; t < row / 3 * 3; t++) {
                if (res[t][y2] > 0) {
                    line2.add(res[t][y2]);
                }
            }
            for (int t = row / 3 * 3 + 3; t < 9; t++) {
                if (res[t][y2] > 0) {
                    line2.add(res[t][y2]);
                }
            }
            result = getJiaoSet(result, line2);
        }

        return result;
    }

    private ArrayList<Integer> getNextLines(int[][] res, int row, int line) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(4);
        result.add(5);
        result.add(6);
        result.add(7);
        result.add(8);
        result.add(9);

        // 相邻第一列
        int y1 = line / 3 * 3;
        if (y1 == line) {
            y1++;
        }
        // 相邻第二列
        int y2 = y1 + 1;
        if (y2 == line) {
            y2++;
        }

        ArrayList line1 = new ArrayList();
        for (int x = 0; x < row / 3 * 3; x++) {
            if (res[x][y1] > 0) {
                line1.add(res[x][y1]);
            }
        }
        for (int x = row / 3 * 3 + 3; x < 9; x++) {
            if (res[x][y1] > 0) {
                line1.add(res[x][y1]);
            }
        }
        result = getJiaoSet(result, line1);

        ArrayList line2 = new ArrayList();
        for (int x = 0; x < row / 3 * 3; x++) {
            if (res[x][y2] > 0) {
                line2.add(res[x][y2]);
            }
        }
        for (int x = row / 3 * 3 + 3; x < 9; x++) {
            if (res[x][y2] > 0) {
                line2.add(res[x][y2]);
            }
        }
        result = getJiaoSet(result, line2);

        if (result.size() == 0) {
            return result;
        }

        // 相邻第一列
        int x1 = row / 3 * 3;
        if (x1 == row) {
            x1++;
        }
        // 相邻第二列
        int x2 = x1 + 1;
        if (x2 == row) {
            x2++;
        }

        if (res[x1][line] == 0) {
            ArrayList row1 = new ArrayList();
            for (int y = 0; y < line / 3 * 3; y++) {
                if (res[x1][y] > 0) {
                    row1.add(res[x1][y]);
                }
            }
            for (int y = line / 3 * 3 + 3; y < 9; y++) {
                if (res[x1][y] > 0) {
                    row1.add(res[x1][y]);
                }
            }
            result = getJiaoSet(result, row1);
        }

        if (res[x2][line] == 0) {
            ArrayList row2 = new ArrayList();
            for (int y = 0; y < line / 3 * 3; y++) {
                if (res[x2][y] > 0) {
                    row2.add(res[x2][y]);
                }
            }
            for (int y = line / 3 * 3 + 3; y < 9; y++) {
                if (res[x2][y] > 0) {
                    row2.add(res[x2][y]);
                }
            }
            result = getJiaoSet(result, row2);
        }

        return result;
    }

    private ArrayList<Integer> getLine(int[][] res, int line) {
        ArrayList<Integer> lines = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (res[i][line] > 0) {
                lines.add(res[i][line]);
            }
        }
        return lines;
    }

    private void getLineNonexistent(ArrayList<Integer> list, int[][] res, int line) {
        for (int i = 0; i < 9; i++) {
            if (res[i][line] > 0 && list.contains(res[i][line])) {
                list.remove(list.indexOf(res[i][line]));
            }
        }
    }

    private ArrayList<Integer> getRow(int[][] res, int row) {
        ArrayList<Integer> rows = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (res[row][i] > 0) {
                rows.add(res[row][i]);
            }
        }
        return rows;
    }

    private void getRowNonexistent(ArrayList<Integer> list, int[][] res, int row) {
        for (int i = 0; i < 9; i++) {
            if (res[row][i] > 0 && list.contains(res[row][i])) {
                list.remove(list.indexOf(res[row][i]));
            }
        }
    }

    private ArrayList<Integer> getBox(int[][] res, int row, int line) {
        if (row < 0 || row > 8
                || line < 0 || line > 8) {
            return null;
        } else {
            row = row / 3;
            line = line / 3;
        }
        ArrayList<Integer> lines = new ArrayList<>();
        for (int i = row * 3; i < row * 3 + 3; i++) {
            for (int j = line * 3; j < line * 3 + 3; j++) {
                if (res[i][j] > 0) {
                    lines.add(res[i][j]);
                }
            }
        }
        return lines;
    }

    private void getBoxNonexistent(ArrayList<Integer> list, int[][] res, int row, int line) {
        row = row / 3;
        line = line / 3;
        for (int i = row * 3; i < row * 3 + 3; i++) {
            for (int j = line * 3; j < line * 3 + 3; j++) {
                if (res[i][j] > 0 && list.contains(res[i][j])) {
                    list.remove(list.indexOf(res[i][j]));
                }
            }
        }
    }

    /**
     * 取差集
     */
    private ArrayList<Integer> getChaSet(ArrayList<Integer> res, ArrayList<Integer> data) {
        if (res == null) {
            res = new ArrayList<>();
            res.add(1);
            res.add(2);
            res.add(3);
            res.add(4);
            res.add(5);
            res.add(6);
            res.add(7);
            res.add(8);
            res.add(9);
        }
        for (int i : data) {
            if (res.contains(i)) {
                res.remove(res.indexOf(i));
            }
        }
        return res;
    }

    /**
     * 取交集
     */
    private ArrayList<Integer> getJiaoSet(ArrayList<Integer> res, ArrayList<Integer> data) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i : data) {
            if (res.contains(i) && data.contains(i)) {
                a.add(i);
            }
        }
        return a;
    }

    private ArrayList<Integer> getAll() {
        ArrayList<Integer> all = new ArrayList<>();
        all.add(1);
        all.add(2);
        all.add(3);
        all.add(4);
        all.add(5);
        all.add(6);
        all.add(7);
        all.add(8);
        all.add(9);
        return all;
    }

}