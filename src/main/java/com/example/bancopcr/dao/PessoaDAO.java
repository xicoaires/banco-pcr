package com.example.bancopcr.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bancopcr.entity.Pessoa;
import com.example.bancopcr.repository.PessoaRepository;
import com.telesign.MessagingClient;

@Repository
public class PessoaDAO {

    private final SessionFactory sessionFactory;
    
    @Autowired
    public PessoaDAO(PessoaRepository pessoaRepository, SessionFactory sessionFactory, MessagingClient messagingClient) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.save(pessoa);
    }

    public List<Pessoa> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Pessoa", Pessoa.class).list();
    }

    public Pessoa findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Pessoa.class, id);
    }

    public void update(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.update(pessoa);
    }

    public void delete(Pessoa pessoa) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(pessoa);
    }
}
