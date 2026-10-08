package padroescomportamentais.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
    }

    // Pedido recebido

    @Test
    public void devePrepararPedidoRecebido() {
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        assertTrue(pedido.preparar());
        assertEquals(PedidoEstadoEmPreparo.getInstance(), pedido.getEstado());
    }

    @Test
    public void naoDeveFinalizarPedidoRecebido() {
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        assertFalse(pedido.finalizar());
    }

    @Test
    public void naoDeveEntregarPedidoRecebido() {
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    public void deveCancelarPedidoRecebido() {
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
    }

    // Pedido em preparo

    @Test
    public void naoDevePrepararPedidoEmPreparo() {
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        assertFalse(pedido.preparar());
    }

    @Test
    public void deveFinalizarPedidoEmPreparo() {
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        assertTrue(pedido.finalizar());
        assertEquals(PedidoEstadoPronto.getInstance(), pedido.getEstado());
    }

    @Test
    public void naoDeveEntregarPedidoEmPreparo() {
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    public void deveCancelarPedidoEmPreparo() {
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        assertTrue(pedido.cancelar());
        assertEquals(PedidoEstadoCancelado.getInstance(), pedido.getEstado());
    }

    // Pedido pronto

    @Test
    public void naoDevePrepararPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.preparar());
    }

    @Test
    public void naoDeveFinalizarPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.finalizar());
    }

    @Test
    public void deveEntregarPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertTrue(pedido.entregar());
        assertEquals(PedidoEstadoEntregue.getInstance(), pedido.getEstado());
    }

    @Test
    public void naoDeveCancelarPedidoPronto() {
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        assertFalse(pedido.cancelar());
    }

    // Pedido entregue

    @Test
    public void naoDevePrepararPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.preparar());
    }

    @Test
    public void naoDeveFinalizarPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.finalizar());
    }

    @Test
    public void naoDeveEntregarPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    public void naoDeveCancelarPedidoEntregue() {
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        assertFalse(pedido.cancelar());
    }

    // Pedido cancelado

    @Test
    public void naoDevePrepararPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.preparar());
    }

    @Test
    public void naoDeveFinalizarPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.finalizar());
    }

    @Test
    public void naoDeveEntregarPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.entregar());
    }

    @Test
    public void naoDeveCancelarPedidoCancelado() {
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        assertFalse(pedido.cancelar());
    }

    // Estado inicial

    @Test
    public void deveIniciarPedidoComoRecebido() {
        assertEquals(PedidoEstadoRecebido.getInstance(), pedido.getEstado());
        assertEquals("Recebido", pedido.getNomeEstado());
    }
}
