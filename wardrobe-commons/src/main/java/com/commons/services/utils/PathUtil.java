package com.commons.services.utils;

import java.util.List;
import java.util.stream.Collectors;

public class PathUtil {

    private PathUtil(){}

    public static List<String> getFullPathFromHrefs(List<String> hrefs, String pref){
        return hrefs.stream()
                .map(x -> pref + x)
                .collect(Collectors.toList());
    }

    public static String getFullPathFromHref(String href, String pref){
        return pref + href;
    }
}
