package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.data.dto.TypeRailwayCarriageDto;
import ru.trainstation.data.entity.TypeRailwayCarriage;
import ru.trainstation.data.mapper.TypeRailwayCarriageMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.TypeRailwayCarriageService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/typeRailwayCarriage")
public class TypeRailwayCarriageController extends BaseController {

    private final TypeRailwayCarriageService typeRailwayCarriageService;
    private final TypeRailwayCarriageMapper typeRailwayCarriageMapper;

    public TypeRailwayCarriageController(
            TypeRailwayCarriageService typeRailwayCarriageService,
            TypeRailwayCarriageMapper typeRailwayCarriageMapper
    ) {
        this.typeRailwayCarriageService = typeRailwayCarriageService;
        this.typeRailwayCarriageMapper = typeRailwayCarriageMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllCities(Model model) {
        model.addAttribute(
                "cities",
                typeRailwayCarriageMapper.toTypeRailwayCarriageDto(
                        typeRailwayCarriageService.getAllTypeRailwayCarriages()
                )
        );
        return "admin/typeRailwayCarriage/listOfTypeRailwayCarriage";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getTypeRailwayCarriageDetail(Model model, @PathVariable Long id) {
        model.addAttribute(
                "typeRailwayCarriage",
                typeRailwayCarriageMapper.toTypeRailwayCarriageDto(typeRailwayCarriageService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/typeRailwayCarriage/typeRailwayCarriageDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createTypeRailwayCarriagePage(
            Model model,
            @ModelAttribute("typeRailwayCarriage") TypeRailwayCarriageDto typeRailwayCarriageDto) {
        if (typeRailwayCarriageDto.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("typeRailwayCarriage", typeRailwayCarriageDto);
        return "admin/typeRailwayCarriage/typeRailwayCarriageEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createTypeRailwayCarriage(Model model,
                             @ModelAttribute("typeRailwayCarriage") @Valid TypeRailwayCarriageDto typeRailwayCarriageDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("typeRailwayCarriage", typeRailwayCarriageDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/typeRailwayCarriage/add";
        }
        TypeRailwayCarriage newTypeRailwayCarriage = typeRailwayCarriageService.save(
                typeRailwayCarriageMapper.toTypeRailwayCarriageFromTypeRailwayCarriageDto(typeRailwayCarriageDto)
        );
        return "redirect:/api/admin/typeRailwayCarriage/" + newTypeRailwayCarriage.getTypeRailwayCarriageId();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateTypeRailwayCarriagePage(Model model, @PathVariable Long id) {
        TypeRailwayCarriageDto typeRailwayCarriageDto = null;
        try {
            typeRailwayCarriageDto = typeRailwayCarriageMapper.toTypeRailwayCarriageDto(
                    typeRailwayCarriageService.findById(id)
            );
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("typeRailwayCarriage", typeRailwayCarriageDto);
        return "admin/typeRailwayCarriage/typeRailwayCarriageEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateTypeRailwayCarriage(
            Model model,
            @PathVariable Long id,
            @ModelAttribute("typeRailwayCarriage") @Valid TypeRailwayCarriageDto typeRailwayCarriageDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("typeRailwayCarriage", typeRailwayCarriageDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/typeRailwayCarriage/" + id + "/edit";
        } else {
            try {
                typeRailwayCarriageDto.setTypeRailwayCarriageId(id);
                typeRailwayCarriageService.update(
                        typeRailwayCarriageMapper.toTypeRailwayCarriageFromTypeRailwayCarriageDto(typeRailwayCarriageDto)
                );
                return "redirect:/api/admin/typeRailwayCarriage/" + id;
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/typeRailwayCarriage/typeRailwayCarriageEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteTypeRailwayCarriagePage(Model model, @PathVariable Long id) {
        TypeRailwayCarriageDto typeRailwayCarriageDto = null;
        try {
            typeRailwayCarriageDto = typeRailwayCarriageMapper.toTypeRailwayCarriageDto(
                    typeRailwayCarriageService.findById(id)
            );
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("typeRailwayCarriage", typeRailwayCarriageDto);
        return "admin/typeRailwayCarriage/typeRailwayCarriageDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteTypeRailwayCarriage(Model model, @PathVariable Long id) {
        try {
            typeRailwayCarriageService.deleteById(id);
            return "redirect:/api/admin/typeRailwayCarriage";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/typeRailwayCarriage/typeRailwayCarriageDetails";
        }
    }
}
