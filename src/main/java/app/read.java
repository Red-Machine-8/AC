package app;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/read")
public class read extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        String number = req.getParameter("Number");
        String datefrom = req.getParameter("Datefrom");
        String dateto = req.getParameter("Dateto");


        ArrayList<sekcii> sekcii = DB.select(number,datefrom,dateto);
        String mas= new Gson().toJson(sekcii);
        writer.print(mas);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
