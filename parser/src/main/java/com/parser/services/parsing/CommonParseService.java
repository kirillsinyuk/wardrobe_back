package com.parser.services.parsing;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonParseService {

    @Value("${user.agent}")
    private String userAgent;
    @Value("${user.referrence}")
    private String referrence;

    public Document getDocument(String url) {
        try {
            log.info("getting page URL: {}", url);
            return Jsoup.connect(url)
                    .userAgent(userAgent)
                    .referrer(referrence)
                    .get();
        } catch (IOException e) {
            log.error("Невозможно получить документ", e);
            return null;
        }
    }

    public List<Document> getDocument(List<String> url) {
        return url.stream()
                .map(this::getDocument)
                .collect(Collectors.toList());
    }

    public Document getDocument(String url, String userAgent, String referrence) {
        try {
            return Jsoup.connect(url)
                    .userAgent(userAgent)
                    .referrer(referrence)
                    .get();
        } catch (IOException e) {
            log.error("Невозможно получить документ", e);
            return null;
        }
    }
}
