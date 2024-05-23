package controller;

import java.util.ArrayList;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Corredor;
import service.CorredorService;

@Path("/corredor")
public class CorredorController {
	
	CorredorService corredorService = new CorredorService();
	
	@Path("/todos")
	@GET
	public ArrayList<Corredor> consultarTodos() {
		
		return corredorService.consultarTodos();
		
	}
	
	@GET
	@Path("/{id}")
	public Corredor consultarPorId(@PathParam("id")int id) {
		
		return corredorService.consultarPorId(id);
		
	}
	

}
