import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class SearchTest {
        @Test
    public void testFindsNearestCheese() throws Exception {
        char[][] maze = {
            {'o', 'o', 'o', 'o', 'c', 'w', 'c', 'o'},
            {'w', 'o', 'o', 'w', 'w', 'c', 'w', 'o'},
            {'o', 'o', 'o', 'o', 'R', 'w', 'o', 'o'},
            {'o', 'o', 'w', 'w', 'w', 'o', 'o', 'o'},
            {'o', 'o', 'o', 'o', 'c', 'o', 'o', 'o'}
        };

        int[] result = Search.nearestCheese(maze);
        assertArrayEquals(new int[]{0, 4}, result);
    }

    @Test
    public void testNoRatThrowsEscapedRatException() {
        char[][] maze = {
            {'o', 'o', 'o'},
            {'c', 'o', 'c'},
            {'o', 'o', 'o'}
        };

        assertThrows(EscapedRatException.class, () -> Search.nearestCheese(maze));
    }

    @Test
    public void testMultipleRatsThrowsCrowdedMazeException() {
        char[][] maze = {
            {'R', 'o', 'R'},
            {'o', 'c', 'o'},
            {'o', 'o', 'o'}
        };

        assertThrows(CrowdedMazeException.class, () -> Search.nearestCheese(maze));
    }

    @Test
    public void testNoReachableCheeseThrowsHungryRatException() {
        char[][] maze = {
            {'R', 'w', 'c'},
            {'w', 'w', 'w'},
            {'c', 'w', 'c'}
        };

        assertThrows(HungryRatException.class, () -> Search.nearestCheese(maze));
    }

    @Test
    public void testRatAlreadyNextToCheese() throws Exception {
        char[][] maze = {
            {'R', 'c', 'o'},
            {'o', 'o', 'o'},
            {'o', 'o', 'o'}
        };

        int[] result = Search.nearestCheese(maze);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    public void testMultipleCheesesSameDistance() throws Exception {
        char[][] maze = {
            {'o', 'c', 'o'},
            {'R', 'o', 'c'},
            {'o', 'o', 'o'}
        };

        int[] result = Search.nearestCheese(maze);

        // It could either go to (0,1) or (1,2)
        boolean isValid = (result[0] == 0 && result[1] == 1) || (result[0] == 1 && result[1] == 2);
        assertTrue(isValid, "Result should be either (0,1) or (1,2)");
    }
}