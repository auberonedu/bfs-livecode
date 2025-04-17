import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void testNearestCheese_NormalCase() throws Exception {
        char[][] maze = {
            {'o', 'o', 'o', 'o', 'c', 'w', 'c', 'o'},
            {'w', 'o', 'o', 'w', 'w', 'c', 'w', 'o'},
            {'o', 'o', 'o', 'o', 'R', 'w', 'o', 'o'},
            {'o', 'o', 'w', 'w', 'w', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o', 'c', 'o', 'o', 'o'},
        };

        int[] result = Search.nearestCheese(maze);
        assertArrayEquals(new int[]{0, 4}, result);
    }

    @Test
    void testNearestCheese_NoRat_ThrowsEscapedRatException() {
        char[][] maze = {
            {'o', 'o', 'c'},
            {'o', 'w', 'o'},
            {'c', 'o', 'o'}
        };

        assertThrows(EscapedRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    void testNearestCheese_MultipleRats_ThrowsCrowdedMazeException() {
        char[][] maze = {
            {'R', 'o', 'R'},
            {'o', 'w', 'c'},
            {'c', 'o', 'o'}
        };

        assertThrows(CrowdedMazeException.class, () -> {
            Search.nearestCheese(maze);
        });
    }

    @Test
    void testNearestCheese_NoReachableCheese_ThrowsHungryRatException() {
        char[][] maze = {
            {'R', 'w', 'c'},
            {'w', 'w', 'w'},
            {'c', 'w', 'c'}
        };

        assertThrows(HungryRatException.class, () -> {
            Search.nearestCheese(maze);
        });
    }
}
