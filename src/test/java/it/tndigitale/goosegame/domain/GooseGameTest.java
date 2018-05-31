package it.tndigitale.goosegame.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IT417 on 31/05/2018.
 */
public class GooseGameTest {

    private GooseGame game;

    @Before
    public void initGame(){
        game = new GooseGame();
    }

    private String addPlayer(String playerName) {
        return  game.action("add player " + playerName);
    }
    private String movePlayer(String playerName, String dadi) {
        return  game.action("move " + playerName + " " + dadi);
    }
    @Test
    public void addFirstPlayer() {
        String result = addPlayer("Pippo");
        assertEquals("players: Pippo", result);
    }


    @Test
    public void adTwoPlayers() {
        addPlayer("Pippo");
        String result = addPlayer("Pluto");
        assertEquals("players: Pippo, Pluto", result);
    }

    @Test
    public void duplicatePlayer() {
        addPlayer("Pippo");
        String result = addPlayer("Pippo");
        assertEquals("Pippo: already existing player", result);
    }

    @Test
    public void movePlayerPippoFromStartToBridge() {
        addPlayer("Pippo");
        String result = movePlayer("Pippo", "4, 2");
        assertEquals("Pippo rolls 4, 2. Pippo moves from Start to The Bridge. Pippo jumps to 12", result);
    }

    @Test
    public void move2Player() {
        addPlayer("Pluto");
        String result = movePlayer("Pluto", "2, 2");
        assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4", result);
    }

    @Test
    public void uatMovePippo2Volte() {
        addPlayer("Pippo");
        addPlayer("Pluto");
        movePlayer("Pippo", "4, 2");
        movePlayer("Pluto", "2, 2");
        String result = movePlayer("Pippo", "2, 3");
        assertEquals("Pippo rolls 2, 3. Pippo moves from 12 to 17", result);
    }

    @Test
    public void pippoWin() {
        addPlayer("Pippo");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        String result = movePlayer("Pippo", "1, 2");
        assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!", result);
    }

    @Test
    public void pippoBounces() {
        addPlayer("Pippo");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        movePlayer("Pippo", "6, 6");
        String result = movePlayer("Pippo", "2, 3");
        assertEquals("Pippo rolls 2, 3. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61", result);
    }

    @Test
    public void gameThrowsDice() {
        addPlayer("Pippo");
        movePlayer("Pippo", "2, 2");
        game.d1 = new Dado(){
            public int lancioDado() {
                return 1;
            }
        };
        game.d2 = new Dado(){
            public int lancioDado() {
                return 2;
            }
        };

        String result = movePlayer("Pippo", "");
        assertEquals("Pippo rolls 1, 2. Pippo moves from 4 to 7", result);
    }

    @Test
    public void gameBridge() {
        addPlayer("Pippo");
        movePlayer("Pippo", "2, 2");
        game.d1 = new Dado(){
            public int lancioDado() {
                return 1;
            }
        };
        game.d2 = new Dado(){
            public int lancioDado() {
                return 1;
            }
        };

        String result = movePlayer("Pippo", "");
        assertEquals("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12", result);
    }


    @Test
    public void simpleGoose() {
        addPlayer("Pippo");
        movePlayer("Pippo", "1, 2");
        game.d1 = new Dado(){
            public int lancioDado() {
                return 1;
            }
        };
        game.d2 = new Dado(){
            public int lancioDado() {
                return 1;
            }
        };

        String result = movePlayer("Pippo", "");
        assertEquals("Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7", result);
    }
}
