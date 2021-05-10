package ru.trainstation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.trainstation.TrainStationApplication;
import ru.trainstation.data.dto.RoleDto;
import ru.trainstation.data.entity.Role;
import ru.trainstation.data.mapper.RoleMapper;
import ru.trainstation.exception.NotFountException;
import ru.trainstation.service.RoleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/role")
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleMapper.toRoleDto(roleService.getAllRoles()));
        return "admin/role/listOfRoles";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getRoleDetail(Model model, @PathVariable Long id) {
        model.addAttribute("role", roleMapper.toRoleDto(roleService.findById(id)));
        model.addAttribute("allowDelete", false);
        return "admin/role/roleDetails";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String createRolePage(Model model, @ModelAttribute("role") RoleDto roleDto) {
        if (roleDto.getName() != null) {
            model.addAttribute("err", "err");
        }
        model.addAttribute("add", true);
        model.addAttribute("role", roleDto);
        return "admin/role/roleEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String createRole(Model model,
                             @ModelAttribute("role") @Valid RoleDto roleDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("role", roleDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", true);
            return "redirect:/api/admin/role/add";
        }
        Role newRole = roleService.save(roleMapper.toRoleFromRoleDto(roleDto));
        return "redirect:/api/admin/role/" + String.valueOf(newRole.getRoleId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String updateRolePage(Model model, @PathVariable Long id) {
        RoleDto roleDto = null;
        try {
            roleDto = roleMapper.toRoleDto(roleService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("role", roleDto);
        return "admin/role/roleEdit";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String updateRole(Model model,
                             @PathVariable Long id,
                             @ModelAttribute("role") @Valid RoleDto roleDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("role", roleDto);
            addValidationMessage(redirectAttributes, bindingResult);
            model.addAttribute("add", false);
            return "redirect:/api/admin/role/" + id + "/edit";
        } else {
            try {
                roleDto.setRoleId(id);
                roleService.update(roleMapper.toRoleFromRoleDto(roleDto));
                return "redirect:/api/admin/role/" + id;
            } catch (Exception ex) {
                model.addAttribute("errorMessage", ex.getMessage());
                model.addAttribute("add", false);
                return "admin/role/roleEdit";
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteRolePage(Model model, @PathVariable Long id) {
        RoleDto roleDto = null;
        try {
            roleDto = roleMapper.toRoleDto(roleService.findById(id));
        } catch (NotFountException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("role", roleDto);
        return "admin/role/roleDetails";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/delete")
    public String deleteRole(Model model, @PathVariable Long id) {
        try {
            roleService.deleteById(id);
            return "redirect:/api/admin/role";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "admin/role/roleDetails";
        }
    }
}
