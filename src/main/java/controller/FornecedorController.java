package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.entity.Fornecedor;
import service.FornecedorService;

@Path("/fornecedor")
public class FornecedorController {

	FornecedorService service = new FornecedorService();

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
