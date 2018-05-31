package it.tndigitale.goosegame.domain;

/**
 * Created by IT417 on 31/05/2018.
 */
public class GooseGameFake extends GooseGame {

    @Override
    public String action(String action) {
        if ("add player Pippo".equals(action)) return "players: Pippo";
        return null;
    }
}

