package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.data.dto.CityDto;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.mapper.CityMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.CityService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/city")
public class CityController extends BaseController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllCities(Model model) {
        model.addAttribute("cities", cityMapper.toCityDto(cityService.getAllCities()));
        return "admin/city/listOfCity";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getCityDetail(Model model, @PathVariable Long id) {
        model.addAttribute("city", cityMapper.toCityDto(cityService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/city/cityDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createCityPage(Model model, @ModelAttribute("city") CityDto cityDto) {
        if (cityDto.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("city", cityDto);
        return "admin/city/cityEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createCity(Model model,
                             @ModelAttribute("city") @Valid CityDto cityDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("city", cityDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/city/add";
        }
        City newCity = cityService.save(cityMapper.toCityFromCityDto(cityDto));
        return "redirect:/api/admin/city/" + String.valueOf(newCity.getCityId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateCityPage(Model model, @PathVariable Long id) {
        CityDto cityDto = null;
        try {
            cityDto = cityMapper.toCityDto(cityService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("city", cityDto);
        return "admin/city/cityEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateCity(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("city") @Valid CityDto cityDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("city", cityDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/city/" + id + "/edit";
        } else {
            try {
                cityDto.setCityId(id);
                cityService.update(cityMapper.toCityFromCityDto(cityDto));
                return "redirect:/api/admin/city/" + id;
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/city/cityEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteCityPage(Model model, @PathVariable Long id) {
        CityDto cityDto = null;
        try {
            cityDto = cityMapper.toCityDto(cityService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("city", cityDto);
        return "admin/city/cityDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteCity(Model model, @PathVariable Long id) {
        try {
            cityService.deleteById(id);
            return "redirect:/api/admin/city";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/city/cityDetails";
        }
    }
}
