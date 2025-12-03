package EjercicioPractico2_StevenFonseca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    @GetMapping
    public String reportes(Model model) {
        // Aquí puedes agregar métricas o estadísticas
        model.addAttribute("mensaje", "Página de reportes para profesores");
        return "reportes";
    }
}
