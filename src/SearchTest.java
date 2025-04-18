import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SearchTest {

    @Test
    public void testNearestCheese() throws Exception {
        char[][] maze = {
            {'o','o','o','o','c','w','c','o'},
            {'w','o','o','w','w','c','w','o'},
            {'o','o','o','o','R','w','o','o'},
            {'o','o','w','w','w','o','o','o'},
            {'o','o','o','o','c','o','o','o'}
        };

        int[] result = Search.nearestCheese(maze);
        assertArrayEquals(new int[]{0, 4}, result);
    }

    @Test
    public void testThrowsCrowdedMazeException() {
        char[][] maze = {
            {'R','o','o','o','c','w','c','o'},
            {'w','R','o','w','w','c','w','R'},
            {'o','o','o','o','o','w','o','o'},
            {'o','o','w','w','w','o','o','o'},
            {'R','o','o','o','c','o','R','o'}
        };

        assertThrows(CrowdedMazeException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testThrowsEscapedRatException() {
        char[][] maze = {
            {'o','o','o','o','c','w','c','o'},
            {'w','o','o','w','w','c','w','o'},
            {'o','o','o','o','o','w','o','o'},
            {'w','o','w','w','w','o','o','o'},
            {'w','w','o','o','c','o','o','o'}
        };

        assertThrows(EscapedRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    public void testThrowsHungryRatException() {
        char[][] maze = {
            {'w','w','w','w','c','w','c','w'},
            {'w','w','w','c','w','c','w','w'},
            {'w','w','w','w','R','w','w','w'},
            {'w','w','w','c','w','c','w','w'},
            {'w','w','w','w','c','w','w','w'}
        };

        assertThrows(HungryRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }
}