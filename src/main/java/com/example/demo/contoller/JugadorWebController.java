package com.example.demo.contoller;

import com.example.demo.model.Jugador;
import com.example.demo.service.JugadorService;
import com.example.demo.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugadores")
public class JugadorWebController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private ClubService clubService;

    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorService.findAll());
        return "jugadores/lista";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Crear un objeto completamente nuevo con ID explícitamente nulo
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setId(null);
        model.addAttribute("jugador", nuevoJugador);
        model.addAttribute("esNuevo", true); // Añadir un flag para indicar que es nuevo
        model.addAttribute("clubes", clubService.findAll());
        return "jugadores/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarJugador(@PathVariable String id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("esNuevo", false); // Añadir un flag para indicar que es edición
        model.addAttribute("clubes", clubService.findAll());
        return "jugadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador, 
                                @RequestParam(value = "esNuevo", required = false, defaultValue = "true") Boolean esNuevo,
                                RedirectAttributes redirectAttributes) {
        try {
            if (esNuevo) {
                // Asegurar que el ID sea nulo para crear un nuevo registro
                jugador.setId(null);
                jugadorService.save(jugador);
                redirectAttributes.addFlashAttribute("mensaje", "Jugador creado con éxito");
            } else {
                // Verificar que el ID no sea nulo para actualizar
                if (jugador.getId() != null && !jugador.getId().isEmpty()) {
                    jugadorService.update(jugador.getId(), jugador);
                    redirectAttributes.addFlashAttribute("mensaje", "Jugador actualizado con éxito");
                } else {
                    throw new IllegalArgumentException("ID no válido para actualización");
                }
            }
            return "redirect:/jugadores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el jugador: " + e.getMessage());
            return "redirect:/jugadores/formulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            jugadorService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Jugador eliminado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el jugador: " + e.getMessage());
        }
        return "redirect:/jugadores";
    }
}

