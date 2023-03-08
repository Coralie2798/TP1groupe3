package com.inti.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Session;

import com.inti.model.CompagnieAerienne;
import com.inti.model.Passager;
import com.inti.model.Reservation;
import com.inti.model.Vol;
import com.inti.util.HibernateUtil;

@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Session s=HibernateUtil.getSessionFactory().openSession();
    
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/ReservationVol.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			s.beginTransaction();
			Passager p1=s.get(Passager.class,Integer.parseInt(request.getParameter("idP")));
			Reservation r1=new Reservation(LocalDate.parse(request.getParameter("date")), Integer.parseInt(request.getParameter("numero")));
			Vol vol1=s.get(Vol.class, Integer.parseInt(request.getParameter("idVol")));
			r1.setVol(vol1);
			r1.setPassager(p1);
			
			if (request.getParameter("options-outlined").equals("confirmer")) {
				
				
				s.save(r1);
			}else {
				s.delete(r1);
			}
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
		}
		doGet(request, response);
	}

}
