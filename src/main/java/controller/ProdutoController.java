package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.entity.Produto;
import service.ProdutoService;

@Path("/produto")
public class ProdutoController {
	
	ProdutoService service = new ProdutoService();
	
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
