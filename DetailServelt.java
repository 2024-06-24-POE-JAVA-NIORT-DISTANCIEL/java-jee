import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/serviette/detail")
public class DetailServelt extends HttpServlet {
    protected void  doGet(HttpServlet request, HttpServletResponse response)
        throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            //Remplacer par la recherche réelle de l'article par ID
            Serviette serviette = /*Recherche de serviette par ID*/
            if  (serviette != null) {
                request setAttribute ("serviette", serviette);
                request.getRequestDsipatcher("/WEB-INF/views/detail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServleResponse.SC_NOT_FOUND, "Article non trouvé :/");
            }
        }catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID invalide :(" );

        }
    }
