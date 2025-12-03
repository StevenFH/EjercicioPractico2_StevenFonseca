package EjercicioPractico2_StevenFonseca.controller;

import EjercicioPractico2_StevenFonseca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {

    private final UsuarioService usuarioService;

    public ConsultasController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String consultas(Model model) {
        model.addAttribute("usuariosPorRol", usuarioService.usuariosPorRol(1L)); // ejemplo: ADMIN
        model.addAttribute("usuariosOrdenados", usuarioService.usuariosOrdenadosPorFecha());
        model.addAttribute("activos", usuarioService.contarActivos());
        model.addAttribute("inactivos", usuarioService.contarInactivos());
        return "consultas";
    }
}
