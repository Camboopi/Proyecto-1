package uniandes.dpo.cds.modelo;

import java.util.ArrayList;
import java.util.List;

public class VentaCafeteria extends Venta {
    private double impuestoConsumo;
    private double propinaSugerida;
    private boolean alergenosInformados;
    private Instalacion instalacionConsumo;
    private List<ProductoCafeteria> productos;

    public VentaCafeteria(String fecha, Usuario comprador, Instalacion instalacionConsumo) {
        super(fecha, "Cafetería", comprador);
        this.impuestoConsumo = 0.08;
        this.propinaSugerida = 0.10;
        this.alergenosInformados = true;
        this.instalacionConsumo = instalacionConsumo;
        this.productos = new ArrayList<ProductoCafeteria>();
    }

    public boolean validarDespachoSegunInstalacion() {
        for (int i = 0; i < productos.size(); i++) {
            if (!productos.get(i).puedeDespacharseEn(instalacionConsumo)) {
                return false;
            }
        }
        return true;
    }

    public boolean agregarProductoCafeteria(ProductoCafeteria prod, int cantidad) {
        if (!prod.puedeDespacharseEn(instalacionConsumo)) {
            return false;
        }
        if (prod instanceof Bebida && ((Bebida) prod).isEsCaliente() && comprador instanceof Socio) {
            ((Socio) comprador).setTieneBebidaCaliente(true);
        }
        productos.add(prod);
        agregarDetalle(new DetalleVenta(cantidad, prod.getPrecio()));
        return true;
    }

    public void modificarPropina(double valor) {
        this.propinaSugerida = valor;
        recalcularTotales();
    }

    @Override
    public void recalcularTotales() {
        this.subtotal = 0;
        for (int i = 0; i < detalles.size(); i++) {
            this.subtotal = this.subtotal + detalles.get(i).calcularSubtotal();
        }
        double base = subtotal - descuentoEmpleado;
        if (base < 0) {
            base = 0;
        }
        double imp = base * impuestoConsumo;
        double prop = base * propinaSugerida;
        this.valorTotal = base + imp + prop - puntosRedimidos;
        if (this.valorTotal < 0) {
            this.valorTotal = 0;
        }
    }

    public double getImpuestoConsumo() {
        return impuestoConsumo;
    }

    public double getPropinaSugerida() {
        return propinaSugerida;
    }

    public boolean isAlergenosInformados() {
        return alergenosInformados;
    }
}
