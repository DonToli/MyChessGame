import java.util.ArrayList;
import java.util.HashMap;

public class ChessMain {
    public static void main(String[] args) {
        ChessMain main = new ChessMain();
        ArrayList<Player> players = main.createPlayer();
        for (Player player: players){
            System.out.println(player);
        }
        HashMap<String,Piece> pieceHashMap = main.createdPiece();
        System.out.println(pieceHashMap);
        main.play(players,pieceHashMap);
    }
    public ArrayList<Player> createPlayer(){
        Player whitePlayer = new Player("Harmon","asdasd@mail.ru",true,2000,18);
        Player blackPlayer = new Player("Casparov","asdd@mail.ru",false,2300,48);
        ArrayList<Player> players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);
        return players;
    }
    public HashMap<String,Piece> createdPiece(){
        King whiteKing = new King(new Spot("H",7),"white_King",true);
        King blackKing = new King(new Spot("D",8),"black_King",false);
        Knight blackKnight = new Knight(new Spot("D",6),"black_Knight",false);
        Rook whiteLeftRook = new Rook(new Spot("A",7),"left_White_Rook",true);
        Rook whiteRightRook = new Rook(new Spot("G",7),"left_Right_Rook",true);

        HashMap<String,Piece> pieceHashMap = new HashMap<>();
        pieceHashMap.put(whiteKing.getId(), whiteKing);
        pieceHashMap.put(blackKing.getId(), blackKing);
        pieceHashMap.put(blackKnight.getId(), blackKnight);
        pieceHashMap.put(whiteRightRook.getId(), whiteRightRook);
        pieceHashMap.put(whiteLeftRook.getId(), whiteLeftRook);

        return pieceHashMap;
    }
    public void play(ArrayList<Player> players,HashMap<String,Piece> hashMap){
         players.get(0).movePiece(hashMap.get("left_White_Rook"),new Spot("a",8) );
         players.get(1).movePiece(hashMap.get("black_Knight"),new Spot("c",8) );
    }

}

