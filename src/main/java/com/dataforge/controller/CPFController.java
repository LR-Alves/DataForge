package com.dataforge.controller;

import com.dataforge.model.CPF;
import com.dataforge.service.CPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

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
    
    @PostMapping("/validate-cpf")
    public boolean validateCPF(@RequestBody String cpf) {
        return cpfService.validateCPF(cpf);
    }

}
