package padroescomportamentais.integracao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    // Observer

    @Test
    void deveNotificarUmCliente() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        pedido.preparar();
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClientes() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente1 = new Cliente("Maria");
        Cliente cliente2 = new Cliente("João");
        cliente1.acompanhar(pedido);
        cliente2.acompanhar(pedido);
        pedido.preparar();
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente1.getUltimaNotificacao());
        assertEquals("João, seu pedido #42 está Em preparo", cliente2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarCliente() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        pedido.preparar();
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarApenasClienteDoPedido() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        pedido1.setNumero(42);
        pedido2.setNumero(43);
        Cliente cliente1 = new Cliente("Maria");
        Cliente cliente2 = new Cliente("João");
        cliente1.acompanhar(pedido1);
        cliente2.acompanhar(pedido2);
        pedido1.preparar();
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente1.getUltimaNotificacao());
        assertEquals(null, cliente2.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteACadaMudancaDeEstado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        pedido.preparar();
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente.getUltimaNotificacao());
        pedido.finalizar();
        assertEquals("Maria, seu pedido #42 está Pronto", cliente.getUltimaNotificacao());
        pedido.entregar();
        assertEquals("Maria, seu pedido #42 está Entregue", cliente.getUltimaNotificacao());
    }

    // State + Observer: Pedido recebido

    @Test
    void deveNotificarClienteAoPrepararPedidoRecebido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertTrue(pedido.preparar());
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoFinalizarPedidoRecebido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.finalizar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoEntregarPedidoRecebido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.entregar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoCancelarPedidoRecebido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoRecebido.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertTrue(pedido.cancelar());
        assertEquals("Maria, seu pedido #42 está Cancelado", cliente.getUltimaNotificacao());
    }

    // State + Observer: Pedido em preparo

    @Test
    void naoDeveNotificarClienteAoPrepararPedidoEmPreparo() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.preparar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoFinalizarPedidoEmPreparo() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertTrue(pedido.finalizar());
        assertEquals("Maria, seu pedido #42 está Pronto", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoEntregarPedidoEmPreparo() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.entregar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoCancelarPedidoEmPreparo() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEmPreparo.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertTrue(pedido.cancelar());
        assertEquals("Maria, seu pedido #42 está Cancelado", cliente.getUltimaNotificacao());
    }

    // State + Observer: Pedido pronto

    @Test
    void naoDeveNotificarClienteAoPrepararPedidoPronto() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.preparar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoFinalizarPedidoPronto() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.finalizar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoEntregarPedidoPronto() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertTrue(pedido.entregar());
        assertEquals("Maria, seu pedido #42 está Entregue", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoCancelarPedidoPronto() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoPronto.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.cancelar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    // State + Observer: Pedido entregue

    @Test
    void naoDeveNotificarClienteAoPrepararPedidoEntregue() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.preparar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoFinalizarPedidoEntregue() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.finalizar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoEntregarPedidoEntregue() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.entregar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoCancelarPedidoEntregue() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoEntregue.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.cancelar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    // State + Observer: Pedido cancelado

    @Test
    void naoDeveNotificarClienteAoPrepararPedidoCancelado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.preparar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoFinalizarPedidoCancelado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.finalizar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoEntregarPedidoCancelado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.entregar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteAoCancelarPedidoCancelado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        pedido.setEstado(PedidoEstadoCancelado.getInstance());
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.cancelar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }
}
