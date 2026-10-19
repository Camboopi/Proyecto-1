package uniandes.dpo.cds.consola;

import uniandes.dpo.cds.modelo.*;
import uniandes.dpo.cds.persistencia.ManejadorPersistencia;

public class DemoTiendaCafeteria {
    public static void main(String[] args) {
        System.out.println("=== DEMO 3: TIENDA, CAFETERIA, PUNTOS DE FIDELIDAD Y BEBIDAS CALIENTES ===");

        ManejadorPersistencia persistencia = new ManejadorPersistencia("data");
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        persistencia.cargarTodo(club);

        Socio mateo = (Socio) club.buscarUsuario("socio_mateo");
        Entrenador profeJuan = (Entrenador) club.buscarUsuario("profe_juan");
        ArticuloTienda raqueta = club.buscarArticuloTienda("Raqueta de Tenis");
        Bebida cafeCaliente = (Bebida) club.buscarProductoCafeteria("Café Americano");
        Snack barraMani = (Snack) club.buscarProductoCafeteria("Barra de Cereal con Maní");

        Instalacion canchaPadel = club.buscarInstalacion("Cancha de Pádel Central");
        Instalacion mesaTechada = club.buscarInstalacion("Mesa Tenis de Mesa Salón Cubierto");
        Instalacion canchaTenis = club.buscarInstalacion("Cancha de Tenis Principal");

        System.out.println("\n1. Compra en tienda por socio con codigo de descuento de empleado (8% + IVA 19%):");
        VentaTienda ventaSocio = new VentaTienda("2026-10-24", mateo);
        ventaSocio.agregarArticulo(raqueta, 1);
        ventaSocio.aplicarCodigoDescuento(profeJuan);
        club.registrarVenta(ventaSocio);

        System.out.println(" - Subtotal: $" + Math.round(ventaSocio.getSubtotal()) +
                           " | Descuento (8%): $" + Math.round(ventaSocio.getDescuentoEmpleado()) +
                           " | Total con IVA (19%): $" + Math.round(ventaSocio.getValorTotal()));
        System.out.println(" - Puntos de fidelidad generados (2%): " + mateo.getPuntosFidelidad());

        System.out.println("\n2. Compra en tienda por empleado con su propio codigo (15% descuento propio):");
        VentaTienda ventaEmpleado = new VentaTienda("2026-10-24", profeJuan);
        ventaEmpleado.agregarArticulo(club.buscarArticuloTienda("Balón de Fútbol"), 1);
        ventaEmpleado.aplicarCodigoDescuento(profeJuan);
        club.registrarVenta(ventaEmpleado);
        System.out.println(" - Subtotal: $" + Math.round(ventaEmpleado.getSubtotal()) +
                           " | Descuento propio (15%): $" + Math.round(ventaEmpleado.getDescuentoEmpleado()) +
                           " | Total con IVA (19%): $" + Math.round(ventaEmpleado.getValorTotal()));

        System.out.println("\n3. Restriccion de bebidas calientes en cancha de padel y mesa de tenis de mesa techada:");
        VentaCafeteria intentoPadel = new VentaCafeteria("2026-10-24", mateo, canchaPadel);
        boolean despPadel = intentoPadel.agregarProductoCafeteria(cafeCaliente, 1);
        System.out.println(" - Despachar bebida caliente en cancha de Padel: " + despPadel);

        VentaCafeteria intentoMesaTechada = new VentaCafeteria("2026-10-24", mateo, mesaTechada);
        boolean despMesaTechada = intentoMesaTechada.agregarProductoCafeteria(cafeCaliente, 1);
        System.out.println(" - Despachar bebida caliente en mesa de Tenis de Mesa techada: " + despMesaTechada);

        System.out.println("\n4. Compra en cafeteria (impuesto consumo 8%, propina 10%, alergenos y redencion de puntos):");
        System.out.println(" - " + barraMani.getNombre() + " -> " + barraMani.informarAlergenosAlSocio());
        VentaCafeteria ventaCafe = new VentaCafeteria("2026-10-24", mateo, canchaTenis);
        ventaCafe.agregarProductoCafeteria(cafeCaliente, 1);
        ventaCafe.agregarProductoCafeteria(barraMani, 2);
        ventaCafe.redimirPuntosComoDescuento(5000);
        club.registrarVenta(ventaCafe);

        System.out.println(" - Subtotal cafeteria: $" + Math.round(ventaCafe.getSubtotal()) +
                           " | Puntos redimidos: " + ventaCafe.getPuntosRedimidos() +
                           " | Total con impuesto (8%) y propina (10%): $" + Math.round(ventaCafe.getValorTotal()));
        System.out.println(" - Saldo final de puntos de Mateo: " + mateo.getPuntosFidelidad());

        boolean reservaPadelConBebida = club.registrarReserva(mateo, canchaPadel, "18:00-19:00", 4, "Dobles");
        System.out.println(" - Reservar/usar cancha de Padel teniendo bebida caliente: " + reservaPadelConBebida);

        persistencia.guardarVentas(club.getVentas());
    }
}
