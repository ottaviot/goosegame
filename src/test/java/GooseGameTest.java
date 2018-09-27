import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class GooseGameTest {

    GooseGame gooseGame;

    @Before
    public void InstantiateGooseGame() {
        gooseGame  = new GooseGame();
    }
    @Test
    public void AddPlayerTest(){
        String player =  gooseGame.UserWrites("add player Pippo");
        assertThat(player, Is.is("players: Pippo"));
    }

    @Test
    public void AddPlayersTest(){
        gooseGame.AddPlayer("add player Pippo");
        String  players =  gooseGame.UserWrites("add player Pluto");
        assertThat(players, Is.is("players: Pippo, Pluto"));
    }

    @Test
    public void AddDuplicatePlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        String  players =  gooseGame.UserWrites("add player Pippo");
        assertThat(players, Is.is("Pippo: already existing player"));
    }

    @Test
    public void MovePlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.AddPlayer("add player Pluto");
        String position = gooseGame.UserWrites("move Pippo 4, 2");
        assertThat(position, Is.is("Pippo rolls 4, 2. Pippo moves from Start to 6"));
    }

    @Test
    public void Move2PlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.AddPlayer("add player Pluto");
        gooseGame.UserWrites("move Pippo 4, 2");
        String position = gooseGame.UserWrites("move Pluto 2, 2");
        assertThat(position, Is.is("Pluto rolls 2, 2. Pluto moves from Start to 4"));
    }

    @Test
    public void Move3PlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.AddPlayer("add player Pluto");
        gooseGame.UserWrites("move Pippo 4, 2");
        gooseGame.UserWrites("move Pluto 2, 2");
        String position = gooseGame.UserWrites("move Pippo 2, 3");
        assertThat(position, Is.is("Pippo rolls 2, 3. Pippo moves from 6 to 11"));
    }


}
