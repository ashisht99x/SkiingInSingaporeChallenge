package skiing;

import java.util.ArrayList;

public class Skiing {

    private int[][] input;
    private ArrayList<Integer> [][] cached;

    public Skiing(int[][] input) {
        this.input = input;
        this.cached = new ArrayList[input.length][input[0].length];
    }

    public ArrayList<Integer> longestPath() {
        ArrayList<Integer> curMax= null;
        for(int ii = 0; ii< input.length; ii++) {
            for (int jj = 0; jj< input[0].length; jj++){
                if(curMax == null){
                    curMax = getLongestPath(ii,jj);
                } else {
                    curMax = maximum(curMax, getLongestPath(ii,jj));
                }
            }
        }
        return curMax;
    }

    private ArrayList<Integer> getLongestPath(int row, int column) {

        if(cached[row][column] != null) {
            return cached[row][column];
        }

        ArrayList<Integer> leftMax = new ArrayList<>();
        ArrayList<Integer> rightMax = new ArrayList<>();
        ArrayList<Integer> topMax = new ArrayList<>();
        ArrayList<Integer> bottomMax = new ArrayList<>();

        leftMax.add(input[row][column]);
        rightMax.add(input[row][column]);
        topMax.add(input[row][column]);
        bottomMax.add(input[row][column]);

        if (column-1 >= 0 && input[row][column-1] < input[row][column]) {
            leftMax.addAll(getLongestPath(row, column-1));
        }

        if (column+1 < input[0].length && input[row][column+1] < input[row][column]) {
            rightMax.addAll(getLongestPath(row, column+1));
        }

        if (row-1 >= 0 && input[row-1][column] < input[row][column]) {
            topMax.addAll(getLongestPath(row-1, column));
        }

        if (row+1 < input.length && input[row+1][column] < input[row][column]) {
            bottomMax.addAll(getLongestPath(row+1, column));
        }

        ArrayList<Integer> max = maximum(leftMax, maximum(rightMax, maximum(topMax, bottomMax)));
        cached[row][column] = max;
        return max;
    }

    private ArrayList<Integer> maximum(ArrayList<Integer> a, ArrayList<Integer> b) {
        if(a.size() != b.size()) {
            return a.size() > b.size() ? a : b;
        } else if (a.size() != 0){
            int slopeA = a.get(0) - a.get(a.size()-1);
            int slopeB = b.get(0) - b.get(b.size()-1);
            return  (slopeA > slopeB) ? a : b;
        } else {
            return a;
        }
    }
}
