package padroescomportamentais.integracao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PainelCozinhaTest {

    @Test
    void deveAtualizarPainelAoPrepararPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        PainelCozinha painel = new PainelCozinha();
        painel.monitorar(pedido);
        pedido.preparar();
        assertEquals("Pedido #42 - Em preparo", painel.getUltimaAtualizacao());
    }

    @Test
    void deveNotificarPainelEClienteDoMesmoPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        PainelCozinha painel = new PainelCozinha();
        Cliente cliente = new Cliente("Maria");
        painel.monitorar(pedido);
        cliente.acompanhar(pedido);
        pedido.preparar();
        pedido.finalizar();
        assertEquals("Pedido #42 - Pronto", painel.getUltimaAtualizacao());
        assertEquals("Maria, seu pedido #42 está Pronto", cliente.getUltimaNotificacao());
    }

    @Test
    void naoDeveAtualizarPainelEmTransicaoInvalida() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        PainelCozinha painel = new PainelCozinha();
        painel.monitorar(pedido);
        assertFalse(pedido.finalizar());
        assertEquals(null, painel.getUltimaAtualizacao());
    }

    @Test
    void naoDeveAtualizarPainelDePedidoNaoMonitorado() {
        Pedido pedido = new Pedido();
        pedido.setNumero(42);
        PainelCozinha painel = new PainelCozinha();
        pedido.preparar();
        assertEquals(null, painel.getUltimaAtualizacao());
    }

    @Test
    void deveAtualizarApenasPainelDoPedido() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        pedido1.setNumero(42);
        pedido2.setNumero(43);
        PainelCozinha painel1 = new PainelCozinha();
        PainelCozinha painel2 = new PainelCozinha();
        painel1.monitorar(pedido1);
        painel2.monitorar(pedido2);
        pedido1.preparar();
        assertEquals("Pedido #42 - Em preparo", painel1.getUltimaAtualizacao());
        assertEquals(null, painel2.getUltimaAtualizacao());
    }
}
