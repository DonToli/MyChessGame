import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class PlayerTest {

    private static ArrayList<Player> players;
    private static HashMap<String, Piece> pieceHashMap;

    @BeforeAll
    public static void setUp() {
        ChessMain main = new ChessMain();
        players = main.createPlayer();
        pieceHashMap = main.createdPiece();
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Running before each");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("Running after each");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Running after all each");
    }

    @Test
    public void validMoveTest() {
        players.get(0).movePiece(pieceHashMap.get("white_King"), new Spot("H", 8));
        Assertions.assertEquals(8, pieceHashMap.get("white_King").getSpot().getY());
    }

    @Test
    public void invalidMovePieceColorTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePiece(pieceHashMap.get("black_King"), new Spot("H", 8));

        });
    }
    @Test
    public void invalidMoveBadSpotTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePiece(pieceHashMap.get("white_King"), new Spot("H", 10));

        });
    }

    @Test
    public void playerCreationSuccessTest() {
        Player whitePlayer = new Player("Harmon", "asdasd@mail.ru", true, 2000, 18);
        Assertions.assertEquals("Harmon", whitePlayer.getName());
        Assertions.assertEquals("asdasd@mail.ru", whitePlayer.getEmail());
        Assertions.assertTrue(whitePlayer.isWhite());
        Assertions.assertEquals(2000, whitePlayer.getRank());
        Assertions.assertEquals(18, whitePlayer.getAge());
    }

    @ParameterizedTest
    @MethodSource("nameError")
    public void playerCreationIncorrectNameTest(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player(name, "asdasd@mail.ru", true, 2000, 18);
        });
    }

    @ParameterizedTest
    @MethodSource("emailError")
    public void playerCreationIncorrectEmailTest(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player("Harmon", email, true, 2000, 18);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 50, 3025})
    public void invalidRankParamsTest(int rank) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player("Harmon", "asdasd@mail.ru", true, rank, 18);
        });
    }

    static Stream<String> nameError() {
        return Stream.of("", "   ", null);
    }

    static Stream<String> emailError() {
        return Stream.of("", "   ", null, "asdasdmail.ru", "asdasd@mailru");
    }

}
