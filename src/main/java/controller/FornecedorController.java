package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Fornecedor;
import service.FornecedorService;

@Path("/fornecedor")
public class FornecedorController {

	FornecedorService service = new FornecedorService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor salvar(Fornecedor f) {
		return service.salvar(f);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluir (@PathParam("id") int id) {
		return service.excluir(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Fornecedor f) {
		return service.alterar(f);
	}

	@GET
	@Path("/todos")
	public List<Fornecedor> consultarTodos() {
		return service.consultarTodos();
	}

	@GET
	@Path("/{id}")
	public Fornecedor consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}

}
