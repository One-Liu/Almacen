package org.example;

public class Productor extends Thread {
    private String nombre;
    private Almacen almacen;
    private int kilosTotalesProducidos;
    private final int kilosProduccion = 10;
    private final int tiempoEspera = 5000;

    public Productor(String nombre, Almacen almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
        this.kilosTotalesProducidos = 0;
    }

    public void run() {
        while(this.almacen.isAbierto()) {
            try {
                this.almacen.producirKilosTrigo(this.kilosProduccion);
                this.kilosTotalesProducidos += this.kilosProduccion;
                System.out.printf(
                        "%s produció %s kilos de trigo. Total de trigo producido: %s kilos%n" +
                        "Operación %s. Trigo restante %s.%n" +
                        "------------------------------------------------------------------------------%n",
                        this.nombre, this.kilosProduccion, this.kilosTotalesProducidos,
                        this.almacen.getOperacionesDia(), this.almacen.getKilosTrigoExistentes());
                sleep(this.tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
