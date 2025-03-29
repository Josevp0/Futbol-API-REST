package com.example.demo.contoller;

import com.example.demo.model.Entrenador;
import com.example.demo.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorWebController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorService.findAll());
        return "entrenadores/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Crear un objeto completamente nuevo con ID explícitamente nulo
        Entrenador nuevoEntrenador = new Entrenador();
        nuevoEntrenador.setId(null);
        model.addAttribute("entrenador", nuevoEntrenador);
        model.addAttribute("esNuevo", true); // Añadir un flag para indicar que es nuevo
        return "entrenadores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarEntrenador(@PathVariable String id, Model model) {
        Entrenador entrenador = entrenadorService.findById(id);
        model.addAttribute("entrenador", entrenador);
        model.addAttribute("esNuevo", false); // Añadir un flag para indicar que es edición
        return "entrenadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador, 
                                   @RequestParam(value = "esNuevo", required = false, defaultValue = "true") Boolean esNuevo,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (esNuevo) {
                // Asegurar que el ID sea nulo para crear un nuevo registro
                entrenador.setId(null);
                entrenadorService.save(entrenador);
                redirectAttributes.addFlashAttribute("mensaje", "Entrenador creado con éxito");
            } else {
                // Verificar que el ID no sea nulo para actualizar
                if (entrenador.getId() != null && !entrenador.getId().isEmpty()) {
                    entrenadorService.update(entrenador.getId(), entrenador);
                    redirectAttributes.addFlashAttribute("mensaje", "Entrenador actualizado con éxito");
                } else {
                    throw new IllegalArgumentException("ID no válido para actualización");
                }
            }
            return "redirect:/entrenadores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el entrenador: " + e.getMessage());
            return "redirect:/entrenadores/formulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            entrenadorService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Entrenador eliminado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el entrenador: " + e.getMessage());
        }
        return "redirect:/entrenadores";
    }
}

