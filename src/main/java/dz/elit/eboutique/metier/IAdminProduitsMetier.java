package dz.elit.eboutique.metier;

import dz.elit.eboutique.entities.Produit;

public interface IAdminProduitsMetier extends InternauteBoutiqueMetier{
	
	public Long ajouterProduit(Produit p, Long idCat);
	public void supprimerProduit(Long idP);
	public void modifierProduit(Produit p);

}
