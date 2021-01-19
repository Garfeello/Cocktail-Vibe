package pl.application.cocktailVibe.apiIntegration;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

@Component
public class GoogleTranslateAPI_v2 {

    private final String TRANSLATE_SCRIPT_URL =
            "https://script.google.com/macros/s/AKfycbxmTKMvaCh9H6Plwhw2AOWMeKbArfEP4LFubJTzgTvdcRaGAr1B/exec";

    public String translateFromEngToPol(String text){
        String translation = "Couldn't translate";
        try {
            translation = translate("en", "pl", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translation;
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String checkedText = Optional.ofNullable(text).orElse("empty");
        String urlStr = TRANSLATE_SCRIPT_URL + "?q=" + URLEncoder.encode(checkedText, "UTF-8") + "&target=" + langTo +
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
