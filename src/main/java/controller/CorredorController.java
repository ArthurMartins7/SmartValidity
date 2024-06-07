package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Corredor;
import service.CorredorService;

@Path("/corredor")
public class CorredorController {

	CorredorService corredorService = new CorredorService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Corredor salvar(Corredor corredor) {
		return corredorService.salvar(corredor);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean excluir (@PathParam("id") int id) {
		return corredorService.excluir(id);
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Corredor c) {
		return corredorService.alterar(c);
	}

	@Path("/todos")
	@GET
	public ArrayList<Corredor> consultarTodos() {

		return corredorService.consultarTodos();

	}

	@GET
	@Path("/{id}")
	public Corredor consultarPorId(@PathParam("id") int id) {

		return corredorService.consultarPorId(id);

	}

}
