package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.MaquinaDAO;
import br.mendonca.testemaven.model.entities.Maquina;
import br.mendonca.testemaven.services.dto.MaquinaDTO;

public class MaquinaService {

    private final MaquinaDAO dao = new MaquinaDAO();

    // Método para registrar uma nova máquina
    public void register(String nome, float pesoTotal, boolean quebrada) throws ClassNotFoundException, SQLException {
        Maquina maquina = new Maquina();
        maquina.setNome(nome);
        maquina.setPesoTotal(pesoTotal);
        maquina.setQuebrada(quebrada);
        dao.register(maquina);
    }

    // Método para listar todas as máquinas
    public List<MaquinaDTO> listAllMachines() throws ClassNotFoundException, SQLException {
        List<Maquina> lista = dao.listAllMachines();
        List<MaquinaDTO> resp = new ArrayList<>();
        for (Maquina maquina : lista) {
            resp.add(MaquinaDTO.maquinaMapper(maquina));
        }
        return resp;
    }

    // Método para listar máquinas de forma paginada
    public List<MaquinaDTO> listMachinesPaginated(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Maquina> lista = dao.listMachinesPaginated(pagina, tamanhoPagina);
        List<MaquinaDTO> resp = new ArrayList<>();
        for (Maquina maquina : lista) {
            resp.add(MaquinaDTO.maquinaMapper(maquina));
        }
        return resp;
    }

    // Método para obter o total de máquinas
    public int getTotalMaquinas() throws ClassNotFoundException, SQLException {
        return dao.getTotalMaquinas();  // Chama o método no DAO
    }
}
