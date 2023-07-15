package uz.web.bugun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

@SpringBootApplication
public class BugunApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugunApplication.class, args);
    }
}
