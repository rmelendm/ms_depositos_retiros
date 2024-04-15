package org.example.Controller;

import org.example.Entity.Cuenta;
import org.example.Services.DepositDebitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class DepositDebitController {
    private final DepositDebitService service;

    public DepositDebitController(DepositDebitService service) {
        this.service = service;
    }

    @PostMapping("/agregar")
    public Cuenta agregarCuenta(@RequestBody Cuenta cuenta) {
        return service.agregarCuenta(cuenta);
    }

    @GetMapping("/obtener/{cod_cta}")
    public Cuenta obtenerCuenta(@PathVariable String cod_cta) {
        return service.obtenerCuenta(cod_cta);
    }

    @GetMapping("/lista")
    public List<Cuenta> obtenerTodosLosMovimientos() {
        return service.obtenerTodosLosMovimientos();
    }

    @PutMapping("/actualizar")
    public Cuenta actualizarCuenta(@RequestBody Cuenta cuenta) {
        return service.actualizarCuenta(cuenta);
    }

    @DeleteMapping("/eliminar/{cod_cta}")
    public void eliminarCuenta(@PathVariable String cod_cta) {
        service.eliminarCuenta(cod_cta);
    }

    @PostMapping("/deposito")
    public void realizarDeposito(@RequestParam String cod_cta, @RequestParam double monto) {
        service.realizarDeposito(cod_cta, monto);
    }

    @PostMapping("/retiro")
    public void realizarRetiro(@RequestParam String cod_cta, @RequestParam double monto) {
        service.realizarRetiro(cod_cta, monto);
    }
}
