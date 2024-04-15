package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cuenta {

    private String cod_cta;
    private String tipo_cta;
    private String titular_cta;
    private String firmante_autorizado_cta;
    private String tipo_movimiento;
    double saldo;

}
