package com.inti.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.inti.model.Client;
import com.inti.util.HibernateUtil;


@WebServlet("/client")
public class NouveauClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Session s=HibernateUtil.getSessionFactory().openSession();
       
 
    public NouveauClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/NouveauClient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			s.beginTransaction();
			
			Client c1=new Client(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("adresse"),
					request.getParameter("telephone"),request.getParameter("email"));
			
			s.save(c1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
