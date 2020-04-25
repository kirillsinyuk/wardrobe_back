package com.parser.services.parsing;

import com.commons.model.Item;
import com.commons.model.LaModaClothes.Accsessoires;
import com.commons.model.LaModaClothes.Clothes;
import com.commons.model.LaModaClothes.Shoes;
import com.commons.model.common.Category;
import com.commons.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.commons.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.commons.model.common.Price;
import com.commons.model.common.Sex;
import com.commons.services.utils.PathUtil;
import com.commons.services.utils.RandomUtils;
import com.parser.repository.WardrobeRepository;
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
    private CommonParseService commonParseService;
    @Autowired
    private WardrobeRepository wardrobeRepository;

    public void createAndSaveItem(String fullItemPath){
        Document document  = commonParseService.getDocument(fullItemPath);
        RandomUtils.threadSleepRandom();
        Item item = parseItem(document, fullItemPath);
        if (Objects.nonNull(item)) {
            wardrobeRepository.save(item);
            log.info("Created entity {}", item.getDescription());
        } else {
            log.warn("Entity not created:\n" + fullItemPath);
        }
    }

    public void updateItem(String fullItemPath, Item item){
        Document document  = commonParseService.getDocument(fullItemPath);
        RandomUtils.threadSleepRandom();
        parseItem(document, fullItemPath);
        Item newItem = parseItem(document, fullItemPath);
        if (Objects.nonNull(newItem)) {
            newItem.setId(item.getId());
            wardrobeRepository.save(item);
            log.info("Updated entity {}", item.getDescription());
        } else {
            log.warn("Entity not updated:\n" + fullItemPath);
        }
    }

    private Item parseItem(Document document, String fullItemPath){
        List<String> categories = document.select("span.js-breadcrumbs__item-text").stream().map(Element::text).collect(Collectors.toList());
        Category category = Category.getCategoryByDescriptionList(categories);

        Item item = getItemInstance(category);
        if (Objects.nonNull(item)) {
            item.setSex(Sex.getCategoryByDescriptionList(categories));
            fillItem(document, item, fullItemPath);
            fillSubItemFields(item, categories);
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
            //TODO add accesoires
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
