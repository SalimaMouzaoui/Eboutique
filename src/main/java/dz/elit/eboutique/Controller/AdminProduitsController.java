package dz.elit.eboutique.Controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dz.elit.eboutique.entities.Categorie;
import dz.elit.eboutique.entities.Produit;
import dz.elit.eboutique.metier.IAdminProduitsMetier;

@Controller
@RequestMapping(value="/adminProd")
public class AdminProduitsController {
	
	@Autowired
	private IAdminProduitsMetier metier;
	
	@RequestMapping(value="/index")
	public String index(Model model){
		
		model.addAttribute("produit", new Produit());
		model.addAttribute("produits", metier.listproduits());
		model.addAttribute("categories", metier.listCategories());
		return "produits";
	}

	@RequestMapping(value = "/saveProd")
	public String saveProd(@ModelAttribute("produit") @Valid Produit p, BindingResult bindingResult, 
			Model model, @RequestParam("file") MultipartFile file) throws IOException{

		if(bindingResult.hasErrors()){
			model.addAttribute("produits", metier.listproduits());
            model.addAttribute("categories", metier.listCategories());
			return ("produits");
		}
		
		if (!file.isEmpty()){		
			String path = System.getProperty("java.io.tmpdir");
			p.setPhoto(file.getOriginalFilename());
			Long idP = null;
			if (p.getIdProduit() == null){
				idP = metier.ajouterProduit(p, p.getCategorie().getIdCategorie());
			} else {
				metier.modifierProduit(p);
				idP = p.getIdProduit();
			}
		
			file.transferTo(new File(path+"/"+"PROD_"+idP+"_"+file.getOriginalFilename()));
		} else {
			if (p.getIdProduit() == null)
				metier.ajouterProduit(p, p.getCategorie().getIdCategorie());
			else metier.modifierProduit(p);
		}
				 	
		model.addAttribute("produit", new Produit());
		model.addAttribute("produits", metier.listproduits());
        model.addAttribute("categories", metier.listCategories());
		return "produits";
		
	}
	
	// une méthode de l'affichage de l'image
		@RequestMapping(value = "photoProd", produces=MediaType.IMAGE_JPEG_VALUE)
		@ResponseBody
		public byte[] photoProd(Long idProd) throws IOException{
		//	System.out.println("here");
			Produit p = metier.getProduit(idProd);
		//	System.out.println("second");
			File f = new File(System.getProperty("java.io.tmpdir")+"/PROD_"+idProd+"_"+p.getPhoto());
		//	System.out.println("f = "+ f);
			return IOUtils.toByteArray(new FileInputStream(f));
	}
	
		@RequestMapping(value = "/suppProd")
		public String suppCat(Long idProd, Model model){

			metier.supprimerProduit(idProd);
			model.addAttribute("produit", new Produit());
			model.addAttribute("produits", metier.listproduits());
	        model.addAttribute("categories", metier.listCategories());
	        return "produits";
			
	}
		
		@RequestMapping(value = "/editProd")
		public String editCat(Long idProd, Model model){

			Produit p = metier.getProduit(idProd);
			model.addAttribute("produit", p);
			model.addAttribute("produits", metier.listproduits());
	        model.addAttribute("categories", metier.listCategories());
	        return "produits";
			
	}
}
