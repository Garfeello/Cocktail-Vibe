package pl.application.cocktailVibe.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

@Controller
public class MyErrorController implements ErrorController {


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data integrity violation")
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public void conflict() {
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error/errorView";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}