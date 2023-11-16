package news.crawler.service;

import news.crawler.controller.dto.EventDTO;
import news.crawler.domin.SourceConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebService {

    SourceConfig sourceConfig = new SourceConfig();

    public List<EventDTO> getEvents(String Sourceurl) throws IOException {
        List<EventDTO> events = new ArrayList<>();

        Document doc = Jsoup.connect(Sourceurl).get();
        Elements links = doc.select("h2");

        for (Element element : links) {
            String title = element.text();
            String url = element.select("a").attr("href");
            events.add(new EventDTO(title, url));
        }


        return events;
    }

    //TODO basic title and href from sit .ua
    public List<EventDTO> getEvents2(String sourceurl) throws IOException {
        List<EventDTO> events = new ArrayList<>();


        for (int i = 1; i < 2; i++) {
            String sourceWithPage = sourceurl + "?page=" + i;
            Document doc = Jsoup.connect(sourceWithPage).get();
            Elements links = doc.select(".all-news__list a");

            for (Element e : links) {
                String url = e.attr("href");
                String title = e.select("span h3").text();
                if (!title.isEmpty()) {
                    events.add(new EventDTO(title, url));
                }
            }

        }


        return events;
    }

    //TODO param from SourceConfig
    public List<EventDTO> getEvents3(String sourceurl) throws IOException {
        List<EventDTO> events = new ArrayList<>();

        sourceConfig.setSourceUrl(sourceurl);
        sourceConfig.setNextPage("?page=");
        sourceConfig.setTagForHref(".all-news__list a");  // or setTagsToList();  // add priority 1
        sourceConfig.setTagForTitle("span h3");           // add priority 2

        for (int i = 1; ; i++) {

            String sourceWithPage = sourceConfig.getSourceUrl() + sourceConfig.getNextPage() + i;
            Document doc = Jsoup.connect(sourceurl).get();
            Elements links = doc.select(sourceConfig.getTagForHref());  // or getTagsToList()


            for (Element e : links) {
                String url = e.attr("href");
                String title = e.select(sourceConfig.getTagForTitle()).text();
                if (!title.isEmpty()) {
                    events.add(new EventDTO(title, url));
                }
            }
            if (i == 3) break;
        }


        return events;
    }

    //TODO with fullText
    public List<EventDTO> getEvents4(String sourceurl) throws IOException {
        List<EventDTO> events = new ArrayList<>();


        for (int i = 1; i < 2; i++) {
            String sourceWithPage = sourceurl + "?page=" + i;
            Document doc = Jsoup.connect(sourceWithPage).get();
            Elements links = doc.select(".all-news__list a");

            for (Element e : links) {
                String url = e.attr("href");
                String title = e.select("span h3").text();
                if (!title.isEmpty()) {
                    Document page = Jsoup.connect(url).get();
                    Elements elementsWithText = page.select(".content__wrapp p");

                    List<String> fullText = new ArrayList<>();

                    for (Element par : elementsWithText) {
                        fullText.add(par.text());
                    }

                    events.add(new EventDTO(title, url, fullText)); // list while tag <p>
                }
            }


        }


        return events;
    }

}
