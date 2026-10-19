package uniandes.dpo.cds.persistencia;

import uniandes.dpo.cds.modelo.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ManejadorPersistencia {
    private String rutaData;

    public ManejadorPersistencia(String rutaData) {
        this.rutaData = rutaData;
        File carpeta = new File(rutaData);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public void cargarTodo(ClubDeportivoSeneca club) {
        crearArchivosBaseSiNoExisten();
        cargarDeportes(club);
        cargarUsuarios(club);
        cargarEquipos(club);
        cargarInstalaciones(club);
        cargarTienda(club);
        cargarCafeteria(club);
        cargarTurnos(club);
    }

    private void crearArchivosBaseSiNoExisten() {
        escribirSiNoExiste("deportes.txt",
            "CONJUNTO;Fútbol\nCONJUNTO;Baloncesto\nCONJUNTO;Voleibol\nINDIVIDUAL;Tenis;2\nINDIVIDUAL;Pádel;2\nINDIVIDUAL;Tenis de Mesa;2\n");
        escribirSiNoExiste("usuarios.txt",
            "ADMIN;admin;admin123;Carlos Administrador\n" +
            "ENTRENADOR;profe_juan;pass123;Juan Perez;DESC_JUAN\n" +
            "ENTRENADOR;profe_marta;pass123;Marta Gomez;DESC_MARTA\n" +
            "ENTRENADOR;profe_andres;pass123;Andres Castro;DESC_ANDRES\n" +
            "FISIOTERAPEUTA;fisio_laura;pass123;Laura Rios;DESC_LAURA\n" +
            "FISIOTERAPEUTA;fisio_camilo;pass123;Camilo Vega;DESC_CAMILO\n" +
            "SOCIO;socio_mateo;pass123;Mateo Silva;16;Tenis:Avanzado:1200.0,Pádel:Intermedio:1100.0\n" +
            "SOCIO;socio_valeria;pass123;Valeria Ortiz;16;Tenis:Intermedio:1150.0,Tenis de Mesa:Avanzado:1220.0\n" +
            "SOCIO;socio_lucas;pass123;Lucas Diaz;16;Tenis:Principiante:1000.0,Tenis de Mesa:Principiante:1000.0\n");
        escribirSiNoExiste("equipos.txt",
            "Halcones CDS;Fútbol;Sub-17;15\nCóndores CDS;Fútbol;Sub-17;15\nTitanes Mayores CDS;Fútbol;Mayores;18\nPanteras CDS;Voleibol;Sub-17;12\n");
        escribirSiNoExiste("instalaciones.txt",
            "TENIS;Cancha de Tenis Principal\nTENIS;Cancha de Tenis Auxiliar\nPADEL;Cancha de Pádel Central\nMESA_TM;Mesa Tenis de Mesa Salón Cubierto;true\nMESA_TM;Mesa Tenis de Mesa Exterior;false\n");
        escribirSiNoExiste("tienda.txt",
            "Raqueta de Tenis;Raquetas;450000;10\nBalón de Fútbol;Balones;120000;25\nMalla Reglamentaria;Mallas;180000;8\nUniforme Oficial CDS;Uniformes;85000;40\n");
        escribirSiNoExiste("cafeteria.txt",
            "BEBIDA;Café Americano;4500;true\nBEBIDA;Bebida Hidratante Fría;6000;false\nSNACK;Barra de Cereal con Maní;5000;Maní,Gluten\nSNACK;Croissant de Queso;4200;Gluten,Lácteos\n");
        escribirSiNoExiste("turnos.txt",
            "Lunes;08:00-12:00;profe_juan,profe_marta,profe_andres,fisio_laura\nMartes;08:00-12:00;profe_juan,profe_marta,fisio_laura,fisio_camilo\n");
    }

    private void escribirSiNoExiste(String nombreArchivo, String contenido) {
        File f = new File(rutaData, nombreArchivo);
        if (!f.exists()) {
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
                bw.write(contenido);
                bw.close();
            } catch (IOException e) {
                System.out.println("Error creando archivo: " + e.getMessage());
            }
        }
    }

    private void cargarDeportes(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "deportes.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 2) {
                        String nombre = partes[1];
                        if ("Fútbol".equalsIgnoreCase(nombre) || "Futbol".equalsIgnoreCase(nombre)) {
                            club.agregarDeporte(new Futbol());
                        } else if ("Baloncesto".equalsIgnoreCase(nombre)) {
                            club.agregarDeporte(new Baloncesto());
                        } else if ("Voleibol".equalsIgnoreCase(nombre)) {
                            club.agregarDeporte(new Voleibol());
                        } else if ("Tenis".equalsIgnoreCase(nombre)) {
                            int maxInst = Integer.parseInt(partes[2]);
                            club.agregarDeporte(new Tenis(maxInst));
                        } else if ("Pádel".equalsIgnoreCase(nombre) || "Padel".equalsIgnoreCase(nombre)) {
                            int maxInst = Integer.parseInt(partes[2]);
                            club.agregarDeporte(new Padel(maxInst));
                        } else if ("Tenis de Mesa".equalsIgnoreCase(nombre)) {
                            int maxInst = Integer.parseInt(partes[2]);
                            club.agregarDeporte(new TenisDeMesa(maxInst));
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar deportes: " + e.getMessage());
        }
    }

    private void cargarUsuarios(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "usuarios.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 4) {
                        String tipo = partes[0];
                        String login = partes[1];
                        String pass = partes[2];
                        String nombre = partes[3];

                        if ("ADMIN".equalsIgnoreCase(tipo)) {
                            club.agregarUsuario(new Administrador(login, pass, nombre));
                        } else if ("ENTRENADOR".equalsIgnoreCase(tipo)) {
                            String desc = partes[4];
                            club.agregarUsuario(new Entrenador(login, pass, nombre, desc));
                        } else if ("FISIOTERAPEUTA".equalsIgnoreCase(tipo)) {
                            String desc = partes[4];
                            club.agregarUsuario(new Fisioterapeuta(login, pass, nombre, desc));
                        } else if ("SOCIO".equalsIgnoreCase(tipo)) {
                            int edad = Integer.parseInt(partes[4]);
                            Socio socio = new Socio(login, pass, nombre, edad);
                            if (partes.length > 5 && !partes[5].trim().isEmpty()) {
                                String[] perfiles = partes[5].split(",");
                                for (int i = 0; i < perfiles.length; i++) {
                                    String[] datosP = perfiles[i].split(":");
                                    if (datosP.length == 3) {
                                        Deporte d = club.buscarDeporte(datosP[0].trim());
                                        if (d instanceof DisciplinaIndividual) {
                                            double elo = Double.parseDouble(datosP[2].trim());
                                            socio.obtenerOCrearPerfil((DisciplinaIndividual) d, datosP[1].trim(), elo);
                                        }
                                    }
                                }
                            }
                            club.agregarUsuario(socio);
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    private void cargarEquipos(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "equipos.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 4) {
                        String nombre = partes[0];
                        String nomDep = partes[1];
                        String cat = partes[2];
                        int cupo = Integer.parseInt(partes[3]);

                        Deporte dep = club.buscarDeporte(nomDep);
                        if (dep instanceof DeporteConjunto) {
                            club.crearEquipo(nombre, (DeporteConjunto) dep, cat, cupo);
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar equipos: " + e.getMessage());
        }
    }

    private void cargarInstalaciones(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "instalaciones.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 2) {
                        String tipo = partes[0];
                        String nom = partes[1];
                        if ("TENIS".equalsIgnoreCase(tipo)) {
                            club.agregarInstalacion(new CanchaTenis(nom));
                        } else if ("PADEL".equalsIgnoreCase(tipo)) {
                            club.agregarInstalacion(new CanchaPadel(nom));
                        } else if ("MESA_TM".equalsIgnoreCase(tipo)) {
                            boolean techada = false;
                            if (partes.length > 2) {
                                techada = Boolean.parseBoolean(partes[2]);
                            }
                            club.agregarInstalacion(new MesaTenisDeMesa(nom, techada));
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar instalaciones: " + e.getMessage());
        }
    }

    private void cargarTienda(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "tienda.txt");
        if (!file.exists()) {
            return;
        }
        UbicacionInventario bodega = new UbicacionInventario("Bodega Principal");
        UbicacionInventario mostrador = new UbicacionInventario("Mostrador Tienda");
        club.agregarUbicacionInventario(bodega);
        club.agregarUbicacionInventario(mostrador);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 4) {
                        String nom = partes[0];
                        String cat = partes[1];
                        double precio = Double.parseDouble(partes[2]);
                        int stock = Integer.parseInt(partes[3]);
                        ArticuloTienda art = new ArticuloTienda(nom, cat, precio, stock);
                        club.agregarArticuloTienda(art);
                        bodega.registrarExistenciaInicial(art, stock / 2);
                        mostrador.registrarExistenciaInicial(art, stock - (stock / 2));
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar tienda: " + e.getMessage());
        }
    }

    private void cargarCafeteria(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "cafeteria.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 3) {
                        String tipo = partes[0];
                        String nom = partes[1];
                        double precio = Double.parseDouble(partes[2]);
                        if ("BEBIDA".equalsIgnoreCase(tipo)) {
                            boolean caliente = false;
                            if (partes.length > 3) {
                                caliente = Boolean.parseBoolean(partes[3]);
                            }
                            club.agregarProductoCafeteria(new Bebida(nom, precio, caliente));
                        } else if ("SNACK".equalsIgnoreCase(tipo)) {
                            List<String> alergenos = new ArrayList<String>();
                            if (partes.length > 3 && !partes[3].trim().isEmpty()) {
                                String[] listaA = partes[3].split(",");
                                for (int i = 0; i < listaA.length; i++) {
                                    alergenos.add(listaA[i].trim());
                                }
                            }
                            club.agregarProductoCafeteria(new Snack(nom, precio, alergenos));
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar cafeteria: " + e.getMessage());
        }
    }

    private void cargarTurnos(ClubDeportivoSeneca club) {
        File file = new File(rutaData, "turnos.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String linea = br.readLine();
            while (linea != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 3) {
                        String dia = partes[0];
                        String horario = partes[1];
                        TurnoSemanal turno = club.obtenerOCrearTurno(dia, horario);
                        String[] logins = partes[2].split(",");
                        for (int i = 0; i < logins.length; i++) {
                            Usuario u = club.buscarUsuario(logins[i].trim());
                            if (u instanceof Empleado) {
                                turno.asignarEmpleado((Empleado) u);
                            }
                        }
                    }
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar turnos: " + e.getMessage());
        }
    }

    public void guardarVentas(List<Venta> ventas) {
        File file = new File(rutaData, "ventas.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            for (int i = 0; i < ventas.size(); i++) {
                Venta v = ventas.get(i);
                bw.write(v.getFecha() + ";" + v.getRubroContable() + ";" + v.getComprador().getLogin() + ";" +
                         v.getSubtotal() + ";" + v.getDescuentoEmpleado() + ";" + v.getPuntosRedimidos() + ";" +
                         v.getValorTotal() + ";" + v.getPuntosFidelidadGenerados() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar ventas: " + e.getMessage());
        }
    }
}
