package dz.elit.eboutique.metier;

import dz.elit.eboutique.entities.Categorie;
import dz.elit.eboutique.entities.Role;
import dz.elit.eboutique.entities.User;

public interface IAdminCategoriesMetier extends IAdminProduitsMetier{
	
	public Long ajouterCategorie(Categorie c);
	public void supprimerCategorie(Long idCat);
	public void modifierCategorie(Categorie c);
	public void ajouterUser(User u);
	public void attributeRole(Role r, Long userID);

}
