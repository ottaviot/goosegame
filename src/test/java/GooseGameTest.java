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

    @Test
    public void WinPlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.UserWrites("move Pippo 30, 30");
        String position = gooseGame.UserWrites("move Pippo 1, 2");
        assertThat(position, Is.is("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!"));
    }

    @Test
    public void BouncePlayerTest(){
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.UserWrites("move Pippo 30, 30");
        String position = gooseGame.UserWrites("move Pippo 3, 2");
        assertThat(position, Is.is("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61"));
    }

    @Test
    public void AutomaticDicePlayerTest(){
        GooseGame gooseGame = new GooseGame (new MockDice(1), new MockDice(2));
        gooseGame.AddPlayer("add player Pippo");
        gooseGame.UserWrites("move Pippo 2, 2");
        String position = gooseGame.UserWrites("move Pippo");
        assertThat(position, Is.is("Pippo rolls 1, 2. Pippo moves from 4 to 7"));
    }

}
