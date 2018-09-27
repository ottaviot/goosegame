import java.util.ArrayList;
import java.util.List;

public class GooseGame {

    private List<Board> boards = new ArrayList<>();

    public String UserWrites(String input){
        String command = input.split(" ")[0];

        if(command.equals("add"))
            return AddPlayer(input);
        else
            return MovePlayer(input);
    }

    public String AddPlayer(String player){
        String newPlayer = player.split(" ")[2];

        if (IsPlayerAlreadyPresent(newPlayer))
            return newPlayer + ": already existing player";

        boards.add(new Board(newPlayer));

        return getPlayersName();
    }

    private String getPlayersName() {
        String output = "";
        for (Board currentPlayer: boards)
        {
            if (!output.isEmpty())
                output+= ", ";
            output += currentPlayer.player;
        }

        return "players: " + output;
    }

    private boolean IsPlayerAlreadyPresent(String newPlayer) {
        for (Board currentPlayer: boards) {
            if (currentPlayer.player.equals(newPlayer))
                return true;
        }
        return false;
    }


    public String MovePlayer(String command) {
        String[] temp = command.split(", | ");
        String[] dice = {temp[2], temp[3]};
        String player = temp[1];
        int move = Integer.parseInt(dice[0]) + Integer.parseInt(dice[1]);

        String startPosition = "", newPosition = "";

        for (Board currentPlayer: boards) {
            if (currentPlayer.player.equals(player)) {
                startPosition = Integer.toString(currentPlayer.position);
                newPosition = Integer.toString(currentPlayer.position += move);
            }
        }

        if(startPosition.equals("0"))
            startPosition = "Start";

        if(newPosition.equals("0"))
            newPosition = "Start";

        return player + " rolls " + dice[0]  + ", " + dice[1] + ". " +player + " moves from " + startPosition + " to " + newPosition;
    }
}
