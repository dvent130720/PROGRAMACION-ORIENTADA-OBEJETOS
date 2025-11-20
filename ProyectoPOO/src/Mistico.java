
public class Mistico extends Personaje {

  
    public Mistico(String n, int v, int a, int d) {
        super(n, v, a, d);
    }

   
    public void usarEstrategia() {

        System.out.println(this.getNombre() + " se concentra y prepara el ¡Ataque Místico!");
    }
}