package com.zen.servlets;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.zen.dao.DAOUtilitaire.*;
import com.zen.beans.Category;
import com.zen.beans.Product;
import com.zen.dao.AuthentificationException;
import com.zen.dao.CategoryDao;
import com.zen.dao.DAOAuthen;
import com.zen.dao.DAOException;
import com.zen.dao.DAOFactory;
import com.zen.dao.ProductDao;

@Path("/Categories")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CategoryServlet {
	@Context
	private UriInfo context;
	private CategoryDao  categoryDao;
	private ProductDao  productDao;

	
	@GET
    public Object getAll() {
		
		
		// ========== A suuprimer car recuperation id signature dans la requete ======
	    /*
		String req = "GET /api/Categories:htss0fia8213a8bk3d70nq20sa";	    
	    String signature = Sha1(req);
	    String id = "1";
		*/
		// =======================================================
        /*
		String reqForSignature ="GET /api/Categories";
				
		DAOAuthen authen = new DAOAuthen();
		try {
			authen.authentification(id, reqForSignature, signature);
		} catch (AuthentificationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		*/
		//System.out.println("token dans le header :" + token);
		//DAOAuthen authen = new DAOAuthen();
		//token = "lorml4glpd0pshakk2es95j7pc";
		/*
		int id = 0;
		try {
			id = authen.authenToken(token);
		} catch (AuthentificationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		*/
		this.categoryDao = DAOFactory.getInstance().getCategoryDao();
		List<Category> category = this.categoryDao.findAll();
		return category;
    }

	@POST 
	public Response create(Category category){
		if (category == null){
			return Response.status(400).entity("error in json format").build();
		}
		Date validationDate = new Date();
		category = new Category("categoryName", "categogyDesc", validationDate);
		this.categoryDao = DAOFactory.getInstance().getCategoryDao();
		try {
			this.categoryDao.create(category);
			}
		catch (DAOException e) {
			return Response.status(500).build();
		}
		return Response.status(201).build();	
	}

	
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
	
	@DELETE
	@Path("{id}")
	public Response deleteCategory(@PathParam("id") String id){
		this.categoryDao = DAOFactory.getInstance().getCategoryDao();
		Category category = this.categoryDao.find(id);		
		try {
			this.categoryDao.delete(category);
			}
		catch (DAOException e) {
			return Response.status(500).build();
		}
		return Response.status(204).build();
		
	}

	
}
