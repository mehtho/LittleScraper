import util.DBConnector;
import util.Scraper;
import objects.Product;

import java.util.List;

public class Main {
    /**
     * Scrapes a website and stores information in the provided database
     * @param args URLtoScrape, connString, username, password
     */
    public static void main(String[] args) {
            Scraper s = new Scraper();
            List<Product> products = s.scrape(args[0]);

            DBConnector db = new DBConnector(args[1], args[2], args[3]);

            for(Product p : products){
                db.insertProduct(p);
            }
    }
}
