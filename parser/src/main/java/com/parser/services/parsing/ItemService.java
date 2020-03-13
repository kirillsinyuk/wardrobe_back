package com.parser.services.parsing;

import com.parser.model.Item;
import com.parser.model.LaModaClothes.Accsessoires;
import com.parser.model.LaModaClothes.Clothes;
import com.parser.model.LaModaClothes.Shoes;
import com.parser.model.common.Category;
import com.parser.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.parser.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.parser.model.common.Price;
import com.parser.model.common.Sex;
import com.parser.repository.WardrobeRepository;
import com.parser.services.parsing.utils.PathUtil;
import com.parser.services.parsing.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    @Autowired
    CommonParseService commonParseService;
    @Autowired
    WardrobeRepository wardrobeRepository;

    public void createAndSaveItem(String fullItemPath){
        Document document  = commonParseService.getDocument(fullItemPath);
        RandomUtils.threadSleepRandom();
        parseItem(document, fullItemPath);
    }

    private Item parseItem(Document document, String fullItemPath){
        List<String> categories = document.select("span.js-breadcrumbs__item-text").stream().map(Element::text).collect(Collectors.toList());
        Category category = Category.getCategoryByDescriptionList(categories);

        Item item = getItemInstance(category);
        if (Objects.nonNull(item)) {
            item.setSex(Sex.getCategoryByDescriptionList(categories));
            fillItem(document, item, fullItemPath);
            fillSubItemFields(item, categories);
            wardrobeRepository.saveAndFlush(item);
            log.info("Created entity {}", item.getDescription());
        }
        return item;
    }

    private Item getItemInstance(Category category){
        if(Objects.isNull(category)) return null;
        switch (category) {
            case SHOES:
                return new Shoes();
            case ACCS:
                return new Accsessoires();
            case CLOTHES:
                return new Clothes();
            default:
                return null;
        }
    }

    private void fillSubItemFields(Item item, List<String> categories) {
        if (item instanceof Shoes) {
            ((Shoes) item).setShoesSubCategory(ShoesSubCategory.getSubCategoryByDescriptionList(categories));
        } else if (item instanceof Clothes){
            ((Clothes) item).setClothesSubCategory(ClothesSubCategory.getSubCategoryByDescriptionList(categories));
        } else if(item instanceof Accsessoires) {

        }
    }

    private Item fillItem(Document document, Item item, String fullItemPath){

        item.setItemPath(fullItemPath);
        item.setImgPath(PathUtil.getFullPathFromHref(document.selectFirst("img.gallery-image").attr("src"), "https:"));
        item.setDescription(document.selectFirst("img.gallery-image").attr("alt"));
        item.setBrand(document.selectFirst("a.ii-product__brand-text").attr("title"));
        try {
            item.setPrice(new Price(Long.parseLong(document.selectFirst("meta[itemprop=price]").attr("content"))));
        } catch (NumberFormatException | NullPointerException e){
            log.error("Error during parse price for item {} - {}", item.getDescription(), fullItemPath);
        }
        return item;
    }
}
