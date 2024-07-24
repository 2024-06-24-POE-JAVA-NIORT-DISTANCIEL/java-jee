import java.io.IOException;
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/serviette/panier")
public class PanierServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = (Panier) session.getAttribute("panier");
        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }
        request.setAttribute("panier", panier);
        request.getRequestDispatcher("/WEB-INF/views/panier.jsp").forward(request, response);
    }
}