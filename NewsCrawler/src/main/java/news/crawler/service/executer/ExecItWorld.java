package news.crawler.service.executer;

import lombok.extern.slf4j.Slf4j;
import news.crawler.common.DateTimeUtils;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domin.SourceConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.Jsoup.connect;

@Slf4j
public class ExecItWorld implements Execute {

    @Override
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();
        Document document = null;

        try {
            document = connect(config.getRootUrl() + config.getNewsSuffix()).timeout(20000*10).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {

//            System.out.println("D");
            Elements links = document.getElementsByClass("news-time");
            for (Element element : links) {

                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();

                // read news page and extract date-time and text
                Document news = connect(newsUrl).get();

                String newsDate = null;
               try {
                   // https://www.it-world.ru/it-news/reviews/197855.html  нет даты
                   newsDate = news.select(".separator-line span").first().text(); //TODO

               }catch (NullPointerException e){
                   log.error("title: {} newsUrl: {}",title,newsUrl);
                   System.out.println("s");
               }

                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, time);

                events.add(new EventDTO(config, title, newsUrl, dateTime, null, text));

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return events;
    }
}


