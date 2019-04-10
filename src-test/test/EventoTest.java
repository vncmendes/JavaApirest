package test;

import java.util.List;

import br.edu.ifsul.model.Evento;

import br.edu.ifsul.service.EventoService;
import junit.framework.TestCase;


public class EventoTest extends TestCase {
	private EventoService eventoService = new EventoService();

	public void testListaEventos() {
		List<Evento> eventos = eventoService.getEventos();
		assertNotNull(eventos);

		assertTrue(eventos.size() > 0);

		Evento skye = eventoService.findByName("Skye").get(0);
		assertEquals("Skye", skye.getName());

		Evento degrau = eventoService.findByName("Degrau").get(0);
		assertEquals("Degrau", degrau.getName());

		Evento jg = eventoService.findByName("Joao e Gilberto").get(0);
		assertEquals("Joao e Gilberto", jg.getName());
	}

	public void testSalvarDeletarevento() {
		Evento c = new Evento();
		c.setName("Teste");
		c.setDescription("Teste desc");
		c.setPreco(10);
		c.setUrlFoto("url foto aqui");
		
		eventoService.save(c);
		// id do evento salvo
		Long id = c.getId();
		assertNotNull(id);
		// Busca no banco de dados para confirmar que o evento foi salvo
		c = eventoService.getEvento(id);
		assertEquals("Teste", c.getName());
		assertEquals("Teste desc", c.getDescription());
		assertEquals("10", c.getPreco());
		assertEquals("url foto aqui", c.getUrlFoto());
		// Atualiza o evento
		c.setName("Teste UPDATE");
		eventoService.save(c);
		// Busca o evento novamente (vai estar atualizado)
		c = eventoService.getEvento(id);
		assertEquals("Teste UPDATE", c.getName());
		// Deleta o evento
		eventoService.delete(id);
		// Busca o evento novamente
		c = eventoService.getEvento(id);
		// Desta vez o evento não existe mais.
		assertNull(c);
	}

}
