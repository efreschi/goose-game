package it.tndigitale.goosegame.web;

import it.tndigitale.goosegame.domain.GooseGameFake;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IT417 on 31/05/2018.
 */
public class HttpGooseGameServletTest {


    @Before
    public void startApplication() throws Exception {
        Application.start(new GooseGameFake());
    }

    @After
    public void stopApplication() throws Exception {
        Application.stop();
    }

    @Test
    public void testHttpGet() throws Exception {
        String result = doCall("http://localhost:8080/gooseGame", "GET");
        Assert.assertEquals("Hello world", result);
    }

    @Test
    public void addPlayer() throws Exception {
        doCall("http://localhost:8080/gooseGame/configure", "GET");
        String result = doCall("http://localhost:8080/gooseGame/players?name=Pippo", "POST");
        Assert.assertEquals("players: Pippo", result);
    }

    //@Test
    public void add2Players() throws Exception {
        doCall("http://localhost:8080/gooseGame/players?name=Pippo", "POST");
        String result = doCall("http://localhost:8080/gooseGame/players?name=Pluto", "POST");
        Assert.assertEquals("players: Pippo, Pluto", result);
    }

    //@Test
    public void movePippo() throws Exception {
        doCall("http://localhost:8080/gooseGame/players?name=Pippo", "POST");
        String result = doCall("http://localhost:8080/gooseGame/move?name=Pippo&dice1=1&dice2=2", "POST");
        Assert.assertEquals("Pippo rolls 1, 2. Pippo moves from Start to 3", result);
    }

    private String doCall(String url, String method) throws IOException {
        URL urlGame = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlGame.openConnection();
        connection.setRequestMethod(method);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
