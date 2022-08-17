package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import java.util.ArrayList;

/**
 * created by adan on 2018/7/9.
 */
public class ShuDu2 {

    //    private int[][] res;
    public void solveSudoku(int[][] shudu) {
        getResult(shudu);
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
        while (loop(shudu, resultSet) == 0) {

        }

    }

    private int loop(int[][] res, ArrayList<Integer>[][] resultList) {

        System.out.println("查询显性条件");
        for (int i = 0; i < 9; i++) { // 行
            for (int j = 0; j < 9; j++) { // 列
                if (res[i][j] == 0) {
                    // 排除法 当前行 当前列 当前九宫格没用的数据
                    ArrayList<Integer> result = getNums(res, i, j);
                    if (result.size() == 0) {
                        return -1;
                        // 异常
                    } else if (result.size() == 1) {
                        // 当计算出一个值来，回到开始重新计算
                        res[i][j] = result.get(0);
                        resultList[i][j] = null;
                        System.out.println("1 i = " + i + " , j = " + j + " result = " + result);
                        return 0;
                    } else {
                        resultList[i][j] = result;
                        // 推算 当个九宫格的其他两列都有的数据，当前列其他两行的位置不能填（其他行其他列此数或其他行当前列非空）
                        ArrayList<Integer> a1 = getJiaoSet(result, getNextLines(res, i, j));
                        if (a1.size() == 1) {
                            res[i][j] = a1.get(0);
                            resultList[i][j] = null;
                            System.out.println("2 i = " + i + " , j = " + j + " result = " + a1);
                            return 0;
                        } else {
                            // 推算 当个九宫格的其他两行都有的数据，当前行其他两列的位置不能填（其他行其他列此数或当前行其他列非空）
                            a1 = getJiaoSet(result, getNextRows(res, i, j));
                            if (a1.size() == 1) {
                                res[i][j] = a1.get(0);
                                resultList[i][j] = null;
                                System.out.println("3 i = " + i + " , j = " + j + " result = " + a1);
                                return 0;
                            }
                        }
                    }

                }// if
            }// for
        }// for

        // 已完成
        if (check(res)) {
            return 0;
        }
//        else {
//            System.out.println("not return resultList = ");
//            for (int i = 0; i < 81; i++) {
//                System.out.print(resultList[i / 9][i % 9]);
//                System.out.print("\t");
//                if (i % 9 == 8) {
//                    System.out.println();
//                }
//            }
//            for (int i = 0; i < 81; i++) {
//                System.out.print(res[i / 9][i % 9]);
//                System.out.print("\t");
//                if (i % 9 == 8) {
//                    System.out.println();
//                }
//            }
//        }

        // 以每行为单位 查询隐形条件
        System.out.println("以行为单位 查询隐性条件");
        for (int i = 0; i < 9; i++) {
            // 以每行为单位，查询待选数据相同的格子，进一步减少待选数据
            for (int index = 0; index < 8; index++) {
                if (resultList[i][index] != null) {
                    ArrayList<Integer> nums = new ArrayList();
                    for (int t = index + 1; t < 9; t++) {
                        if (resultList[i][index] != null && resultList[i][t] != null) {
                            if (equals(resultList[i][index], resultList[i][t])) {
                                nums.add(t);
                            }
                        }
                    }
                    if (nums.size() == resultList[i][index].size()) {
                        // 同一行内，有n个格共用长度为n的待选数集合 把这n个待选书从其他格的待选数中去掉
                        for (int t = 0; t < 9; t++) {
                            if (resultList[i][index] != null && resultList[i][index].size() > 0) {
                                if (resultList[i][t] != null && !nums.contains(t)) {
                                    for (int result : resultList[i][index]) {
                                        if (resultList[i][t].contains(result)) {
                                            resultList[i][t].remove(resultList[i][t].indexOf(result));
                                        }
                                    }
                                    if (resultList[i][t].size() == 1) {
                                        res[i][t] = resultList[i][t].get(0);
                                        System.out.println("4 i = " + i + " , j = " + t + " result = " + resultList[i][t]);
                                        resultList[i][t] = null;
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }// for 列
        }// for 行

        // 以列为单位 查询隐形条件
        System.out.println("以列为单位 查询隐性条件");
        for (int i = 0; i < 9; i++) {
            // 以列为单位，查询待选数据相同的格子，进一步减少待选数据
            for (int index = 0; index < 8; index++) {
                if (resultList[index][i] != null) {
                    ArrayList<Integer> nums = new ArrayList();
                    for (int t = index + 1; t < 9; t++) {
                        if (resultList[index][i] != null && resultList[t][i] != null) {
                            if (equals(resultList[index][i], resultList[t][i])) {
                                nums.add(t);
                            }
                        }
                    }
                    if (nums.size() == resultList[index][i].size()) {
                        // 同一行内，有n个格共用长度为n的待选数集合 把这n个待选书从其他格的待选数中去掉
                        for (int t = 0; t < 9; t++) {
                            if (resultList[index][i] != null && resultList[index][i].size() > 0) {
                                if (resultList[t][i] != null && !nums.contains(t)) {
                                    for (int result : resultList[index][i]) {
                                        if (resultList[t][i].contains(result)) {
                                            resultList[t][i].remove(resultList[t][i].indexOf(result));
                                        }
                                    }
                                    if (resultList[t][i].size() == 1) {
                                        res[t][i] = resultList[t][i].get(0);
                                        System.out.println("5 i = " + t + " , j = " + i + " result = " + resultList[t][i]);
                                        resultList[t][i] = null;
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }// for 行
        }// for 列

        // 以每个九宫格为单位 查询隐形条件
        System.out.println("以九宫格为单位 查询隐性条件");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 以每个九宫格为单位，查询待选数据相同的格子，进一步减杀待选数据
                ArrayList<Integer>[] lists = new ArrayList[9];
                int index = 0;
                for (int x = i * 3; x < i * 3 + 3; x++) {
                    for (int y = j * 3; y < j * 3 + 3; y++) {
                        lists[index] = resultList[x][y];
                        index++;
                    }
                }

                for (index = 0; index < 8; index++) {
                    if (lists[index] != null) {
                        ArrayList<Integer> nums = new ArrayList();
                        for (int t = index + 1; t < 9; t++) {
                            if (lists[index] != null && lists[t] != null) {
                                if (equals(lists[index], lists[t])) {
                                    nums.add(t);
                                }
                            }
                        }
                        if (nums.size() == lists[index].size()) {
                            // 同一个九宫格内，有n个格共用长度为n的待选数集合 把这n个待选书从其他格的待选数中去掉
                            for (int t = 0; t < 9; t++) {
                                if (!nums.contains(t) && lists[t] != null && lists[t].size() > 0) {
                                    for (int num : lists[index]) {
                                        if (lists[t].contains(num)) {
                                            lists[t].remove(lists[t].indexOf(num));
                                        }
                                    }
                                    if (lists[t].size() == 1) {
                                        res[i * 3 + t / 3][j * 3 + t % 3] = lists[t].get(0);
                                        System.out.println("6 i = " + t + " , j = " + i + " result = " + resultList[i * 3 + t / 3][j * 3 + t % 3]);
                                        resultList[i * 3 + t / 3][j * 3 + t % 3] = null;
                                        return 0;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        //多个答案 找待选集合数据最少的
        int minIndex = 0;
        int minSize = 9;
        for (int i = 0; i < 81; i++) {
            if (resultList[i / 9][i % 9] != null) {
                if (resultList[i / 9][i % 9].size() == 2) {
                    minIndex = i;
                    break;
                } else if (minSize > resultList[i / 9][i % 9].size()) {
                    minIndex = i;
                }
            }
        }

        int[][] temp = new int[9][9];
        for (int i = 0; i < 81; i++) {
            temp[i / 9][i % 9] = res[i / 9][i % 9];
        }
        for (int result : resultList[minIndex / 9][minIndex % 9]) {
            temp[minIndex / 9][minIndex % 9] = result;
            System.out.println("多选 res[" + (minIndex / 9) + "][" + (minIndex % 9) + "] = " + res[minIndex / 9][minIndex % 9]);
            resultList[minIndex / 9][minIndex % 9] = null;
            if (loop(temp, resultList) != -1) {
                for (int i = 0; i < 81; i++) {
                    res[i / 9][i % 9] = temp[i / 9][i % 9];
                }
                return 0;
            }
        }
        return 1;
    }

    private boolean check2(ArrayList[][] resultList) {
        for (ArrayList[] lists : resultList) {
            for (ArrayList list : lists) {
                if (list != null && list.size() == 0) {
                    return false;
                }
            }
        }
        return true;
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

    private boolean equals(ArrayList list1, ArrayList list2) {
        if (list1 == null || list2 == null
                || list1.size() == 0 || list2.size() == 0) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (Object o : list1) {
            int i = (int) o;
            if (!list2.contains(i)) {
                return false;
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