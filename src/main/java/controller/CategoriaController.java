package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.entity.Categoria;
import service.CategoriaService;

@Path("/categoria")
public class CategoriaController {
	
	CategoriaService service = new CategoriaService();
	
	
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
