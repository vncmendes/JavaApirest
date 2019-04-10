package br.edu.ifsul.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifsul.model.Evento;
import br.edu.ifsul.service.EventoService;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EventosResource {
	EventoService eventoService = new EventoService();
	
	@GET
	public List<Evento> getEventos(){
		return eventoService.getEventos();
	}
	
	@GET
	@Path("{id}")
	public Evento getEvento(@PathParam("id") long id) {
		return eventoService.getEvento(id);
	}

	@GET
	@Path("/name/{name}")
	public List<Evento> getByTipo(@PathParam("name") String name) {
		return eventoService.findByName(name);
	}

	@POST
	public String post(Evento evento) {
		if (eventoService.save(evento)) {
			return "salvo";
		}
		return "erro ao tentar salvar";
	}
	
	@PUT
	public String put(Evento evento) {
		if (eventoService.save(evento)) {
			return "salvo";
		}
		return "erro ao tentar salvar";
	}

	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") long id) {
		if (eventoService.delete(id)) {
			return "ok";
		}
		return "erro ao tentar excluir";
	}
}
