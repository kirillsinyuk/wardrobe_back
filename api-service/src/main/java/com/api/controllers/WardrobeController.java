package com.api.controllers;


import com.api.repository.WardrobeRepository;
import com.api.services.api.ClothesService;
import com.api.services.api.ShoesService;
import com.commons.model.dto.clothes.ClothesDto;
import com.commons.model.dto.clothes.ClothesFilter;
import com.commons.model.dto.shoes.ShoesDto;
import com.commons.model.dto.shoes.ShoesFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WardrobeController {

    @Autowired
    WardrobeRepository wardrobeRepository;
    @Autowired
    ShoesService shoesService;
    @Autowired
    ClothesService clothesService;

    @GetMapping("/items/shoes")
    public Page<ShoesDto> getShoesSpec(@RequestBody ShoesFilter filter,
                                       Pageable pageable) {
        return shoesService.findFiltered(filter, pageable);
    }

    @GetMapping("/items/clothes")
    public Page<ClothesDto> getClothes (@RequestBody ClothesFilter filter,
                                        Pageable pageable) {
        return clothesService.findFiltered(filter, pageable);
    }
    /*

    запросы:
        /items/shoes
        /items/clothes
        /items/accs

    параметры запроса(paging):
        page = номер страницы
        size = количество элементов на странице
        sort = сортировка id, price, desc
        DESC/ASC - по убыванию/возрастанию

    тело запроса(фильтр)
        {
          "id": 10, //ИД (число)
          "min": 10, //минимальная цена(число)
          "max": 10, //максимальная цена(число)
          "sex": "MAN", //пол(MAN/WOMAN)
          "brand": [                    //бренды
              "название бренда",
              "название бренда"
            ]
        }
     */
}