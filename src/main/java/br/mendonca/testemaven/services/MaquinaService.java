package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.MaquinaDAO;
import br.mendonca.testemaven.model.entities.Maquina;
import br.mendonca.testemaven.services.dto.MaquinaDTO;

public class MaquinaService {


    public void register(String nome, float pesoTotal, boolean quebrada) throws ClassNotFoundException, SQLException {
        MaquinaDAO dao = new MaquinaDAO();

        Maquina maquina = new Maquina();
        maquina.setNome(nome);
        maquina.setPesoTotal(pesoTotal);
        maquina.setQuebrada(quebrada);

        dao.register(maquina);
    }


    public List<MaquinaDTO> listAllMachines() throws ClassNotFoundException, SQLException {
        ArrayList<MaquinaDTO> resp = new ArrayList<MaquinaDTO>();

        MaquinaDAO dao = new MaquinaDAO();
        List<Maquina> lista;
        lista = dao.listAllMachines();

        for (Maquina maquina : lista) {
            resp.add(MaquinaDTO.maquinaMapper(maquina));
        }

        return resp;
    }
}
