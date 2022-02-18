package util;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import objects.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    /**
     * Scrapes a list of Product objects from a given URL
     * @param url URL of site to Scrape
     * @return  List of Products from the scraped site
     */
    public List<Product> scrape(String url){
        ArrayList<Product> scrapedProducts = new ArrayList<>();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        try {
            final HtmlPage page = webClient.getPage(url);
            List<HtmlElement> chunks = page.getByXPath("//li[@class='item product product-item']");

            for(HtmlElement he : chunks){
                scrapedProducts.add(handleChunk(he));
            }

            return scrapedProducts;
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
        finally {
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();
        }
        return null;
    }

    /**
     * Converts an HTML element containing individual product information into a Product object
     * @param he One of the repeated HTML Elements containing each product's information
     * @return The HTML Element's product information in a Product
     */
    private Product handleChunk(HtmlElement he){
        HtmlAnchor linkAnchor = he.getFirstByXPath(".//a[@class='product photo product-item-photo']");
        String href = linkAnchor.getHrefAttribute();

        HtmlAnchor nameAnchor = he.getFirstByXPath(".//a[@class='product-item-link']");
        String name = nameAnchor.getTextContent();

        DomNode modelP = he.getFirstByXPath(".//p[@class='product-model-number']");
        modelP = modelP.getFirstChild();
        String model = modelP.getTextContent();

        DomNode priceP = he.getFirstByXPath(".//span[@class='price']");
        String price = priceP.getTextContent();

        String oldPrice=price;
        DomNode oldPriceP = he.getFirstByXPath(".//span[@class='old-price']");
        if(oldPriceP!=null){
            oldPriceP = oldPriceP.getFirstByXPath(".//span[@class='price']");
            oldPrice = oldPriceP.getTextContent();
        }

        return new Product(name,model, new BigDecimal(price.replaceAll("[^\\d.]", "")),
                new BigDecimal(oldPrice.replaceAll("[^\\d.]", "")),href);
    }
}
