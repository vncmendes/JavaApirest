package br.edu.ifsul.service;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifsul.model.Evento;
import br.edu.ifsul.model.EventoDAO;

public class EventoService {
	private EventoDAO db = new EventoDAO();

	// Lista todos os Eventos do banco de dados
	public List<Evento> getEventos() {
		try {
			return db.getEventos();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Busca um Evento pelo id
	public Evento getEvento(Long id) {
		try {
			return db.getEventoById(id);
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o Evento pelo id
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o Evento
	public boolean save(Evento evento) {
		try {
			return db.save(evento);
		} catch (SQLException e) {
			return false;
		}
	}

	// Busca o Evento pelo nome
	public List<Evento> findByName(String name) {
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			return null;
		}
	}

}
