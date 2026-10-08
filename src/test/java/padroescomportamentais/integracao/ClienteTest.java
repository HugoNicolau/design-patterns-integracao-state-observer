package padroescomportamentais.integracao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveNotificarClienteAoPrepararPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        pedido.preparar();
        assertEquals("Maria, seu pedido #42 está Em preparo", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoFinalizarPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        pedido.preparar();
        pedido.finalizar();
        assertEquals("Maria, seu pedido #42 está Pronto", cliente.getUltimaNotificacao());
    }

    @Test
    void deveNotificarClienteAoCancelarPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        pedido.cancelar();
        assertEquals("Maria, seu pedido #42 está Cancelado", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteEmTransicaoInvalida() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        Cliente cliente = new Cliente("Maria");
        cliente.acompanhar(pedido);
        assertFalse(pedido.entregar());
        assertEquals(null, cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarClienteQueNaoAcompanhaPedido() {
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
}
