package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.MaquinaDAO;
import br.mendonca.testemaven.model.entities.Maquina;
import br.mendonca.testemaven.services.dto.MaquinaDTO;

public class MaquinaService {

    // Método para registrar uma nova máquina
    public void register(String nome, float pesoTotal, boolean quebrada) throws ClassNotFoundException, SQLException {
        final MaquinaDAO dao = new MaquinaDAO();

        // Criação e configuração do objeto Maquina
        Maquina maquina = new Maquina();
        maquina.setNome(nome);
        maquina.setPesoTotal(pesoTotal);
        maquina.setQuebrada(quebrada);

        // Chamada ao método de persistência
        dao.register(maquina);
    }

    // Método para listar todas as máquinas
    public List<MaquinaDTO> listAllMachines() throws ClassNotFoundException, SQLException {
        final MaquinaDAO dao = new MaquinaDAO();
        List<Maquina> lista = dao.listAllMachines();

        // Mapeando para DTO
        List<MaquinaDTO> resp = new ArrayList<>();
        for (Maquina maquina : lista) {
            resp.add(MaquinaDTO.maquinaMapper(maquina));
        }

        return resp;
    }

    // Método para listar máquinas de forma paginada
    public List<MaquinaDTO> listMachinesPaginated(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        final MaquinaDAO dao = new MaquinaDAO();
        List<Maquina> lista = dao.listMachinesPaginated(pagina, tamanhoPagina);

        // Mapeando para DTO
        List<MaquinaDTO> resp = new ArrayList<>();
        for (Maquina maquina : lista) {
            resp.add(MaquinaDTO.maquinaMapper(maquina));
        }

        return resp;
    }
}
