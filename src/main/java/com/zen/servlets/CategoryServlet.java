package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.Category;
import com.zen.beans.Product;
import com.zen.dao.CategoryDao;
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
    public List<Category> getAll() {

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
	/*
	@GET ok
	@Path("{id}")
	public void delete(@PathParam("id") String id) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		Product product = this.productDao.find(id);
		this.productDao.delete(product);
	}
	*/
	/*
	@GET ok
	@Path("{id}")
	public void update(@PathParam("id") String id) {
		Product product = new Product("productNameServletUpdate", "productDescrServletUpdate", 0, 0.0);
		this.productDao = DAOFactory.getInstance().getProductDao();
		int idInt = Integer.parseInt(id);
		this.productDao.update(idInt,product);
	}
	*/
}
