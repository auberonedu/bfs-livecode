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
        int[] ratLocation = ratLocation(maze);

        Queue<int[]> queue = new LinkedList<>();

        queue.add(ratLocation);

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];

            if(maze[curR][curC] == 'c'){
                return current;
            }

            if(visited[curR][curC]){
                continue;
            }

            visited[curR][curC] = true;

            List<int[]> moves = possibleMoves(maze, current);

            queue.addAll(moves);




        }

        throw new HungryRatException();

    }

    public static List<int[]> possibleMoves(char[][] maze, int[] current){
        
        List<int[]> moves = new ArrayList<>();

        int[][] steps = {
            {1,0},
            {-1,0},
            {0,1},
            {0, -1}
        };

        int curR = current[0];
        int curC = current[1];

        for(int[] step : steps){
            int newR = curR + step[0];
            int newC = curC + step[1];

            if(newR >= 0 && newR < maze.length && newC >= 0 && newC < maze[0].length && maze[newR][newC] != 'w'){
                moves.add(new int[] {newR, newC});
            }
        }

        return moves;

    }

    public static int[] ratLocation(char[][] maze) throws CrowdedMazeException, EscapedRatException {
        int ratCount = 0;
        int[] ratLocation = null;

        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[0].length; col++){
                if(maze[row][col] == 'R'){
                    ratLocation = new int[] {row, col};
                    ratCount++;
                }
            }
        }

        if(ratCount > 1){
            throw new CrowdedMazeException();
        }
        if(ratCount == 0){
            throw new EscapedRatException();
        }

        return ratLocation;

    }
}