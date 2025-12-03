package EjercicioPractico2_StevenFonseca.controller;

import EjercicioPractico2_StevenFonseca.domain.Usuario;
import EjercicioPractico2_StevenFonseca.service.UsuarioService;
import EjercicioPractico2_StevenFonseca.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "usuarios/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario,
                          @RequestParam String rolNombre,
                          RedirectAttributes redirectAttributes) {
        usuarioService.crearUsuario(usuario, rolNombre);
        redirectAttributes.addFlashAttribute("todoOk", "Usuario creado correctamente");
        return "redirect:/usuarios/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuario(id);
        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios/listado";
        }
        model.addAttribute("usuario", usuarioOpt.get());
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.eliminarUsuario(id);
        redirectAttributes.addFlashAttribute("todoOk", "Usuario eliminado correctamente");
        return "redirect:/usuarios/listado";
    }
}