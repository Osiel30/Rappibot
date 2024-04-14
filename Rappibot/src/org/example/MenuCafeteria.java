package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCafeteria {
    private Map<String, Map<String, Producto>> menus;

    public MenuCafeteria() {
        this.menus = new HashMap<>();
        inicializarMenus();
    }

    private void inicializarMenus() {
        // Menú de Cafetería A
        Map<String, Producto> menuCafeteriaA = new HashMap<>();
        menuCafeteriaA.put("Gordita", new Producto("Gordita", "Salsa Verde, Picadillo, Deshebrada", 15.0, 15, 5));
        menuCafeteriaA.put("Flauta Ch", new Producto("Flauta Ch", "Salsa Verde, Picadillo, Deshebrada", 15.0, 15, 5));
        menuCafeteriaA.put("Flauta Gde", new Producto("Flauta Gde", "Salsa Verde, Picadillo, Deshebrada", 45.0, 10, 10));
        menuCafeteriaA.put("Coca Cola", new Producto("Coca Cola", "600 ml", 20.0, 10, 0));
        menuCafeteriaA.put("Coca Cola", new Producto("Coca Cola", "250 ml", 15.0, 10, 0));
        menuCafeteriaA.put("Sabritas", new Producto("Sabritas", "Adobadas", 20.0, 10, 0));
        menuCafeteriaA.put("Sabritas", new Producto("Sabritas", "Sal", 20.0, 10, 0));
        menuCafeteriaA.put("Sabritas", new Producto("Sabritas", "Especias", 20.0, 10, 0));
        menuCafeteriaA.put("Galletas", new Producto("Galletas", "Principe chocolate", 20.0, 10, 0));
        menuCafeteriaA.put("Galletas", new Producto("Galletas", "Chokis", 20.0, 10, 0));

        menus.put("Cafeteria_Jaguares", menuCafeteriaA);

        // Menú de Cafetería B
        Map<String, Producto> menuCafeteriaB = new HashMap<>();
        menuCafeteriaB.put("Tostada", new Producto("Tostada", "Salsa Verde", 15.0, 15, 5));
        menuCafeteriaB.put("Tostada", new Producto("Tostada", "Picadillo", 15.0, 15, 5));
        menuCafeteriaB.put("Tostada", new Producto("Tostada", "Deshebrada", 15.0, 15, 5));
        menuCafeteriaB.put("Migada", new Producto("Migada", "Salsa Verde", 15.0, 15, 5));
        menuCafeteriaB.put("Migada", new Producto("Migada", "Picadillo", 15.0, 15, 5));
        menuCafeteriaB.put("Migada", new Producto("Migada", "Deshebrada", 15.0, 15, 5));
        menuCafeteriaB.put("Hamburguesa", new Producto("Hamburguesa", "pollo", 45.0, 10, 15));
        menuCafeteriaB.put("Hamburguesa", new Producto("Hamburguesa", "res", 45.0, 10, 15));
        menuCafeteriaB.put("Nachos", new Producto("Nachos", "chicos", 25.0, 10, 5));
        menuCafeteriaB.put("Nachos", new Producto("Nachos", "grandes", 40.0, 10, 5));
        menuCafeteriaB.put("Coca Cola", new Producto("Coca Cola", "600 ml", 20.0, 10, 0));
        menuCafeteriaB.put("Coca Cola", new Producto("Coca Cola", "250 ml", 15.0, 10, 0));
        menuCafeteriaB.put("Sabritas", new Producto("Sabritas", "Adobadas", 20.0, 10, 0));
        menuCafeteriaB.put("Sabritas", new Producto("Sabritas", "Sal", 20.0, 10, 0));
        menuCafeteriaB.put("Sabritas", new Producto("Sabritas", "Especias", 20.0, 10, 0));
        menuCafeteriaB.put("Galletas", new Producto("Galletas", "Principe chocolate", 20.0, 10, 0));
        menuCafeteriaB.put("Galletas", new Producto("Galletas", "Chokis", 20.0, 10, 0));
        menus.put("Cafeteria_Upv", menuCafeteriaB);
    }

    public String[] obtenerMenu(String cafeteria) {
        List<String> comandos = new ArrayList<>();
        if (menus.containsKey(cafeteria)) {
            for (Map.Entry<String, Producto> entry : menus.get(cafeteria).entrySet()) {
                Producto producto = entry.getValue();
                // Generar el comando para el producto
                String comando = "/ordenar_" + producto.getNombre().toLowerCase().replace(" ", "_");
                comandos.add(comando);
            }
        } else {
            comandos.add("No se encontró el menú para la cafetería especificada.");
        }
        return comandos.toArray(new String[0]);
    }
}
