package com.dataforge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataforge.model.CPF;
import com.dataforge.service.CPFService;

@RestController
@RequestMapping("/cpf")
public class CPFController {

    private final CPFService cpfService;

    @Autowired
    public CPFController(CPFService cpfService) {
        this.cpfService = cpfService;
    }

    @GetMapping("/generate")
    public CPF generateSingleCPF() {
        return cpfService.generateSingleCPF();
    }

    @GetMapping("/generate/{quantity}")
    public List<CPF> generateMultipleCPF(@PathVariable int quantity) {
        return cpfService.generateMultipleCPF(quantity);
    }
}
