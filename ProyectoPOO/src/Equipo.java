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
        System.out.println("===================================\n\n===================================");
        System.out.println("Equipo atacante: " + this.nombre);
        
        int danoTotalRonda = 0;
        ArrayList<Personaje> enemigos = otroEquipo.getPersonajes();

        for (int i = 0; i < 4; i++) {
            Personaje atacante = this.personajes.get(i);
            Personaje enemigo = enemigos.get(i);

            if (atacante.estaVivo() && enemigo.estaVivo()) {
                if (i == 3) {
                    danoTotalRonda += ataqueMistico(atacante, enemigo, sc, danoTotalRonda);
                } else {
                    int danoHecho = atacante.realizarAtaque(enemigo);
                    danoTotalRonda += danoHecho;
                    
                }
                
            } else { // Para que deje de atacar enemigos muertos
                if (!atacante.estaVivo()) {
                    System.out.println(atacante.getNombre() + " esta muerto!!!");
                } else { // Si el enemigo esta muerto
                    int c = 0;
                    for (int n = 0; n < 4; n++) { // Que busque a un enemigo vivo
                        if (c == 3) {
                            break;
                        }
                        int enemigoMuertoQuizas = enemigos.get(n).getVida();
                        if (enemigoMuertoQuizas > 0) { // Si el enemigo nuevo esta vivo...
                            if (personajes.indexOf(atacante) == 3) {
                                danoTotalRonda += ataqueMistico(atacante, enemigos.get(n), sc, danoTotalRonda);
                                break;
                            } else {
                                int danoHecho = atacante.realizarAtaque(enemigos.get(n));
                                danoTotalRonda += danoHecho;
                                break;
                            }
                        } else {
                            c += 1;
                        }
                    }
                }
            }
        }
    }


    public int ataqueMistico(Personaje misticoAtaca, Personaje misticoEnemigo, Scanner sc, int danoTotalRonda) {
        misticoAtaca.usarEstrategia(); 

        System.out.print("¡Adivina el número del dado (1-6) para potenciar al Místico!: ");
        int adivinanza = sc.nextInt();
        int ataqueFinalMistico = misticoAtaca.getAtaque();

        if (misticoAtaca.estaVivo() && misticoEnemigo.estaVivo()) {
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