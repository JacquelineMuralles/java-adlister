package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
        request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: ensure the submitted information is valid
        //getParameter because it's a Post
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        if(!validRegistrationInput(username, email, password, confirm)){
            //further on we could put a message here instead of just redirecting back.
            request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request,response);
            return;

        }
        //you need a return after this so system doesn't crash
        if(confirm.equals(password)){
            request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request,response);
            return;

        }
        // TODO: create a new user based off of the submitted information
        User user = new User(username,email,password);
        // TODO: if a user was successfully created, send them to their profile
        DaoFactory.getUserDao().insert(user);
        response.sendRedirect("/profile");
    }

    private boolean validRegistrationInput(String username, String email, String password, String confirm) {
        if(!(isValidUsername(username) && isValidEmail(email) && isValidPassword(password))) return false;
        return true;
    }

    private boolean isValidPassword(String password) {
        return true;
    }

    private boolean isValidEmail(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21\\\\x23-\\\\x5b\\\\x5d-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21-\\\\x5a\\\\x53-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])+)\\\\])";
        return true;
    }

    private boolean isValidUsername(String username) {
        if(username.length() >= 4 && username.length() > 20) return false;
        //below we are validating username
        Pattern pattern = Pattern.compile("[A-Za-z][A-Za-z0-9_]+");
        if (!pattern.matcher(username).matches()) return false;
        return true;
    }
}
