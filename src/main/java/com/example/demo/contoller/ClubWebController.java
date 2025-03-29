package com.example.demo.contoller;

import com.example.demo.model.Club;
import com.example.demo.service.ClubService;
import com.example.demo.service.AsociacionService;
import com.example.demo.service.EntrenadorService;
import com.example.demo.service.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clubes")
public class ClubWebController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private AsociacionService asociacionService;

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private CompeticionService competicionService;

    @GetMapping
    public String listarClubes(Model model) {
        model.addAttribute("clubes", clubService.findAll());
        return "clubes/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Crear un objeto completamente nuevo con ID explícitamente nulo
        Club nuevoClub = new Club();
        nuevoClub.setId(null);
        model.addAttribute("club", nuevoClub);
        model.addAttribute("esNuevo", true); // Añadir un flag para indicar que es nuevo
        cargarListasRelacionadas(model);
        return "clubes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarClub(@PathVariable String id, Model model) {
        Club club = clubService.findById(id);
        model.addAttribute("club", club);
        model.addAttribute("esNuevo", false); // Añadir un flag para indicar que es edición
        cargarListasRelacionadas(model);
        return "clubes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club, 
                             @RequestParam(value = "esNuevo", required = false, defaultValue = "true") Boolean esNuevo,
                             RedirectAttributes redirectAttributes) {
        try {
            if (esNuevo) {
                // Asegurar que el ID sea nulo para crear un nuevo registro
                club.setId(null);
                clubService.save(club);
                redirectAttributes.addFlashAttribute("mensaje", "Club creado con éxito");
            } else {
                // Verificar que el ID no sea nulo para actualizar
                if (club.getId() != null && !club.getId().isEmpty()) {
                    clubService.update(club.getId(), club);
                    redirectAttributes.addFlashAttribute("mensaje", "Club actualizado con éxito");
                } else {
                    throw new IllegalArgumentException("ID no válido para actualización");
                }
            }
            return "redirect:/clubes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el club: " + e.getMessage());
            return "redirect:/clubes/formulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            clubService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Club eliminado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el club: " + e.getMessage());
        }
        return "redirect:/clubes";
    }

    private void cargarListasRelacionadas(Model model) {
        model.addAttribute("asociaciones", asociacionService.findAll());
        model.addAttribute("entrenadores", entrenadorService.findAll());
        model.addAttribute("competiciones", competicionService.findAll());
    }
}

