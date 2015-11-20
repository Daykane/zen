package com.zen.servlets;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.Category;
import com.zen.beans.Product;
import com.zen.dao.AuthentificationException;
import com.zen.dao.CategoryDao;
import com.zen.dao.DAOAuthen;
import com.zen.dao.DAOFactory;
import com.zen.dao.ProductDao;

@Path("/Categories")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CategoryServlet {
	@Context
	private UriInfo context;
	//private UtilisateurDao  utilisateurDao;
	private CategoryDao  categoryDao;
	private ProductDao  productDao;

	
	@GET
    public Object getAll() {
		// ========== A suuprimer car recuperation id signature dans la requete ======
		  String req = "";
		  byte[] output = null;
	        
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			output = md.digest();
	      
	        
	         req = "GET /api/Categories:htss0fia8213a8bk3d70nq20sa";
	         try {
				md.update(req.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
	      	 output = md.digest();
	      	System.out.println(" Code Sha1  : "+bytesToHex(output));
	      	
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String id = "1";
		String signature = bytesToHex(output);
		
		// =======================================================
        
		String reqForSignature ="GET /api/Categories";		
		DAOAuthen authen = new DAOAuthen();
		try {
			authen.authentification(id, reqForSignature, signature);
		} catch (AuthentificationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		this.categoryDao = DAOFactory.getInstance().getCategoryDao();
		List<Category> category = this.categoryDao.findAll();
		return category;
    }
	/* ok
	@GET 
	public void create(){
		Product product = new Product("productNameServlet", "productDescrServlet", 0, 0.0);
		this.productDao = DAOFactory.getInstance().getProductDao();
		this.productDao.create(product);
	}
	*/
	
	@GET
	@Path("{id}")
	public Category getOneJSON(@PathParam("id") String id) {
		this.categoryDao = DAOFactory.getInstance().getCategoryDao();
		Category category = this.categoryDao.find(id);
		return category;
	}
	
	@GET
	@Path("{id}/Products")
	public List<Product> getProductCategories(@PathParam("id") String id) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		List<Product>  products = this.productDao.findByCategory(id);

		return products;
	}
	
	@GET
	@Path("{id}/Products/{idP}")
	public Product getProductCategoriesId(@PathParam("id") String id,@PathParam("idP") String idP) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		Product  product = this.productDao.findByCategoryById(id,idP);

		return product;
	}
	
	public static String bytesToHex(byte[] b) {
	      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                         '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	      StringBuffer buf = new StringBuffer();
	      for (int j=0; j<b.length; j++) {
	         buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	         buf.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buf.toString();
	   }
	
}
