package com.example.prova1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.prova1.model.Candidato;
import com.example.prova1.service.CandidatosTseService;

@Controller
public class CandidatosTseController {
  private final CandidatosTseService candidatosTseService;

  public CandidatosTseController(CandidatosTseService candidatosTseService) {
    this.candidatosTseService = candidatosTseService;
  }

  @GetMapping("/")
  public String Index(
    @RequestParam(required = false) String cargo,
    @RequestParam(required = false) String partido,
    @RequestParam(required = false) String texto,
    Model model){
    List<Candidato> candidatosFiltrados = candidatosTseService.filtrar(cargo, partido, texto);
    
    model.addAttribute("candidatos", candidatosFiltrados);
    model.addAttribute("total", candidatosFiltrados.size());
    
    model.addAttribute("cargos", candidatosTseService.listarCargos());
    model.addAttribute("partidos", candidatosTseService.listarPartidos());
    
    model.addAttribute("cargoSelecionado", cargo != null ? cargo : "");
    model.addAttribute("partidoSelecionado", partido != null ? partido : "");
    model.addAttribute("textoBuscado", texto != null ? texto : "");

    return "index";
  }
}