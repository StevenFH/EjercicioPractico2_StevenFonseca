package EjercicioPractico2_StevenFonseca.controller;

import EjercicioPractico2_StevenFonseca.domain.Rol;
import EjercicioPractico2_StevenFonseca.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var roles = rolService.listarRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("totalRoles", roles.size());
        return "roles/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Rol rol, RedirectAttributes redirectAttributes) {
        rolService.crearRol(rol);
        redirectAttributes.addFlashAttribute("todoOk", "Rol creado correctamente");
        return "redirect:/roles/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Rol> rolOpt = rolService.obtenerRol(id);
        if (rolOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Rol no encontrado");
            return "redirect:/roles/listado";
        }
        model.addAttribute("rol", rolOpt.get());
        return "roles/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        rolService.eliminarRol(id);
        redirectAttributes.addFlashAttribute("todoOk", "Rol eliminado correctamente");
        return "redirect:/roles/listado";
    }
}
