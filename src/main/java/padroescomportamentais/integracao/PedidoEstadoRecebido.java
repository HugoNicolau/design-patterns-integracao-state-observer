package padroescomportamentais.integracao;

public class PedidoEstadoRecebido extends PedidoEstado {

    private PedidoEstadoRecebido() {};
    private static PedidoEstadoRecebido instance = new PedidoEstadoRecebido();
    public static PedidoEstadoRecebido getInstance() {
        return instance;
    }

    public String getEstado() {
        return "Recebido";
    }

    public boolean preparar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        return true;
    }

    public boolean cancelar(Pedido pedido) {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        return true;
    }

}
