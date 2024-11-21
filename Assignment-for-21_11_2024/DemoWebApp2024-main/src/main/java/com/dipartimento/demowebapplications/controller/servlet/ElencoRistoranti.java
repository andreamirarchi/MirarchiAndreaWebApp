package com.dipartimento.demowebapplications.controller.servlet;

import com.dipartimento.demowebapplications.model.Ristorante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ristoranti")
public class ElencoRistoranti extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession(true).getAttribute("username");

        if (username != null) {
            List<Ristorante> ristoranti = new ArrayList<Ristorante>();

            Ristorante r1 = new Ristorante("Le Casette di Zio Santino", "Ristorante/Pizzeria", "Rende", null);
            ristoranti.add(r1);

            Ristorante r2 = new Ristorante("Fratfrode", "Pizzeria", "Rende", null);
            ristoranti.add(r2);

            req.setAttribute("ristoranti", ristoranti);

            RequestDispatcher dispatcher = req.getRequestDispatcher("views/elenco_ristoranti.html");
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/notAuthorized.html");
            dispatcher.forward(req, resp);
        }
    }
}