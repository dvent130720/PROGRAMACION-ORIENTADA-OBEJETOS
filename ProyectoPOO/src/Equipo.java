import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Equipo {
    Scanner sc = new Scanner(System.in);    
    private String nombre;
    private ArrayList<Personaje> personajes;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<Personaje>();
    }

    public void agregarPersonaje(Personaje p) {
        this.personajes.add(p);
        p.setEquipo(this);
    }

    public ArrayList<Personaje> getPersonajes() {
        return this.personajes;
    }

    
    public String getNombre() {
        return this.nombre;
    }
   
    public boolean estaDerrotado() {
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                return false; 
            }
        }
        return true; 
    }
    
 
    public int getSumaVida() {
        int suma = 0;
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                suma += p.getVida();
            }
        }
        return suma;
    }
    

    public void atacarOtroEquipo(Equipo otroEquipo) {
        System.out.println("===================================");
        System.out.println("Equipo atacante: " + this.nombre);
        
        int danoTotalRonda = 0;
        ArrayList<Personaje> enemigos = otroEquipo.getPersonajes();

        Personaje mistico = this.personajes.get(3);
        Personaje enemigoMistico = enemigos.get(3);

        for (int i = 0; i < 4; i++) {
            Personaje atacante = this.personajes.get(i);
            Personaje enemigo = enemigos.get(i);
            System.out.println(atacante.getNombre());
            System.out.println(enemigo.getNombre());

            if (atacante.estaVivo() && enemigo.estaVivo()) {
                if (i == 3) {
                    danoTotalRonda += ataqueMistico(mistico, enemigoMistico, sc, danoTotalRonda);
                } else {
                    int danoHecho = atacante.realizarAtaque(enemigo);
                    danoTotalRonda += danoHecho;
                    System.out.println(" ---------------->>> ENTRA AL IF");
                }
                
            } else { // Le añadí código desde aquí -- Para que deje de atacar enemigos muertos
                if (!atacante.estaVivo()) {
                    System.out.println(atacante.getNombre() + " esta muerto!!!");
                } else { // Si el enemigo esta muerto
                    for (int n = 0; n < 4; n++) {
                        int enemigoMuertoQuizas = enemigos.get(n).getVida();
                        if (enemigoMuertoQuizas > 0) {
                            if (n == 3) {
                                danoTotalRonda += ataqueMistico(mistico, enemigos.get(n), sc, danoTotalRonda);
                                break;
                            }
                            System.out.println(" --------->> ATACANTE: " + atacante.getNombre());
                            int danoHecho = atacante.realizarAtaque(enemigos.get(n));
                            danoTotalRonda += danoHecho;
                        }
                    }    
                }
            }
        }
    }


    public int ataqueMistico(Personaje misticoAtaca, Personaje misticoEnemigo, Scanner sc, int danoTotalRonda) {
    
        System.out.print("¡Adivina el número del dado (1-6) para potenciar al Místico!: ");
        int adivinanza = sc.nextInt();
        int ataqueFinalMistico = misticoAtaca.getAtaque();

        if (misticoAtaca.estaVivo() && misticoEnemigo.estaVivo()) {
            misticoAtaca.usarEstrategia(); 
        
            Random rand = new Random();
            int dado = rand.nextInt(6) + 1; 
                
            if (dado == adivinanza) {
                System.out.println("¡Acierto! El dado fue " + dado + ". ¡El ataque se potencia con " + danoTotalRonda + " de daño extra!");
                ataqueFinalMistico += danoTotalRonda;
            } else {
                System.out.println("¡Fallo! El dado fue " + dado + ". El Místico ataca con fuerza normal.");
            }
            // El místico ataca directamente
            System.out.println(misticoAtaca.getNombre() + " ataca a " + misticoEnemigo.getNombre());
            misticoEnemigo.recibirAtaque(ataqueFinalMistico);
        }
        return ataqueFinalMistico;
    }
    
}