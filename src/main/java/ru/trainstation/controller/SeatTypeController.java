package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.data.dto.SeatTypeDto;
import ru.trainstation.data.entity.SeatType;
import ru.trainstation.data.mapper.SeatTypeMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.SeatTypeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/seatType")
public class SeatTypeController extends BaseController {

    private final SeatTypeService seatTypeService;
    private final SeatTypeMapper seatTypeMapper;

    public SeatTypeController(SeatTypeService seatTypeService, SeatTypeMapper seatTypeMapper) {
        this.seatTypeService = seatTypeService;
        this.seatTypeMapper = seatTypeMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllCities(Model model) {
        model.addAttribute("cities", seatTypeMapper.toSeatTypeDto(seatTypeService.getAllTypesOfSeats()));
        return "admin/seatType/listOfSeatType";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getSeatTypeDetail(Model model, @PathVariable Long id) {
        model.addAttribute("seatType", seatTypeMapper.toSeatTypeDto(seatTypeService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/seatType/seatTypeDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createSeatTypePage(Model model, @ModelAttribute("seatType") SeatTypeDto seatTypeDto) {
        if (seatTypeDto.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("seatType", seatTypeDto);
        return "admin/seatType/seatTypeEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createSeatType(Model model,
                             @ModelAttribute("seatType") @Valid SeatTypeDto seatTypeDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("seatType", seatTypeDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/seatType/add";
        }
        SeatType newSeatType = seatTypeService.save(seatTypeMapper.toSeatTypeFromSeatTypeDto(seatTypeDto));
        return "redirect:/api/admin/seatType/" + String.valueOf(newSeatType.getSeatTypeId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateSeatTypePage(Model model, @PathVariable Long id) {
        SeatTypeDto seatTypeDto = null;
        try {
            seatTypeDto = seatTypeMapper.toSeatTypeDto(seatTypeService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("seatType", seatTypeDto);
        return "admin/seatType/seatTypeEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateSeatType(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("seatType") @Valid SeatTypeDto seatTypeDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("seatType", seatTypeDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/seatType/" + id + "/edit";
        } else {
            try {
                seatTypeDto.setSeatTypeId(id);
                seatTypeService.update(seatTypeMapper.toSeatTypeFromSeatTypeDto(seatTypeDto));
                return "redirect:/api/admin/seatType/" + id;
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/seatType/seatTypeEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteSeatTypePage(Model model, @PathVariable Long id) {
        SeatTypeDto seatTypeDto = null;
        try {
            seatTypeDto = seatTypeMapper.toSeatTypeDto(seatTypeService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("seatType", seatTypeDto);
        return "admin/seatType/seatTypeDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteSeatType(Model model, @PathVariable Long id) {
        try {
            seatTypeService.deleteById(id);
            return "redirect:/api/admin/seatType";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/seatType/seatTypeDetails";
        }
    }
}
