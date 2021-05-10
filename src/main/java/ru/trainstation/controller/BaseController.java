package ru.trainstation.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

abstract public class BaseController {

    protected void addValidationMessage(RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            redirectAttributes.addFlashAttribute("validation." + error.getField(), error.getDefaultMessage());
        }
    }
}