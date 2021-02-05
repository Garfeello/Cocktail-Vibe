package pl.application.cocktailVibe.apiIntegrationTEST;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.application.cocktailVibe.apiIntegration.GoogleTranslateAPI;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoogleTranslationApiTEST {

    @Autowired
    private GoogleTranslateAPI googleTranslateAPI;

    @Test
    public void givenTranslationWhenTranslateThenCorrectTranslation(){
        //given
        String translateFrom = "en";
        String translateTo = "pl";
        String translationText = "great application";
        //when
        String translation = googleTranslateAPI.prepareAndGetTranslation(translationText, translateFrom, translateTo);
        //then
        assertNotNull(translation);
        assertNotEquals(translationText, translation);
        assertEquals(translation, "świetna aplikacja");
    }

    @Test
    public void givenTranslationWhenTranslateThenExpectGoogleAPIStringError(){
        //given
        String translateFrom = "xxxxx";
        String translateTo = "pl";
        String translationText = "great application";
        //when
        String translation = googleTranslateAPI.prepareAndGetTranslation(translationText, translateFrom, translateTo);
        //then
        assertNotNull(translation);
        assertEquals(translation, "<!DOCTYPE html><html><head><link rel=\"shortcut icon\" href=\"//ssl.gstatic.com/docs/script/images/favicon.ico\"><title>Błąd</title><style type=\"text/css\">" +
                "body {background-color: #fff; margin: 0; padding: 0;}.errorMessage {font-family: Arial,sans-serif; font-size: 12pt; font-weight: bold; line-height: 150%; padding-top: 25px;}</style></head>" +
                "<body style=\"margin:20px\"><div><img alt=\"Google Apps Script\" src=\"//ssl.gstatic.com/docs/script/images/logo.png\"></div><div style=\"text-align:center;font-family:monospace;margin:50px auto 0;max-width:600px\">" +
                "Exception: Nieprawidłowy argument: source (wiersz 28, plik „Kod”)</div></body></html>");
    }


}
