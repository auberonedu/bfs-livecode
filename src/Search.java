import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {
     /**
     * Finds the location of the nearest reachable cheese from the rat's position.
     * Returns a 2-element int array: [row, col] of the closest 'c'. If there are multiple
     * cheeses that are tied for the same shortest distance to reach, return
     * any one of them.
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
     * @throws EscapedRatException if there is no rat in the maze
     * @throws CrowdedMazeException if there is more than one rat in the maze
     * @throws HungryRatException if there is no reachable cheese
     */
    public static int[] nearestCheese(char[][] maze) throws EscapedRatException, CrowdedMazeException, HungryRatException {

        // find the rat
        int[] start = findRat(maze);
        
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while(!queue.isEmpty()) {
            // get the current location
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];
            
            // check if the current location is cheese
            if (maze[curR][curC] == 'c') {
                return current;
            }

            // mark the current location as visited
            if (!visited[curR][curC]) {
                continue;
            }

            visited[curR][curC] = true;

            // get the possible moves
            List<int[]> nextMoves = possibleMoves(maze, current);
            queue.addAll(nextMoves);


        }

        // if we reach here, it means we have visited all possible locations
        throw new HungryRatException();
    }

    // possible directions to move in the maze
    public static List<int[]> possibleMoves(char[][] maze, int[] currentLoc) {
        List<int[]> moves = new ArrayList<>();
        int[][] steps = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int curR = currentLoc[0];
        int curC = currentLoc[1];

        for (int[] step : steps) {
            int newR = curR + step[0];
            int newC = curC + step[1];

            // check if the new location is within bounds and not a wall
            if (newR >= 0 && newR < maze.length && newC >= 0 && newC < maze[0].length && maze[newR][newC] != 'w') {
                moves.add(new int[]{newR, newC});
            }
        }

        return moves;
    }

    // finding the rat
    public static int[] findRat(char[][] maze) throws EscapedRatException, CrowdedMazeException {
        int ratCount = 0;
        int[] ratLocation = null;

        // looping through the maze to find the rat
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == 'R') {
                    ratLocation = new int[]{r, c};
                    ratCount++;
                }
            }
        }

        if(ratCount > 1) throw new CrowdedMazeException();
        if(ratCount == 0) throw new EscapedRatException();

        return ratLocation;
    }

}
