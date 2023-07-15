package uz.web.bugun.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/wiki")
    public ResponseEntity<String> history(){
        List<String> months = new ArrayList<>();
        months.add("yanvar");
        months.add("fevral");
        months.add("mart");
        months.add("aprel");
        months.add("may");
        months.add("iyun");
        months.add("iyul");
        months.add("avgust");
        months.add("sentyabr");
        months.add("oktyabr");
        months.add("noyabr");
        months.add("dekabr");

        String url = "https://uz.wikipedia.org/wiki/"+new Date().getDate()+"-"+months.get(new Date().getMonth()-1);
        try {
            Document document = Jsoup.connect(url).get();
            Elements paragraphs = document.select("p");
            for (Element paragraph : paragraphs) {
                return ResponseEntity.ok(paragraph.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/kun")
    public ResponseEntity<String> news(){
        String url = "https://kun.uz/uz";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements divElements = doc.getElementsByClass("mb-25");

            for (Element divElement : divElements) {
                return ResponseEntity.ok(formatting(divElement.text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("");
    }

    @GetMapping("/weather")
    public ResponseEntity<String> weather(){
        String url = "https://www.google.com/search?q=weather";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements divElements = doc.getElementsByClass("MjjYud");

            for (Element divElement : divElements) {
                return ResponseEntity.ok(formatting(divElement.text()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("");
    }

    public String formatting(String input){
        String regex = "\\d\\d:\\d\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int startIndex = 0;
        int endIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            if(startIndex>5) endIndex++;
            if(endIndex == 2) break;
        }
        String result = input.substring(0, startIndex);
        return result;
    }
}
