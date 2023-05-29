package org.example;

public class Almacen {
    private int kilosTrigoExistentes;
    private int operacionesDia;
    private boolean abierto;
    private int maximoOperaciones;
    private int minimoTrigo;
    private int maximoTrigo;

    public Almacen(int kilosTrigoExistentes, int operacionesDia, boolean abierto, int maximoOperaciones, int minimoTrigo, int maximoTrigo) {
        this.kilosTrigoExistentes = kilosTrigoExistentes;
        this.operacionesDia = operacionesDia;
        this.abierto = abierto;
        this.maximoOperaciones = maximoOperaciones;
        this.minimoTrigo = minimoTrigo;
        this.maximoTrigo = maximoTrigo;
    }

    public synchronized int getKilosTrigoExistentes() {
        return kilosTrigoExistentes;
    }

    public synchronized void validarNumeroOperaciones() {
        if(getOperacionesDia() == getMaximoOperaciones()) {
            setAbierto(false);
            notifyAll();
        }
    }

    public synchronized void producirKilosTrigo(int kilosTrigoAgregados) throws InterruptedException {
        if((getKilosTrigoExistentes() + kilosTrigoAgregados) > getMaximoTrigo()) {
            wait();
        } else {
            notify();
            this.kilosTrigoExistentes += kilosTrigoAgregados;
            agregarOperacion();
            validarNumeroOperaciones();
        }
    }

    public synchronized void venderKilosTrigo(int kilosTrigoVendidos) throws InterruptedException {
        if(getKilosTrigoExistentes() <= getMinimoTrigo()) {
            wait();
        } else {
            notify();
            this.kilosTrigoExistentes -= kilosTrigoVendidos;
            agregarOperacion();
            validarNumeroOperaciones();
        }
    }

    public synchronized int getOperacionesDia() {
        return operacionesDia;
    }

    public synchronized void agregarOperacion() {
        this.operacionesDia += 1;
    }

    public synchronized boolean isAbierto() {
        return abierto;
    }

    public synchronized void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public synchronized int getMaximoOperaciones() {
        return maximoOperaciones;
    }

    public synchronized int getMinimoTrigo() {
        return minimoTrigo;
    }

    public synchronized int getMaximoTrigo() {
        return maximoTrigo;
    }
}
