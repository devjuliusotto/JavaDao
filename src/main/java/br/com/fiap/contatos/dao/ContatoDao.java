package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

public class ContatoDao {

    private EntityManager em;

    public ContatoDao(EntityManager em) {
        this.em = em;

    }

    public void salvar(Contato contato) {
        em.persist(contato);
    }

    public void atualizar(Contato contato) {
        em.merge(contato);

    }

    public void excluir(Contato contato) {
        contato = em.find(Contato.class, contato.getId());
        em.remove(contatoExcluir);
    }

    public void cadastrar(Contato contato) {
        em.persist(contato);
    }

    public list<Contato> ListarTodosOsContatos(){
        // SQL -->  select * from tbl_contatos  ORDER BY nome ASC

        // JPQL
        String consulta = "select c from Contato c ORDER BY nome ASC";
    }

}
