package com.parser.services.parsing.LaModa;

import com.commons.model.Item;
import com.commons.model.common.Category;
import com.commons.services.utils.PathUtil;
import com.commons.services.utils.RandomUtils;
import com.parser.repository.WardrobeRepository;
import com.parser.services.parsing.CommonParseService;
import com.parser.services.parsing.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class LamodaCommonParseService {

    @Value("${lamoda.root}")
    String laModaRoot;

    @Autowired
    CommonParseService commonParseService;
    @Autowired
    ItemService itemService;
    @Autowired
    WardrobeRepository wardrobeRepository;

    @Scheduled(cron = "0 0 23 0 0 0")
    public void getItems(){
        log.info("Starting parsing");
        entityCreator("https://www.lamoda.ru/c/15/shoes-women/");
        log.info("Parsing ended successful");
    }

    private void saveClothesItemsFromPage(Document page) {
        Elements baseElement = page.getElementsByClass("products-list-item");
        for (Element el: baseElement) {
            String itemPath = PathUtil.getFullPathFromHref(el.getElementsByClass("products-list-item__link").attr("href"), laModaRoot);
            Item item = wardrobeRepository.getItemByItemPath(itemPath);
            if(item == null){
                itemService.createAndSaveItem(itemPath);
            } else {
                itemService.updateItem(itemPath, item);
            }
        }
    }

    private List<Document> getAllItemPagesFromUrl(String url) {
        List<Document> pages = new ArrayList<>();
        int counter = 1;
        while (counter < 5) {
            RandomUtils.threadSleepRandom();
            Document page = commonParseService.getDocument(url + "?page=" + counter);
            if (page.getElementsByClass("product-catalog-main").isEmpty()) break;
            pages.add(page);
            counter++;
        }
        return pages;
    }

    public void entityCreator(String url) {
        Map<Category, String> paths = getClothesCategoryPaths(url);

        Arrays.stream(Category.values())
                .map(x-> getAllItemPagesFromUrl(paths.get(x)))
                .forEach(x-> x.forEach(this::saveClothesItemsFromPage));

    }

    private Map<Category, String> getClothesCategoryPaths(String url) {
        Map<Category, String> categoryMap = new HashMap<>();
        Document document  = commonParseService.getDocument(url);
        Elements elements = document.getElementsByClass("dt102_bold");
        for (Element el : elements) {
            Category cat = Category.getCategoryByDescription(el.text());
            String href = el.attr("href");
            if (cat != null && !href.isEmpty()) {
                categoryMap.put(cat,  PathUtil.getFullPathFromHref(el.attr("href"), laModaRoot));
            }
        }
        return categoryMap;
    }

}
