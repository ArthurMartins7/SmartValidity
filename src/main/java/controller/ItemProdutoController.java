package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.entity.ItemProduto;
import service.ItemProdutoService;

@Path("/itemProduto")
public class ItemProdutoController {

	ItemProdutoService service = new ItemProdutoService();

	@GET
	@Path("/todos")
	public List<ItemProduto> consultarTodos() {
		return service.consultarTodos();

	}

	@GET
	@Path("/{id}")
	public ItemProduto consultarPorId(@PathParam("id") int id) {
		return service.consultarTodos(id);

	}

}
