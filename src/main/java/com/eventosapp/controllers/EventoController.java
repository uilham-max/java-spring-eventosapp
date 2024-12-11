package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.IConvidadoRepository;
import com.eventosapp.repository.IEventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventoController {

    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IConvidadoRepository convidadoRepository;
    @RequestMapping(value ="/cadastrarEvento", method = RequestMethod.GET)
    public String form(){
        return "evento/cadastrarEvento";
    }


    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar evento");
            return "redirect:/cadastrarEvento";
        }
        eventoRepository.save(evento);
        redirectAttributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
        return "redirect:/cadastrarEvento";
    }


    @RequestMapping(value = "/eventos", method = RequestMethod.GET)
    public ModelAndView listaEventos(){

        ModelAndView modelAndView = new ModelAndView("index"); // Variável que renderiza os dados no front
        Iterable<Evento> eventos = eventoRepository.findAll(); // Buscando a lista de eventos do banco
        modelAndView.addObject("eventos", eventos); // Adinciona a lista de eventos ao objeto

        return modelAndView;
    }


    @RequestMapping(value ="/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){

        Evento evento = eventoRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("evento/detalhesEvento");
        modelAndView.addObject("evento", evento);

        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        modelAndView.addObject("convidados", convidados);

        return modelAndView;
    }


    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar convidado");
            return "redirect:/{codigo}";
        }

        Evento evento = eventoRepository.findByCodigo(codigo);
        convidado.setEvento(evento);
        convidadoRepository.save(convidado);
        redirectAttributes.addFlashAttribute("mensagem", "Convidado cadastrado com sucesso");

        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(long codigo){
        Evento evento = eventoRepository.findByCodigo(codigo);
        eventoRepository.delete(evento);
        return "redirect:/eventos";
    }

    @RequestMapping("/deleterConvidado")
    public String deletarConvidado(String cpf){
        Convidado convidado = convidadoRepository.findByCpf(cpf);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong =  evento.getCodigo();
        String codigo = "" + codigoLong;
        return "redirect:/" + codigo;
    }

}
