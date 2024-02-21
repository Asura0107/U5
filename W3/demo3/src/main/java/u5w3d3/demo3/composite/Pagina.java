package u5w3d3.demo3.composite;

public class Pagina extends Elemento{
    private int pagina;

    public Pagina() {
        this.pagina = 1;
    }

    @Override
    int numeroPagine() {
        return pagina;
    }
}
