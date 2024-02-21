package u5w3d3.demo3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import u5w3d3.demo3.adapter.Info;
import u5w3d3.demo3.adapter.InfoAdapter;
import u5w3d3.demo3.adapter.UserData;
import u5w3d3.demo3.composite.Libro;
import u5w3d3.demo3.composite.Pagina;
import u5w3d3.demo3.composite.Sezione;

import java.util.Date;

@Component
public class MyRunner implements CommandLineRunner {
//    @Autowired
//    private InfoAdapter adapter;

    @Override
    public void run(String... args) throws Exception {
        UserData userData=new UserData("pippo pluto",28);
        Info info=new Info("pippo","pluto",new Date(1996-1-1));
        InfoAdapter adapter=new InfoAdapter(info);
        String nomeCompleto= adapter.getnomeCompleto();
        int eta=adapter.getEta();
        System.out.println(nomeCompleto);
        System.out.println(eta);

        Pagina pagina1=new Pagina();
        Pagina pagina2=new Pagina();
        Pagina pagina3=new Pagina();
        Pagina pagina4=new Pagina();
        Pagina pagina5=new Pagina();
        Pagina pagina6=new Pagina();
        Sezione sezione1=new Sezione();
        sezione1.aggiungiElemento(pagina1);
        sezione1.aggiungiElemento(pagina2);
        sezione1.aggiungiElemento(pagina3);
        Libro libro1=new Libro(12.30);
        libro1.aggiungiSezione(sezione1);
        System.out.println(libro1.totpagine());
    }
}
