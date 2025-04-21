import java.util.*;

public class Search {
    public static int[] nearestCheese(char[][] maze)
            throws EscapedRatException, CrowdedMazeException, HungryRatException {
        int[] start = findRat(maze);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0], curC = current[1];

            // If this is cheese, we’re done
            if (maze[curR][curC] == 'c') {
                return current;
            }

            if (visited[curR][curC]) {
                continue;
            }
            visited[curR][curC] = true;

            // Add all valid neighbor moves
            for (int[] next : possibleMoves(maze, current)) {
                if (!visited[next[0]][next[1]]) {
                    queue.add(next);
                }
            }
        }

        // No reachable cheese
        throw new HungryRatException();
    }

    public static List<int[]> possibleMoves(char[][] maze, int[] loc) {
        List<int[]> moves = new ArrayList<>();
        int r = loc[0], c = loc[1];

        int[][] dirs = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < maze.length &&
                nc >= 0 && nc < maze[0].length &&
                maze[nr][nc] != 'w') {
                moves.add(new int[]{nr, nc});
            }
        }
        return moves;
    }

    public static int[] findRat(char[][] maze)
            throws EscapedRatException, CrowdedMazeException {
        int count = 0;
        int[] loc = null;

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == 'R') {
                    count++;
                    loc = new int[]{r, c};
                }
            }
        }

        if (count == 0) {
            throw new EscapedRatException();
        }
        if (count > 1) {
            throw new CrowdedMazeException();
        }
        return loc;
    }
}
