package org.example.projeto_led.controllers;

import org.example.projeto_led.ControlePorta;
import org.example.projeto_led.entities.Led;
import org.example.projeto_led.repositories.LedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/led")
public class LedController {

    @Autowired
    private LedRepository ledRepository;

    ControlePorta controle = new ControlePorta("COM4", 9600);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping
    public String getLeds(Model model) {
        List<Led> led = ledRepository.findAll();
        model.addAttribute("led", led);
        return "led";
    }

    @PostMapping("/ligar")
    public String ligarLed(Model model) {
        Led led = new Led();
        controle.enviaDados('1');
        led.setLigado(true);
        led.setData(LocalDateTime.now());
        ledRepository.save(led);
        model.addAttribute("led", led);
        return "led";
    }

    @PostMapping("/desligar")
    public String desligarLed(Model model) {
        Led led = new Led();
        controle.close();
        controle.enviaDados('2');
        led.setLigado(false);
        led.setData(LocalDateTime.now());
        ledRepository.save(led);
        model.addAttribute("led", led);
        return "led";
    }
}
