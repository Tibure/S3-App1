package inventaire;

import ingredients.Ingredient;
import ingredients.IngredientInventaire;
import ingredients.exceptions.IngredientException;

import java.util.ArrayList;

public class Inventaire implements IContainer{
    private static final int OK = 0;
    private static final int PAS_TROUVE = 1;
    private static final int PAS_ASSEZ = 2;

    private ArrayList<IngredientInventaire> lesIngredients = new ArrayList<IngredientInventaire>();

    public IIterator createIterator(){
        InventaireIterator result = new InventaireIterator();
        return result;
    }

    @Override
    public String toString() {
        String retour = "inventaire.Inventaire{ingredients=";

        if(lesIngredients.size() > 0){
            for(IngredientInventaire ingredientInventaire: lesIngredients){
                retour += ingredientInventaire.toString() + ", ";
            }
        }

        if(retour.endsWith(", "))
            retour = retour.substring(0, retour.length()-2);

        retour += "}";

        return retour;
    }

    private class InventaireIterator implements IIterator
    {
        private int position;

        public InventaireIterator(){
            position = 0;
        }
        @Override
        public boolean hasNext() {
            if(position < lesIngredients.size())
                return true;
            else
                return false;
        }
        @Override
        public IngredientInventaire next(){
            if(this.hasNext())
                return lesIngredients.get(position++);
            else
                return null;
        }

        @Override
        public boolean insert(IngredientInventaire ingredient) {
            return lesIngredients.add(ingredient);
        }

        @Override
        public boolean remove(String nom, int quantite) throws IngredientException {
            boolean trouve = false;
            for(int i =  0; i < lesIngredients.size() && !trouve; i++)
            {
                if(lesIngredients.get(i).getIngredient().getNom().equals(nom))
                {
                    trouve = true;
                    int inventaire = lesIngredients.get(i).getQuantite();
                    if(inventaire > quantite)
                        lesIngredients.get(i).setQuantite(inventaire - quantite);
                    else if(inventaire == quantite)
                        lesIngredients.remove(i);
                    else
                        return false;
                }
            }
            return trouve;
        }
    }
}
