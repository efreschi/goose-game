package it.tndigitale.goosegame.domain;

import java.util.Random;

/**
 * Created by IT417 on 31/05/2018.
 */
public class Dado {

    public int lancioDado() {
        return new Random().nextInt(6)+1;
    }

}
