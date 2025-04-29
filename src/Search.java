import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {
    /**
     * Finds the location of the nearest reachable cheese from the rat's position.
     * Returns a 2-element int array: [row, col] of the closest 'c'. If there are
     * multiple cheeses that are tied for the same shortest distance to reach,
     * return any one of them.
     * 
     * 'R' - the rat's starting position (exactly one)
     * 'o' - open space the rat can walk on
     * 'w' - wall the rat cannot pass through
     * 'c' - cheese that the rat wants to reach
     * 
     * If no rat is found, throws EscapedRatException.
     * If more than one rat is found, throws CrowdedMazeException.
     * If no cheese is reachable, throws HungryRatException
     *
     * oooocwco
     * woowwcwo
     * ooooRwoo
     * oowwwooo
     * oooocooo
     *
     * The method will return [0,4] as the nearest cheese.
     *
     * @param maze 2D char array representing the maze
     * @return int[] location of the closest cheese in row, column format
     * @throws EscapedRatException  if there is no rat in the maze
     * @throws CrowdedMazeException if there is more than one rat in the maze
     * @throws HungryRatException   if there is no reachable cheese
     */
    public static int[] nearestCheese(char[][] maze)
            throws EscapedRatException, CrowdedMazeException, HungryRatException {
        int[] start = findRat(maze);

        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);

        // When starting off, everything in this matrix will be false
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            if (maze[curRow][curCol] == 'c') {
                return current;
            }
            if (visited[curRow][curCol]) {
                continue;
            }

            visited[curRow][curCol] = true;

            List<int[]> nextMoves = possibleMoves(maze, current);
            queue.addAll(nextMoves);
        }

        throw new HungryRatException();

        return null;
    }

    public static List<int[]> possibleMoves(char[][] maze, int[] currentLoc) {
        List<int[]> moves = new ArrayList<>();
        int[][] steps = {
                { 1, 0 },
                { -1, 0 },
                { 0, 1 },
                { 0, -1 }
        };

        int curRow = currentLoc[0];
        int curCol = currentLoc[1];

        for (int[] step : steps) {
            int newRow = curRow + step[0];
            int newCol = curCol + step[1];

            if (newRow >= 0 && newRow < maze.length &&
                    newCol >= 0 && newCol < maze[0].length &&
                    maze[newRow][newCol] != 'w') {
                moves.add(new int[] { newRow, newCol });
            }
        }

        return moves;

    }

    public static int[] findRat(char[][] maze) throws CrowdedMazeException, EscapedRatException {
        int ratCount = 0;
        int[] ratLocation = null;

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze.length; col++) {
                if (maze[row][col] == 'R') {
                    ratLocation = new int[] { row, col };
                    ratCount++;
                }
            }
        }

        if (ratCount > 1)
            throw new CrowdedMazeException();
        if (ratCount == 0)
            throw new EscapedRatException();

        return ratLocation;
    }

    public static void main(String[] args) {
    }
}