package com.example.demo.contoller;

import com.example.demo.model.Competicion;
import com.example.demo.service.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/competiciones")
public class CompeticionWebController {

    @Autowired
    private CompeticionService competicionService;

    @GetMapping
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionService.findAll());
        return "competiciones/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Crear un objeto completamente nuevo con ID explícitamente nulo
        Competicion nuevaCompeticion = new Competicion();
        nuevaCompeticion.setId(null);
        model.addAttribute("competicion", nuevaCompeticion);
        model.addAttribute("esNuevo", true); // Añadir un flag para indicar que es nuevo
        return "competiciones/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarCompeticion(@PathVariable String id, Model model) {
        Competicion competicion = competicionService.findById(id);
        model.addAttribute("competicion", competicion);
        model.addAttribute("esNuevo", false); // Añadir un flag para indicar que es edición
        return "competiciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion, 
                                    @RequestParam(value = "esNuevo", required = false, defaultValue = "true") Boolean esNuevo,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (esNuevo) {
                // Asegurar que el ID sea nulo para crear un nuevo registro
                competicion.setId(null);
                competicionService.save(competicion);
                redirectAttributes.addFlashAttribute("mensaje", "Competición creada con éxito");
            } else {
                // Verificar que el ID no sea nulo para actualizar
                if (competicion.getId() != null && !competicion.getId().isEmpty()) {
                    competicionService.update(competicion.getId(), competicion);
                    redirectAttributes.addFlashAttribute("mensaje", "Competición actualizada con éxito");
                } else {
                    throw new IllegalArgumentException("ID no válido para actualización");
                }
            }
            return "redirect:/competiciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la competición: " + e.getMessage());
            return "redirect:/competiciones/formulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            competicionService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Competición eliminada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la competición: " + e.getMessage());
        }
        return "redirect:/competiciones";
    }
}

