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
}
