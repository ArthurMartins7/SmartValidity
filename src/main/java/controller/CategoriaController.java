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
import model.entity.Categoria;
import service.CategoriaService;

@Path("/categoria")
public class CategoriaController {

	CategoriaService service = new CategoriaService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria salvar(Categoria c) {
		return service.salvar(c);
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
	public boolean alterar(Categoria c) {
		return service.alterar(c);
	}

	@GET
	@Path("/todas")
	public List<Categoria> consultarTodos() {
		return service.consultarTodas();

	}

	@GET
	@Path("/{id}")
	public Categoria consultarPorId(@PathParam("id") int id) {
		return service.ConsultarPorId(id);
	}

}
