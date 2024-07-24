import java.util.List;
import java.util.ArrayList;

public class Panier {
    private List<Serviette> articles = new ArrayList<>();

    public void ajouterArticle(Serviette serviette) {
        articles.add(serviette);
    }

    public List<Serviette> getArticles() {
            return articles;
    }
}