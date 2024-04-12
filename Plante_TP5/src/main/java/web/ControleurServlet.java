package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.IPlanteDao;
import dao.ITypeDao;
import dao.PlanteDaoImpl;
import dao.TypeDaoImpl;
import metier.entities.Plante;
import metier.entities.Type;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IPlanteDao metier;
	ITypeDao metierType;

	@Override
	public void init() throws ServletException {
		metier = new PlanteDaoImpl();
		metierType = new TypeDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("plantes.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			PlanteModele model = new PlanteModele();
			model.setMotCle(motCle);
			List<Plante> plantes = metier.plantesParMC(motCle);
			model.setPlantes(plantes);
			request.setAttribute("model", model);
			request.getRequestDispatcher("plantes.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			List<Type> types = metierType.getAllTypes();
			TypeModele model = new TypeModele();
			model.setTypes(types);
			request.setAttribute("typeModel", model);
			request.getRequestDispatcher("saisiePlante.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			String couleur = request.getParameter("couleur");
			Long typeId=Long.parseLong(request.getParameter("type"));
			Type type = metierType.getType(typeId);
			Plante p = metier.save(new Plante(nom, couleur,type));
			request.setAttribute("plante", p);
//			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deletePlante(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Plante p = metier.getPlante(id);
			request.setAttribute("plante", p);
			List<Type> types = metierType.getAllTypes();
			TypeModele model= new TypeModele();
			model.setTypes(types);
			request.setAttribute("typeModel", model);
			request.getRequestDispatcher("editerPlante.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String couleur = request.getParameter("couleur");
			Long typeId=Long.parseLong(request.getParameter("type"));
			Plante p = new Plante();
			p.setIdPlante(id);
			p.setNomPlante(nom);
			p.setCouleur(couleur);
			Type type = metierType.getType(typeId);
			p.setType(type);
//			request.setAttribute("plante", p);
//			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			metier.updatePlante(p);
			response.sendRedirect("chercher.do?motCle=");
		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}