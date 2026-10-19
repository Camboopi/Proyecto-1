package uniandes.dpo.cds.modelo;

import java.util.HashMap;
import java.util.Map;

public class UbicacionInventario {
    private String nombreUbicacion;
    private Map<ArticuloTienda, Integer> cantidadesPorArticulo;

    public UbicacionInventario(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
        this.cantidadesPorArticulo = new HashMap<ArticuloTienda, Integer>();
    }

    public void reabastecerArticulo(ArticuloTienda art, int cant) {
        int actual = 0;
        if (cantidadesPorArticulo.containsKey(art)) {
            actual = cantidadesPorArticulo.get(art);
        }
        cantidadesPorArticulo.put(art, actual + cant);
        art.incrementarInventario(cant);
    }

    public void registrarExistenciaInicial(ArticuloTienda art, int cant) {
        cantidadesPorArticulo.put(art, cant);
    }

    public boolean moverArticulosA(UbicacionInventario dest, ArticuloTienda art, int cant) {
        int actual = 0;
        if (cantidadesPorArticulo.containsKey(art)) {
            actual = cantidadesPorArticulo.get(art);
        }
        if (cant <= 0 || actual < cant) {
            return false;
        }
        cantidadesPorArticulo.put(art, actual - cant);
        int enDestino = 0;
        if (dest.cantidadesPorArticulo.containsKey(art)) {
            enDestino = dest.cantidadesPorArticulo.get(art);
        }
        dest.cantidadesPorArticulo.put(art, enDestino + cant);
        return true;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public Map<ArticuloTienda, Integer> getCantidadesPorArticulo() {
        return cantidadesPorArticulo;
    }
}
