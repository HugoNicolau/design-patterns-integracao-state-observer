package padroescomportamentais.integracao;

import java.util.Observable;

public class Pedido extends Observable {

    private int numero;
    private PedidoEstado estado;

    public Pedido() {
        this.estado = PedidoEstadoRecebido.getInstance();
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        setChanged();
        notifyObservers();
    }

    public boolean preparar() {
        return estado.preparar(this);
    }

    public boolean finalizar() {
        return estado.finalizar(this);
    }

    public boolean entregar() {
        return estado.entregar(this);
    }

    public boolean cancelar() {
        return estado.cancelar(this);
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public PedidoEstado getEstado() {
        return estado;
    }
}
