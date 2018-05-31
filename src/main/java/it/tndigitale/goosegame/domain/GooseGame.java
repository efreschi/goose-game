package it.tndigitale.goosegame.domain;

import java.util.*;

/**
 * Created by IT417 on 31/05/2018.
 */
public class GooseGame {
    private Map<String, Integer> playerScore = new HashMap<String, Integer>();
    public Dado d1 = new Dado();
    public Dado d2 = new Dado();
    public static int BRIDGE = 6;
    private static final List<Integer> spaziOca = Arrays.asList(5, 9 , 14, 18, 23, 27);

    public String action(String action) {
        if(isMove(action)) {
            return move(action);
        }
        return addPlayer(action);
    }

    private String addPlayer(String action) {
        String playerName = getPlayerNameToAdd(action);
        if(isDuplicated(playerName)) return playerName + ": already existing player";
        playerScore.put(playerName, 0);

        return printPlayers();
    }

    private String printPlayers() {
        StringBuilder sb = new StringBuilder("players: ");
        playerScore.keySet().forEach(currPlayer -> sb.append(currPlayer + ", "));
        String message = sb.toString();
        if(message.endsWith(", ")) message=message.substring(0,message.length()-2);
        return message;
    }

    private boolean isDuplicated(String playerName) {
        return playerScore.keySet().contains(playerName);
    }

    private String move(String action) {
        if(!action.contains(",")){
            action = lanciaDadi(action);
        }
        String dadi = getPunteggioDadi(action);
        String playerNameMove = getPlayerToMove(action, dadi);
        String[] numbersList = dadi.split(", ");
        int start = playerScore.get(playerNameMove);
        int punteggioDadi = Integer.parseInt(numbersList[0]) + Integer.parseInt(numbersList[1]);
        int posizioneArrivo =  start + punteggioDadi;
        String mossa = playerNameMove + " rolls " + dadi + ". " + playerNameMove +
                " moves from " + ((start != 0) ? start :  "Start") + " to " + posizioneArrivo;
        if (haVinto(posizioneArrivo)) {
            playerScore.put(playerNameMove, posizioneArrivo);
            return mossa + ". " + playerNameMove + " Wins!!";
        }
        if (posizioneArrivo == BRIDGE) {
            posizioneArrivo = BRIDGE + 6;
            mossa = playerNameMove + " rolls " + dadi + ". " + playerNameMove +
                    " moves from " + ((start != 0) ? start :  "Start") + " to The Bridge. " + playerNameMove + " jumps to " + posizioneArrivo;
        }
        if (spaziOca.contains(posizioneArrivo)) {
            posizioneArrivo += punteggioDadi;
            mossa += ", The Goose. " + playerNameMove + " moves again and goes to " + posizioneArrivo;
        }
        if (posizioneArrivo > 63) {
            mossa = tornaIndietro(dadi, playerNameMove, start, posizioneArrivo);
        }
        playerScore.put(playerNameMove, posizioneArrivo);
        return mossa;
    }

    private String tornaIndietro(String dadi, String playerNameMove, int start, int posizioneArrivo) {
        String mossa;
        int diff = posizioneArrivo - 63;
        posizioneArrivo = 63 - diff;
        mossa = playerNameMove + " rolls " + dadi + ". " + playerNameMove +
                " moves from " + start + " to 63. " + playerNameMove + " bounces! " + playerNameMove + " returns to " + posizioneArrivo;
        return mossa;
    }

    private boolean haVinto(int posizioneArrivo) {
        return posizioneArrivo == 63;
    }

    private String lanciaDadi(String action) {
        int dado1 = d1.lancioDado();
        int dado2 = d2.lancioDado();
        action += " " + dado1 + ", " + dado2;
        return action;
    }

    private String getPlayerToMove(String action, String dadi) {
        action = action.replace(dadi, "");
        action = action.replace("move", "");
        return action.trim();
    }

    private String getPunteggioDadi(String action) {
        return action.substring(action.length()- 4);
    }

    private boolean isMove(String action) {
        return action.startsWith("move");
    }

    private String getPlayerNameToAdd(String player) {
        return player.replace("add player ", "");
    }
}
