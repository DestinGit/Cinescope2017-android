package utilities.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import db.fr.cinescope2017.ImportBD;
import db.fr.cinescope2017.Inscription;
import db.fr.cinescope2017.Login;
import db.fr.cinescope2017.LoginActivity;
import db.fr.cinescope2017.MonCompte;
import db.fr.cinescope2017.R;

import static utilities.classes.Globale.*;

/**
 * Created by formation on 03/11/2017.
 */
public class MenuItemChoix {

    public static boolean menuItemChoix(Activity activite, int choix) {

        Intent intention;

        // Détermine quelle entrée a été sélectionnée.
        switch (choix) {
            case (R.id.itemConnect):
//                intention.setClass(this, Inscription.class);
                intention = new Intent(activite, Login.class);
//                intention.putExtra("motRecherche","mon mot");
                activite.startActivityForResult(intention, 8);
//                startActivityForResult(intention, 8);
                return true;
            // Aiguille
            case (R.id.itemMyAccount):
                String lsNom = getNom();
                if (lsNom.isEmpty()) {
                    Toast.makeText(activite, Globale.getNom(), Toast.LENGTH_LONG).show();
                } else {
                    intention = new Intent(activite, MonCompte.class);

                    intention.putExtra("id", Globale.getId());
                    intention.putExtra("nom", Globale.getNom());
                    intention.putExtra("mdp", Globale.getMdp());
                    intention.putExtra("email", Globale.getEmail());

                    activite.startActivityForResult(intention, 9);
                }
                return true;
            case (R.id.itemImportDB):
                intention = new Intent(activite, ImportBD.class);
                activite.startActivityForResult(intention, 10);
                return true;
            case (R.id.action_settings):
                Toast.makeText(activite, "Configuration", Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemAide):
                // Une activite(Aide) avec une WebView et l'aide en HTML/CSS
//                intention = new Intent(activite, Aide.class);
//                activite.startActivityForResult(intention, 1);
                String lsMsg = "Aide :\n Une activite(Aide) à créer avec une WebView et l'aide en HTML/CSS";
                Toast.makeText(activite,lsMsg,Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemAPropos):
                /*
                 * LA BOITE DE DIALOGUE
                 */
                // --- L'écouteur pour le clic
                // Le code peut être en dehors de la méthode utilisatrice
                DialogInterface.OnClickListener ecouteurClicBD = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int codeBouton) {

                    }
                };

                String lsTitre = "A propos";
                String lsMessage = "Android Contacts\nDécembre 2015\nVersion 0.9\nAuthor : PB & Co";
                String lsOK = "OK";

                AlertDialog.Builder ad = new AlertDialog.Builder(activite);
                ad.setTitle(lsTitre);
                ad.setMessage(lsMessage);
                ad.setNeutralButton(lsOK, ecouteurClicBD);
                ad.show();
                return true;

            default:
                // Renvoie false si les entrées n’ont pas été gérées.
                return false;
        }
    } /// menuItemChoix

}
