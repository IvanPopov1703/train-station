package ru.trainstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.data.dto.DiscountDto;
import ru.trainstation.data.entity.Discount;
import ru.trainstation.data.mapper.DiscountMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.DiscountService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/discount")
public class DiscountController extends BaseController {

    private final DiscountService discountService;
    private final DiscountMapper discountMapper;

    public DiscountController(DiscountService discountService, DiscountMapper discountMapper) {
        this.discountService = discountService;
        this.discountMapper = discountMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllDiscounts(Model model) {
        model.addAttribute("discounts", discountMapper.toDiscountDto(discountService.getAllDiscounts()));
        return "admin/discount/listOfDiscount";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getDiscountDetail(Model model, @PathVariable Long id) {
        model.addAttribute("discount", discountMapper.toDiscountDto(discountService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/discount/discountDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createDiscountPage(Model model, @ModelAttribute("discount") DiscountDto discountDto) {
        if (discountDto.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("discount", discountDto);
        return "admin/discount/discountEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createDiscount(Model model,
                             @ModelAttribute("discount") @Valid DiscountDto discountDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("discount", discountDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/discount/add";
        }
        Discount newDiscount = discountService.save(discountMapper.toDiscountFromDiscountDto(discountDto));
        return "redirect:/api/admin/discount/" + String.valueOf(newDiscount.getDiscountId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateDiscountPage(Model model, @PathVariable Long id) {
        DiscountDto discountDto = null;
        try {
            discountDto = discountMapper.toDiscountDto(discountService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("discount", discountDto);
        return "admin/discount/discountEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateDiscount(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("discount") @Valid DiscountDto discountDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("discount", discountDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/discount/" + id + "/edit";
        } else {
            try {
                discountDto.setDiscountId(id);
                discountService.update(discountMapper.toDiscountFromDiscountDto(discountDto));
                return "redirect:/api/admin/discount/" + id;
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/discount/discountEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteDiscountPage(Model model, @PathVariable Long id) {
        DiscountDto discountDto = null;
        try {
            discountDto = discountMapper.toDiscountDto(discountService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("discount", discountDto);
        return "admin/discount/discountDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteDiscount(Model model, @PathVariable Long id) {
        try {
            discountService.deleteById(id);
            return "redirect:/api/admin/discount";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/discount/discountDetails";
        }
    }
}
