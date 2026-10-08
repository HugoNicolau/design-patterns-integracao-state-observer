package padroescomportamentais.integracao;

import java.util.Observable;
import java.util.Observer;

public class PainelCozinha implements Observer {

    private String ultimaAtualizacao;

    public String getUltimaAtualizacao() {
        return this.ultimaAtualizacao;
    }

    public void monitorar(Pedido pedido) {
        pedido.addObserver(this);
    }

    public void update(Observable pedido, Object arg1) {
        Pedido p = (Pedido) pedido;
        this.ultimaAtualizacao = "Pedido #" + p.getNumero() + " - " + p.getNomeEstado();
    }
}
