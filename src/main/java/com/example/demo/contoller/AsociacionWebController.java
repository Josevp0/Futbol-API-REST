package com.example.demo.contoller;

import com.example.demo.model.Asociacion;
import com.example.demo.service.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionWebController {

    @Autowired
    private AsociacionService asociacionService;

    @GetMapping
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionService.findAll());
        return "asociaciones/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Crear un objeto completamente nuevo con ID explícitamente nulo
        Asociacion nuevaAsociacion = new Asociacion();
        nuevaAsociacion.setId(null);
        model.addAttribute("asociacion", nuevaAsociacion);
        model.addAttribute("esNuevo", true); // Añadir un flag para indicar que es nuevo
        return "asociaciones/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarAsociacion(@PathVariable String id, Model model) {
        Asociacion asociacion = asociacionService.findById(id);
        model.addAttribute("asociacion", asociacion);
        model.addAttribute("esNuevo", false); // Añadir un flag para indicar que es edición
        return "asociaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion, 
                                   @RequestParam(value = "esNuevo", required = false, defaultValue = "true") Boolean esNuevo,
                                   RedirectAttributes redirectAttributes) {
        try {
            System.out.println("esNuevo: " + esNuevo);
            System.out.println("ID: " + asociacion.getId());
            
            if (esNuevo) {
                // Asegurar que el ID sea nulo para crear un nuevo registro
                asociacion.setId(null);
                Asociacion nuevaAsociacion = asociacionService.save(asociacion);
                redirectAttributes.addFlashAttribute("mensaje", "Asociación creada con éxito");
            } else {
                // Verificar que el ID no sea nulo para actualizar
                if (asociacion.getId() != null && !asociacion.getId().isEmpty()) {
                    Asociacion asociacionActualizada = asociacionService.update(asociacion.getId(), asociacion);
                    redirectAttributes.addFlashAttribute("mensaje", "Asociación actualizada con éxito");
                } else {
                    throw new IllegalArgumentException("ID no válido para actualización");
                }
            }
            return "redirect:/asociaciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la asociación: " + e.getMessage());
            return "redirect:/asociaciones/formulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            asociacionService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Asociación eliminada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la asociación: " + e.getMessage());
        }
        return "redirect:/asociaciones";
    }
}

