import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet("/serviette/home")
    public class HomeServlet extends HttpServlet {

    //Méthode pour récuperer la liste des serviette disponibles
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throw ServletException, IOException {
        // Remplacer par la récupération réelle des données
        List<Serviette> serviettes = /* Récupération des données */;
        request.setAttribute("serviettes", serviettes);
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}