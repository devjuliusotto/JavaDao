package br.com.fiap.contatos;

import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

public class ContatoApp {

    public static void main(String[] args) {
      /*  Contato contato = new Contato();
        contato.setNome("ana");
        contato.setEmail("anna@gmail.com");

        // Cria&ccedil;&atilde;o EntityManager


        EntityManager em = (EntityManager) Conexao.getEntityManager();

        // criando instancia do dao
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.salvar(contato);
        em.getTransaction().commit();


*/

        EntityManager em = (EntityManager) Conexao.getEntityManager();


        //cadastrar(em);
        //atualizar(em);
        excluir(em);
    }

    public static void cadastrar(EntityManager em){

        Contato contato = new Contato();
        contato.setNome("ana");
        contato.setEmail("anna@gmail.com");

        // Criação EntityManager


        em = (EntityManager) Conexao.getEntityManager();

        // criando instancia do dao
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.salvar(contato);
        em.getTransaction().commit();

    }

    public static void atualizar(EntityManager em){

        Contato contato = new Contato();
        contato.setNome("ana");
        contato.setEmail("anna@gmail.com");

        // Criação EntityManager


        em = (EntityManager) Conexao.getEntityManager();

        // criando instancia do dao
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.salvar(contato);
        em.getTransaction().commit();

    }

    public static void excluir(EntityManager em){
        Contato contato = new Contato();
        em = (EntityManager) Conexao.getEntityManager();
        ContatoDao contatoDao = new ContatoDao(em);
        em.getTransaction().begin();


    }
}
