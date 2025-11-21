
import java.util.Random;
import java.util.ArrayList;

public class Mago extends Personaje {

    
    public Mago(String n, int v, int a, int d) {
        super(n, v, a, d);
    }

  

    public void usarEstrategia() {
        Random rand = new Random();
        
       
        ArrayList<Personaje> aliados = this.miEquipo.getPersonajes();
        
        Personaje aliadoACurar = null;
        while(aliadoACurar == null) {
            int indice = rand.nextInt(aliados.size());
            if (aliados.get(indice).estaVivo()) {
                aliadoACurar = aliados.get(indice);
            }
        }
        int curacion = (int)(this.vida * 0.25);
        
        System.out.println(this.getNombre() + " usa ¡Curación! (" + curacion + " pts)");
        aliadoACurar.curar(curacion);
        aliadoACurar = null;
    }
}