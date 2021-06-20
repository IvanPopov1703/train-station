package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.data.dto.RailwayStationDto;
import ru.trainstation.data.entity.RailwayStation;
import ru.trainstation.data.mapper.CityMapper;
import ru.trainstation.data.mapper.RailwayStationMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.CityService;
import ru.trainstation.service.RailwayStationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/railwayStation")
public class RailwayStationControllerTest extends BaseController {

    private final RailwayStationService railwayStationService;
    private final RailwayStationMapper railwayStationMapper;
    private final CityService cityService;
    private final CityMapper cityMapper;

    public RailwayStationControllerTest(RailwayStationService railwayStationService,
                                        RailwayStationMapper railwayStationMapper, CityService cityService, CityMapper cityMapper) {
        this.railwayStationService = railwayStationService;
        this.railwayStationMapper = railwayStationMapper;
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllRailwayStations(Model model) {
        model.addAttribute(
                "stations",
                railwayStationMapper.toRailwayStationDto(railwayStationService.getAllRailwayStations()));
        model.addAttribute(
                "cities",
                cityMapper.toCityDto(cityService.getAllCities()));
        return "admin/railwayStation/listOfRailwayStation";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getRailwayStationDetail(Model model, @PathVariable Long id) {
        model.addAttribute(
                "station",
                railwayStationMapper.toRailwayStationDto(railwayStationService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/railwayStation/railwayStationDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createRailwayStationPage(
            Model model,
            @ModelAttribute("station") RailwayStation railwayStation
    ) {
        if (railwayStation.getStationName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("station", railwayStation);
        model.addAttribute(
                "cities", cityService.getAllCities());
        return "admin/railwayStation/railwayStationEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createRailwayStation(Model model,
                             @ModelAttribute("station") @Valid RailwayStation railwayStation,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("station", railwayStation);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/railwayStation/add";
        }
        RailwayStation newRailwayStation = railwayStationService.save(railwayStation);
        return "redirect:/api/admin/railwayStation/" + String.valueOf(newRailwayStation.getRailwayStationId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateRailwayStationPage(Model model, @PathVariable Long id) {
        RailwayStationDto railwayStationDto = null;
        try {
            railwayStationDto = railwayStationMapper.toRailwayStationDto(railwayStationService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("station", railwayStationDto);
        model.addAttribute(
                "cities",
                cityMapper.toCityDto(cityService.getAllCities()));
        return "admin/railwayStation/railwayStationEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateRailwayStation(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("station") @Valid RailwayStationDto railwayStationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("station", railwayStationDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/railwayStation/" + id + "/edit";
        } else {
            try {
                RailwayStation newRailwayStation = railwayStationMapper.toRailwayStationFromDto(railwayStationDto);
                RailwayStation railwayStation = railwayStationService.findById(id);
                newRailwayStation.setFromList(railwayStation.getFromList());
                newRailwayStation.setToList(railwayStation.getToList());
                newRailwayStation.setTrainStopsList(railwayStation.getTrainStopsList());
                railwayStationService.update(railwayStationMapper.toRailwayStationFromDto(railwayStationDto));
                return "redirect:/api/admin/railwayStation/" + id;
            } catch (NotFountException ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/railwayStation/railwayStationEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteRailwayStationPage(Model model, @PathVariable Long id) {
        RailwayStationDto railwayStationDto = null;
        try {
            railwayStationDto = railwayStationMapper.toRailwayStationDto(railwayStationService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("railwayStation", railwayStationDto);
        return "admin/railwayStation/railwayStationDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteRailwayStation(Model model, @PathVariable Long id) {
        try {
            railwayStationService.deleteById(id);
            return "redirect:/api/admin/railwayStation";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/railwayStation/railwayStationDetails";
        }
    }
}