package com.parser.services.parsing.LaModa;

import com.parser.model.common.Category;
import com.parser.repository.WardrobeRepository;
import com.parser.services.parsing.CommonParseService;
import com.parser.services.parsing.ItemService;
import com.parser.services.parsing.utils.PathUtil;
import com.parser.services.parsing.utils.RandomUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LamodaCommonParseService {

    @Value("${lamoda.root}")
    String laModaRoot;

    @Autowired
    CommonParseService commonParseService;
    @Autowired
    ItemService itemService;
    @Autowired
    WardrobeRepository wardrobeRepository;

    private void createAndSaveClothesItemsFromPage(Document page) {
        Elements baseElement = page.getElementsByClass("products-list-item");
        for (Element el: baseElement) {
            String itemPath = PathUtil.getFullPathFromHref(el.getElementsByClass("products-list-item__link").attr("href"), laModaRoot);
            itemService.createAndSaveItem(itemPath);
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
                .forEach(x-> x.forEach(this::createAndSaveClothesItemsFromPage));

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
