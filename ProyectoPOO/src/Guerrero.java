
public class Guerrero extends Personaje {

  
    public Guerrero(String n, int v, int a, int d) {
        super(n, v, a, d);
    }

    public void usarEstrategia() {
        this.ataque = this.ataqueBase * 2;
        System.out.println(this.getNombre() + " usa ¡Furia! Su ataque se duplica a " + this.ataque);
    }
}