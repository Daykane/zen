package com.zen.servlets;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.zen.beans.Product;
import com.zen.dao.AuthentificationException;
import com.zen.dao.DAOAuthen;
import com.zen.dao.DAOFactory;
import com.zen.dao.ProductDao;

@Path("/Products")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ProductsServlet {
	@Context
	private UriInfo context;
	//private UtilisateurDao  utilisateurDao;
	private ProductDao  productDao;

	
	@GET
    public List<Product> getAll() {
		this.productDao = DAOFactory.getInstance().getProductDao();
		List<Product> products = this.productDao.findAll();
		return products;
    }
	
	@GET
	@Path("{id}")
	public Product getOneJSON(@PathParam("id") String id) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		Product product = this.productDao.find(id);
		return product;
	}
	
	@POST
	public Response create(Product product,@HeaderParam("token") String token){
		
		DAOAuthen authen = new DAOAuthen();
		String idU;

		
		try {
			idU = Integer.toString(authen.authenToken(token));
		} catch (AuthentificationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
		if(product.getPrice()<0){
			return Response.status(400).entity("{\"PriceError\": \"true\"}").build();
		}
		if(product.getAvailableQuantity()<0){
			return Response.status(400).entity("{\"QuantityError\": \"true\"}").build();
		}
		this.productDao = DAOFactory.getInstance().getProductDao();
		this.productDao.create(product);
		return Response.status(201).build();
	}
		
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String id) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		this.productDao.delete(id);
		return Response.status(204).build();
	}
	
	
	@PUT
	@Path("{id}")
	public void update(Product product,@PathParam("id") String id) {
		this.productDao = DAOFactory.getInstance().getProductDao();
		int idInt = Integer.parseInt(id);
		this.productDao.update(idInt,product);
	}
	
}
