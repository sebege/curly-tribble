package projektoo;

/**
 * Kurz werden hier solche Methoden definiert, die das Model manipulieren.
 * 
 * Es wird unterschieden zwischen hardware input und ingame commands. Im View
 * wird definiert, welches Input welchem Kommando entsprechen soll. Der
 * Controller ist dann sozusagen die Kommandozentrale, in der implementiert ist,
 * wie ein gegebenes Kommando umgesetzt wird. Das soll den Vorteil haben, dass
 * gegebenenfalls beim Erstellen einer neuen View wieder lediglich Eingabetasten
 * mit Kommandos belegt werden müssen, und nicht nochmal in den Tiefen und
 * Verwinklungen des Programms herumgetaucht werden muss, um die richtige
 * Befehlsfolge wiederzufinden, was eine Fehlerquelle wäre. Dies muss
 * stattdessen lediglich ein Mal im Controller getan werden.
 * 
 * Wie es praktisch ist, darf hier Zeugs in andere Klassen ausgelagert werden.
 * Naheliegend zum Beispiel solche Methoden, die Instanzen der eigenen Klasse
 * betreffen. Dort verwischt sich dann die Grenze zwischen Controller und Model,
 * da die Instanzen einer solchen Klasse teil des Models sind, aber gleichzeitig
 * Methoden beinhalten, die das Modell variieren.
 * 
 * @author bsg
 *
 */
public class Controller {

	/*
	 * instance variables
	 */
	// ein Controller muss wisse, welches konrekte Modell er manipulieren soll,
	// darum braucht er hier eine solche Variable
	private Model model;

	/*
	 * contructor
	 */
	public Controller(Model model) {
		this.model = model;
	}

	/*
	 * methods
	 */

	/**
	 * diese methode implementiert veränderung über zeit im modell. idealerweise
	 * ruft sie eine untermethode für das spezielle objekt, das aktualisiert
	 * werden soll, sodass wir das ganze modular halten.
	 * 
	 * @param newTime
	 */
	public void updateModel(long newTime) {
		this.model.getHero().updateHero(newTime);

		// am ende wird der bezugspunkt des zeitpunktes an dem das letzte mal
		// geupdatet wurde geupdated
		this.model.setOldTime(newTime);
	}

	/**
	 * diese Methode beschreibt, wie das "jump"-Kommando ausgeführt werden soll
	 */
	public void jump() {
		System.out.println("jump()");
		this.model.getHero().triggerJump();
	}

	public void duck() {
		System.out.println("duck()");
		this.model.getHero().duck();
	}
	
	public void unduck() {
		System.out.println("unduck()");
		this.model.getHero().unduck();
	}
}
