package com.example.calculadora_rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculadoraController {

    @RequestMapping(value = "/somar", method = {RequestMethod.GET, RequestMethod.POST})
    public double somar(@RequestParam double a, @RequestParam double b) {
        return a + b;
    }

    @RequestMapping(value = "/subtrair", method = {RequestMethod.GET, RequestMethod.POST})
    public double subtrair(@RequestParam double a, @RequestParam double b) {
        return a - b;
    }

    @RequestMapping(value = "/multiplicar", method = {RequestMethod.GET, RequestMethod.POST})
    public double multiplicar(@RequestParam double a, @RequestParam double b) {
        return a * b;
    }

    @RequestMapping(value = "/dividir", method = {RequestMethod.GET, RequestMethod.POST})
    public double dividir(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return a / b;
    }

    @RequestMapping(value = "/exponenciar", method = {RequestMethod.GET, RequestMethod.POST})
    public double exponenciar(@RequestParam double a, @RequestParam double b) {
        return Math.pow(a, b);
    }
}
