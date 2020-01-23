package dz.elit.eboutique.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dz.elit.eboutique.entities.*;

public class BoutiqueDAOImpl implements IBoutiqueDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long ajouterCategorie(Categorie c) {
		em.persist(c);
		return c.getIdCategorie();
	}

	@Override
	public List<Categorie> listCategories() {
		Query req = em.createQuery("select c from Categorie c");
		return req.getResultList();
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		
		return em.find(Categorie.class, idCat);
	}

	@Override
	public void supprimerCategorie(Long idCat) {
		Categorie cat = em.find(Categorie.class, idCat);
		em.remove(cat);
		
	}

	@Override
	public void modifierCategorie(Categorie c) {
		em.merge(c);
		
	}

	@Override
	public Long ajouterProduit(Produit p, Long idCat) {
		Categorie c = getCategorie(idCat);
		p.setCategorie(c);
		em.persist(p);
		return p.getIdProduit();
	}

	@Override
	public List<Produit> listproduits() {
		Query req = em.createQuery("select p from Produit p");
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsParMotCle(String mc) {
		Query req = em.createQuery("select p from Produit p where p.designation like :x or "
				+ "p.descreption like :x");
		req.setParameter("x", "%"+mc+"%"); // % veut dire qlq soit la position
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsParCategorie(Long idCat) {
		Query req = em.createQuery("select p from Produit p where "
				+ "p.Categorie.idCategorie :x");
		req.setParameter("x", "%"+idCat+"%"); // % veut dire qlq soit la position
		return req.getResultList();
	}

	@Override
	public List<Produit> produitsSelectionnes() {
		Query req = em.createQuery("select p from Produit p where "
				+ "p.selected = true");
		return req.getResultList();
	}

	@Override
	public Produit getProduit(Long idP) {
		// TODO Auto-generated method stub
		return em.find(Produit.class, idP);
	}

	@Override
	public void supprimerProduit(Long idP) {
		Produit p = getProduit(idP);
		em.remove(p);
		
	}

	@Override
	public void modifierProduit(Produit p) {
		em.merge(p);
		
	}

	@Override
	public void ajouterUser(User u) {
		em.persist(u);
		
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		User u = em.find(User.class, userID);
		u.getRoles().add(r);
		em.persist(r);
		
	}

	@Override
	public Commande enregistrerCommande(Panier p, Client c) {
		em.persist(c);
		Commande cmd = new Commande();
		cmd.setDateCommande(new Date());
		cmd.setItems(p.getItems());
		for (LigneCommande lc:p.getItems()){
			em.persist(lc);
		}
		em.persist(cmd);
		return cmd;
	}

}
