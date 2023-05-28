package org.example;

public class Consumidor extends Thread {
    private String nombre;
    private Almacen almacen;
    private int kilosTotalesComprados;
    private final int kilosCompra = 1;
    private final int tiempoEspera = 2000;

    public Consumidor(String nombre, Almacen almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
        this.kilosTotalesComprados = 0;
    }

    public void run() {
        while (this.almacen.isAbierto()) {
            try {
                this.almacen.venderKilosTrigo(this.kilosCompra);
                this.kilosTotalesComprados += this.kilosCompra;
                System.out.printf(String.format(
                        "%s compró %s kilos de trigo. Total de trigo comprado: %s kilos%n" +
                        "Operación %s. Trigo restante %s.%n" +
                        "------------------------------------------------------------------------------%n",
                        this.nombre, this.kilosCompra, this.kilosTotalesComprados,
                        this.almacen.getOperacionesDia(), this.almacen.getKilosTrigoExistentes()));
                Thread.sleep(this.tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
