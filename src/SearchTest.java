import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    @Test
    public void testNearestCheeseBasicCase() throws Exception {
        char[][] maze = {
            {'o','o','o','o','c','w','c','o'},
            {'w','o','o','w','w','c','w','o'},
            {'o','o','o','o','R','w','o','o'},
            {'o','o','w','w','w','o','o','o'},
            {'o','o','o','o','c','o','o','o'}
        };

        int[] expected = {0, 4}; // Closest cheese
        int[] result = Search.nearestCheese(maze);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultipleCheeseOptions() throws Exception {
        char[][] maze = {
            {'o','o','c'},
            {'R','w','c'},
            {'o','o','c'}
        };

        int[] result = Search.nearestCheese(maze);
        // Either (0,2), (1,2), or (2,2) could be valid depending on shortest path
        // (1,2) is the closest cheese
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    public void testOnlyOneRatAllowed() {
        char[][] maze = {
            {'R','o','o'},
            {'o','R','o'},
            {'o','o','c'}
        };

        assertThrows(CrowdedMazeException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testNoRatFound() {
        char[][] maze = {
            {'o','o','o'},
            {'w','w','c'},
            {'o','o','o'}
        };

        assertThrows(EscapedRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testNoCheeseReachable() {
        char[][] maze = {
            {'R','w','c'},
            {'w','w','w'},
            {'c','w','c'}
        };

        assertThrows(HungryRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testCheeseAlmostSurroundedByWalls() {
        char[][] maze = {
            {'R','w','w'},
            {'w','c','w'},
            {'w','w','w'}
        };

        assertThrows(HungryRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testRatSurroundedByWalls() {
        char[][] maze = {
            {'w','w','w'},
            {'w','R','w'},
            {'w','w','c'}
        };

        assertThrows(HungryRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }
}
