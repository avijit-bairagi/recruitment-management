package com.akash.recruitment.config;

import com.akash.recruitment.excepion.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public String handleNoSuchElementException(Model model, HttpServletRequest request, CustomException ex) {
        model.addAttribute("msg", ex.getMessage());
        printException(request, ex);
        return "error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(Model model, HttpServletRequest request, NoSuchElementException ex) {
        printException(request, ex);
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Model model, HttpServletRequest request, SQLException ex) {
        printException(request, ex);
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Model model, HttpServletRequest request, NullPointerException ex) {
        printException(request, ex);
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Model model, HttpServletRequest request, Exception ex) {
        printException(request, ex);
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }

    public void printException(HttpServletRequest request, Exception ex) {
        LOGGER.error(" - - - - - - - Start error - - - - - - - ");
        LOGGER.error(request.getRequestURL(), ex);
        LOGGER.error(" - - - - - - - End error - - - - - - - ");
    }
}