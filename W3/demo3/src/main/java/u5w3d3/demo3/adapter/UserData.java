package u5w3d3.demo3.adapter;

public class UserData {
    private String nomeCompleto;
    private int eta;

    public UserData(String nomeCompleto, int eta) {
        this.nomeCompleto = nomeCompleto;
        this.eta = eta;
    }

    public void getData(data ds){
        nomeCompleto=ds.getnomeCompleto();
        eta=ds.getEta();
    }
}
