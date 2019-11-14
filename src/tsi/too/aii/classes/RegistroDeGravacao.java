package tsi.too.aii.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe tem por objetivo fornecer variáveis e métodos que possibilitem a manipulação de registros informados pelo
 * usuário.
 * 
 * @author Daniel Soares Ferreira
 *
 */
@SuppressWarnings("serial")
public class RegistroDeGravacao implements Serializable{

	private List<String> descricoes;

	/**
	 * Construtor default da classe.
	 */
	public RegistroDeGravacao() {
		descricoes = new ArrayList<>();
	}
	
	/**
	 * O usuário informa uma descrição, é pesquisado na base de dados o nome fornecido, caso o nome já exista e programa
	 * retorna <code>false</code> caso não exista o registro é inserido na base de dados e o programa retorna <code>true</code>.
	 * 
	 * @param descricao
	 * @return boolean
	 */
	public boolean inserirDescricao(String descricao) {//insere uma descricao, nao é possivel inserir descricoes duplicadas
		if(!descricoes.contains(descricao)) {
			descricoes.add(descricao);
			return true;
		}
		else
			return false;
	}//inserirDescricao

	@Override
	public String toString() {
		return "RegistroDeGravacao [descricoes=" + descricoes + "]";
	}

}//class RegistroDeGravacao 

