package it.tndigitale.goosegame.web;

import it.tndigitale.goosegame.domain.GooseGame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IT417 on 31/05/2018.
 */
public class HttpGooseGameServlet extends HttpServlet {

    private GooseGame game = new GooseGame();

    public HttpGooseGameServlet(GooseGame game) {
        this.game = game;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello world");
    }

    private String addPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("name");
        String result = game.action("add player " + playerName);
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.endsWith("/players")) {
            response.getWriter().println(addPlayer(request, response));
        } else if (uri.endsWith("/move")) {
            String playerName = request.getParameter("name");
            System.out.println(playerName);
            String dice1 = request.getParameter("dice1");
            String dice2 = request.getParameter("dice2");
            String result = game.action("move " + playerName + " " + dice1 + ", " + dice2);
            response.getWriter().println(result);
        }else  {
            response.getWriter().println("Hello world");
        }
    }
}
