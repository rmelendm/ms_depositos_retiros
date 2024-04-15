package org.example.Services;

import org.example.Entity.Cuenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositDebitService {
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cuenta agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        return cuenta;
    }

    public void realizarDeposito(String cod_cta, double monto) {
        Optional<Cuenta> optionalCuenta = cuentas.stream()
                .filter(c -> c.getCod_cta().equals(cod_cta))
                .findFirst();

        optionalCuenta.ifPresent(cuenta -> {
            // Realizar depósito
            if (cuenta.getTipo_movimiento().equals("deposito")) {
                cuenta.setSaldo(cuenta.getSaldo() + monto);
            }
        });
    }

    public void realizarRetiro(String cod_cta, double monto) {
        Optional<Cuenta> optionalCuenta = cuentas.stream()
                .filter(c -> c.getCod_cta().equals(cod_cta))
                .findFirst();
        optionalCuenta.ifPresent(cuenta -> {
            // Realizar retiro
            if (cuenta.getTipo_movimiento().equals("retiro")) {
                double saldoActual = cuenta.getSaldo();
                cuenta.setSaldo(saldoActual - monto);
                // Mensaje de error con lambda si el saldo es insuficiente
                Optional.of(saldoActual >= monto)
                        .filter(suficiente -> !suficiente)
                        .ifPresent(saldoSuficiente -> System.out.println("Saldo insuficiente para realizar el retiro."));
            }
        });
    }


    public Cuenta obtenerCuenta(String cod_cta) {
        Optional<Cuenta> optionalCuenta = cuentas.stream()
                .filter(c -> c.getCod_cta().equals(cod_cta))
                .findFirst();
        return optionalCuenta.orElse(null);
    }

    public Cuenta actualizarCuenta(Cuenta cuenta) {
        Optional<Cuenta> optionalCuenta = cuentas.stream()
                .filter(c -> c.getCod_cta().equals(cuenta.getCod_cta()))
                .findFirst();

        optionalCuenta.ifPresent(c -> {
            c.setTipo_cta(cuenta.getTipo_cta());
            c.setTitular_cta(cuenta.getTitular_cta());
            c.setFirmante_autorizado_cta(cuenta.getFirmante_autorizado_cta());
            c.setTipo_movimiento(cuenta.getTipo_movimiento());
        });

        return optionalCuenta.orElse(null);
    }

    public void eliminarCuenta(String cod_cta) {
        cuentas.removeIf(c -> c.getCod_cta().equals(cod_cta));
    }

    public List<Cuenta> obtenerTodosLosMovimientos() {
        return new ArrayList<>(cuentas);
    }
}

