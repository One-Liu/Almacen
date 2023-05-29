package org.example;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen(0,0,true,5000,5,1000);
        Productor productor1 = new Productor("Productor1", almacen);
        Productor productor2 = new Productor("Productor2", almacen);
        Consumidor consumidor1 = new Consumidor("Consumidor1", almacen);
        Consumidor consumidor2 = new Consumidor("Consumidor2", almacen);
        Consumidor consumidor3 = new Consumidor("Consumidor3", almacen);

        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();

        try {
            productor1.join();
            productor2.join();
            consumidor1.join();
            consumidor2.join();
            consumidor3.join();
            System.out.println("El almacén ha cerrado.");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}