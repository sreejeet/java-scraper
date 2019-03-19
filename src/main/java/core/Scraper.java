package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Attribute;
import java.util.ArrayList;

import java.util.List;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import core.Review;


public class Scraper {
    
    public ArrayList<Review> getReviews(int start, int limit) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Review> reviews = new ArrayList<Review>();
        
        // Link: https://www.google.com/maps/contrib/117975225695174451393/reviews/@28.8702294,77.1291154,10z
        // Link: https://www.google.com/maps/preview/reviews?authuser=0&hl=en&gl=in&pb=!1s0x390d047ab4403917:0xa15c7ff58eabbf58!2i8!3i10!4e6!5m2!1subWQXNivEOzCz7sP6ZK5oAc!7e81!7m5!2b1!3b1!5b1!6b1!7b1
        String fid = "0x390d047ab4403917:0xa15c7ff58eabbf58";
        String index = "0";
        String url
            = "https://www.google.com/maps/preview/reviews?authuser=0&hl=en&gl=in&pb=!1s"
            + fid
            + "!2i"
            + index
            + "!3i10!4e6!5m2!1subWQXNivEOzCz7sP6ZK5oAc!7e81!7m5!2b1!3b1!5b1!6b1!7b1";
        print("Fetching %s...", url);

        String docstr = Jsoup.connect(url).ignoreContentType(true).get().wholeText().substring(4);

        Long now = Instant.now().toEpochMilli();
        
        Date date = new Date(now);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String now_formatted = format.format(date);

        JsonNode root = mapper.readTree(docstr);

        for (int id = start; id < start+limit; id++){
            Review review = new Review();
            try{
                review.set_id( root.path(0).path(id).path(0).path(0).asText() );
                review.setAuthor( root.path(0).path(id).path(0).path(1).asText() );
                review.setAuthor( root.path(0).path(id).path(0).path(1).asText() );
                review.setBody( root.path(0).path(id).path(3).asText() );
                review.setRating( root.path(0).path(id).path(4).asText() );
                review.setReviews( root.path(0).path(id).path(12).path(1).path(1).asText() );
                review.setPub_ts( Long.parseLong(root.path(0).path(id).path(27).asText()) );
                review.setReviews( root.path(0).path(id).path(12).path(1).path(1).asText() );
                review.setScrape_ts( now );
                review.setDate( now_formatted );

                reviews.add(review);
            }
            catch (Exception e) { e.printStackTrace();}
        }

        return reviews;
    }

    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }


    public static void main(String[] args) throws IOException {
        Scraper scraper = new Scraper();
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Review> reviews = scraper.getReviews(0, 5);
        
        for ( Review review : reviews ) {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(review);
            print(jsonInString);
            System.out.println("---------------------------------------------------------");
        }

    }

}