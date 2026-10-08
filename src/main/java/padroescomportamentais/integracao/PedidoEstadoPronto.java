package padroescomportamentais.integracao;

public class PedidoEstadoPronto extends PedidoEstado {

    private PedidoEstadoPronto() {};
    private static PedidoEstadoPronto instance = new PedidoEstadoPronto();
    public static PedidoEstadoPronto getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Pronto";
    }

    public boolean entregar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        return true;
    }

}
