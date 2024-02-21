package u5w3d3.demo3.adapter;

import java.util.Date;
public class InfoAdapter implements data{
    private Info info;

    public InfoAdapter(Info info) {
        this.info = info;
    }

    @Override
    public String getnomeCompleto() {
        String nomeCompleto= info.getNome()+" "+ info.getCongnome();
        return nomeCompleto;
    }

    @Override
    public int getEta() {
        Date date=new Date();
        int eta= date.getYear()-info.getDataNascita().getYear();
        return eta;
    }
}
