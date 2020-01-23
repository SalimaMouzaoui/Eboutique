package dz.elit.eboutique;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dz.elit.eboutique.entities.Categorie;
import dz.elit.eboutique.entities.Produit;
import dz.elit.eboutique.metier.IAdminCategoriesMetier;

public class TestJPA {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new 
			String[]{"applicationContext.xml"});
	@Before
	public void SetUP() throws Exception{
		
	}
	@Test
	public void test1() {
		try{
			IAdminCategoriesMetier metier = 
					(IAdminCategoriesMetier) context.getBean("metier");
			List<Categorie> cts1 = metier.listCategories();
			metier.ajouterCategorie(new Categorie("Ordinateur", "Ordnnnnnnnn", null, "image1.jpg"));
			metier.ajouterCategorie(new Categorie("Imprimante", "Imprrrrrrrr", null, "image2.jpg"));
			List<Categorie> cts2 = metier.listCategories();
			assertTrue(cts1.size()+2 == cts2.size());
		} catch (Exception e){
			assertTrue(e.getMessage(), false);
		}
	}

	@Test
	public void test2() {
		try{
			IAdminCategoriesMetier metier = 
					(IAdminCategoriesMetier) context.getBean("metier");
			List<Produit> prod1 = metier.listproduits();
			metier.ajouterProduit(new Produit
					("HP14fg", "HP1425", 60000, true, "image1.jpg", 50), 1L);
			metier.ajouterProduit(new Produit
					("DELL578", "DELL1425", 60000, true, "image2.jpg", 50), 1L);List<Categorie> cts2 = metier.listCategories();
			List<Produit> prod2 = metier.listproduits();
			assertTrue(prod1.size()+2 == prod2.size());
		} catch (Exception e){
			assertTrue(e.getMessage(), false);
		}
	}
//	public void test() {
//		try{
//		ClassPathXmlApplicationContext app =
//		new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
//		assertTrue(true);
//		} catch(Exception e) {
//		assertTrue(e.getMessage(),false);
//		}
//	}
}
