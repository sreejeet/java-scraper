package core;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Attribute;
import java.util.List;

import java.io.IOException;

public class Scraper {
    public static void main(String[] args) throws IOException {
        String start = "0";
        // Max limit is 200
        String limit = "5";
        String url = ("https://www.google.com/async/ugc_rvs?vet=12ahUKEwjjhISv0IvhAhVLXCsKHTHjCqwQl_YDegQIARA1..i&ei=doaPXLicHc749QPU3JeQCA&yv=3&async=mid:/m/0126b88c,start:" + start + ",num:" + limit + ",ro:false,_pms:s,_fmt:pc");
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements reviews = doc.select(".O48dm");

        print("\nReviews: (%d)", reviews.size());
        for (Element review : reviews) {
            String txt = review.html();
            
            if (txt.contains("...<a class=\"HEgglb\">More<span")) {
                print("Skipping long!!");
                continue;
            } else {
                print("Short enough!!");
                print("\n====================================================", review.html());
                print("\nReview: %s", review.html());
                print("\n====================================================\n\n", review.html());
            }

            List<Attribute> attr = review.attributes().asList();
            for (Attribute a : attr) {
                print("key: %s, val: %s", a.getKey(), a.getValue());
            }            
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

}