package com.vikas.urlshortner.services;

import com.vikas.urlshortner.dtos.InputDto;
import com.vikas.urlshortner.dtos.UrlDto;
import com.vikas.urlshortner.repository.UrlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlRedirectService {

    private final UrlDao dao;

    public UrlDto setRedirectUrl(InputDto dto) {

        if(dao.findByLongUrl(dto.getLongUrl())!=null){
            return dao.findByLongUrl(dto.getLongUrl());
        }
        else{
            UrlDto urlDto = new UrlDto();
            urlDto.setLongUrl(dto.getLongUrl());
            urlDto.setShortUrl(createCustomShortUrl());
            return dao.save(urlDto);
        }

    }

    private String createCustomShortUrl() {
        List<String> allExistingShortUrl = null;
        for (Iterator<UrlDto> itr = dao.findAll().iterator(); itr.hasNext(); ) {
            allExistingShortUrl.add(itr.next().getShortUrl());
        }
        String random = createRandomShortUrl();
        if (allExistingShortUrl == null || !allExistingShortUrl.contains(random)) {
            return random;
        } else {
            createCustomShortUrl();
        }

        return random;
    }

    private String createRandomShortUrl() {
        //currently not
        String allowedValuesString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" /*+ "0123456789"*/ + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int index = (int) (allowedValuesString.length() * Math.random());
            sb.append(allowedValuesString.charAt(index));
        }
        return sb.toString();
    }

    public URI getRedirectUrl(String shortUrlValue) {
        if (!dao.findByShortUrl(shortUrlValue).getLongUrl().contains("https") || !dao.findByShortUrl(shortUrlValue).getLongUrl().contains("http")) {
            return URI.create(String.format("https://%s", dao.findByShortUrl(shortUrlValue).getLongUrl()));
        } else {
            return URI.create(dao.findByShortUrl(shortUrlValue).getLongUrl());
        }
    }

}
