package padroescomportamentais.integracao;

public abstract class PedidoEstado {

    public abstract String getEstado();

    public boolean preparar(Pedido pedido) {
        return false;
    }

    public boolean finalizar(Pedido pedido) {
        return false;
    }

    public boolean entregar(Pedido pedido) {
        return false;
    }

    public boolean cancelar(Pedido pedido) {
        return false;
    }

}
