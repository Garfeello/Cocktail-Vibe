package pl.application.cocktailVibe.apiIntegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

@Component
public class GoogleTranslateAPI {

    private final Logger logger = LoggerFactory.getLogger(GoogleTranslateAPI.class);

    public String prepareAndGetTranslation(String text, String translatedFrom, String translatedTo) {
        String translation = "Couldn't translate";
        try {
            translation = translate(translatedFrom, translatedTo, text);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return translation;
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String checkedText = Optional.ofNullable(text).orElse("empty");
        String translateScriptUrl = "https://script.google.com/macros/s/AKfycbxmTKMvaCh9H6Plwhw2AOWMeKbArfEP4LFubJTzgTvdcRaGAr1B/exec";
        String urlStr = translateScriptUrl + "?q=" + URLEncoder.encode(checkedText, "UTF-8") + "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }
}
