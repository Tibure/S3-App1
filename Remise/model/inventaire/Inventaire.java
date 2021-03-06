package model.inventaire;

import model.ingredients.IngredientInventaire;
import model.ingredients.exceptions.IngredientException;

import java.util.ArrayList;

/**
 * Classe model.inventaire contenant une liste des ingrédients, caché par un iterator
 * @author beae0601 bure1301
 */
public class Inventaire implements IInventaire {
    /**
     *
     * Tableau dynamique des ingrédients
     */
    private ArrayList<IngredientInventaire> lesIngredients = new ArrayList<IngredientInventaire>();
    /**
     * Instance d'un singleton
     */
    private static Inventaire instance;


    /**
     * Constructeur de base de la classe
     */
    private Inventaire(){}

    /**
     * @return Création d'un instance afin de s'assurer qu'il y en a qu'une seule
     */
    public static Inventaire getInstance(){
        if(instance == null)
            instance = new Inventaire();

        return instance;
    }


    /**
     *
     * @return Un iterator sur les ingrédients
     */
    public IIterator createIterator(){
        InventaireIterator result = new InventaireIterator();
        return result;
    }

    /**
     *
     * @return Une String contenant tous les ingrédients
     */
    @Override
    public String toString() {
        String retour = "model.inventaire.Inventaire{ingredients=";

        if(lesIngredients.size() > 0){
            for(IngredientInventaire ingredientInventaire: lesIngredients){
                retour += ingredientInventaire.toString() + "\n";
            }
        }

        if(retour.endsWith("\n"))
            retour = retour.substring(0, retour.length()-1);

        retour += "}";

        return retour;
    }

    /**
     * Classe privée qui implémente l'interface IIterator. Permet d'itérer sur les ingrédients de l'model.inventaire, d'ajouter et de retirer des ingrédients
     */
    private class InventaireIterator implements IIterator
    {
        /**
         * Position courante de l'iterator
         */
        private int position;

        /**
         * Constructeur par défaut
         */
        public InventaireIterator(){
            position = 0;
        }

        /**
         *
         * @return Vrai s'il y a un prochain, faux sinon
         */
        @Override
        public boolean hasNext() {
            if(position < lesIngredients.size())
                return true;
            else
                return false;
        }

        /**
         * Deplace l'iterator sur l'élément suivant
         * @return L'élément courant
         */
        @Override
        public IngredientInventaire next(){
            if(this.hasNext())
                return lesIngredients.get(position++);
            else
                return null;
        }

        /**
         * Insère un élément à la fin
         * @param ingredient Élément à insérer
         * @return Vrai si ajout réussi, faux sinon
         */
        @Override
        public boolean insert(IngredientInventaire ingredient) throws IngredientException {
            int index = -1;
            for(int i = 0; i < lesIngredients.size(); i++){
                if(lesIngredients.get(i).getIngredient().getNom().equals(ingredient.getIngredient().getNom())){
                    index = i;
                    break;
                }
            }
            if(index != -1)
                lesIngredients.get(index).setQuantite(lesIngredients.get(index).getQuantite()+ingredient.getQuantite());
            else
                lesIngredients.add(ingredient);

            return true;
        }

        /**
         * Retire la quantité demandé de l'élément spécifié
         * @param nom Nom de l'ingrédient
         * @param quantite Quantité à retirer
         * @return Vrai si l'opération réussi, faux sinon
         * @throws IngredientException Lance une exception si la quantité est invalide
         */
        @Override
        public boolean remove(String nom, int quantite) throws IngredientException {
            boolean trouve = false;
            for(int i =  0; i < lesIngredients.size() && !trouve; i++)
            {
                if(lesIngredients.get(i).getIngredient().getNom().equals(nom))
                {
                    trouve = true;
                    double inventaire = lesIngredients.get(i).getQuantite();
                    if(inventaire > quantite && quantite >= 0)
                        lesIngredients.get(i).setQuantite(inventaire - quantite);
                    else if(inventaire == quantite)
                        lesIngredients.remove(i);
                    else
                        return false;
                }
            }
            return trouve;
        }

        public void setPosition(int position){
            this.position = position;
        }
    }
}
