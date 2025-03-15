/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Movie.MovieDAO;
import Movie.MovieDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trung
 */
public class MovieController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        String url = "admin1.jsp"; 

        try {
            MovieDAO movieDAO = new MovieDAO();

            if (action == null) {
                url = "error.jsp";
            } 
            else if (action.equals("viewMovie")) {
                List<MovieDTO> movies = movieDAO.getAllMoviesWithActors();
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("admin1.jsp").forward(request, response);
            } 
            else if (action.equals("addMovie")) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
                String country = request.getParameter("country");
                double rating = Double.parseDouble(request.getParameter("rating"));
                String videoURL = request.getParameter("videoURL");
                String trailerURL = request.getParameter("trailerURL");
                String thumbnailURL = request.getParameter("thumbnailURL");
                String userName = request.getParameter("userName");

                MovieDTO newMovie = new MovieDTO( title, description, releaseYear, country, rating, videoURL, trailerURL, thumbnailURL, userName);
               
                if (movieDAO.addMovie(newMovie)) {
                    request.setAttribute("message", "Thêm phim thành công!");
                } else {
                    request.setAttribute("message", "Lỗi khi thêm phim!");
                }
                url = "admin1.jsp"; 
            } 
         
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi hệ thống!");
        }

        request.getRequestDispatcher(url).forward(request, response);
    }
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
