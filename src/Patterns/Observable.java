package Patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Observable {
    List<Observateur> observateurs;

    public Observable() {
        observateurs = new ArrayList<Observateur>();
    }

    public void ajouteObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void miseAJour() {
        ListIterator<Observateur> it = observateurs.listIterator();
        while (it.hasNext()) {
            it.next().metAJour();
        }
    }
}
