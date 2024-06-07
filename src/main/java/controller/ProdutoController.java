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
import model.entity.Produto;
import service.ProdutoService;

@Path("/produto")
public class ProdutoController {

	ProdutoService service = new ProdutoService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto salvar(Produto p) {
		return service.salvar(p);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Produto p) {
		return service.alterar(p);
	}

	@GET
	@Path("/todos")
	public List<Produto> consultarTodos() {
		return service.consultarTodos();
	}

	@GET
	@Path("{id}")
	public Produto consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}

}
