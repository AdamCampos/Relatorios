// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2/26/2019 11:43:46 AM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Conexao.java
package controle.banco;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Conexao {

    private static final Logger LOGGER = LogManager.getLogger();
    private static Session session;
    private static Transaction transaction;

    public Conexao() {
    }

    private static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            org.hibernate.boot.registry.StandardServiceRegistry standardRegistry = (new StandardServiceRegistryBuilder()).configure("hibernate.cfg.xml").build();
            Metadata metaData = (new MetadataSources(standardRegistry)).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {
            LOGGER.error((new StringBuilder()).append("Falha na conexão ").append(th).toString());
            throw new ExceptionInInitializerError(th);
        }
        return sessionFactory;
    }

    private void inicia() {
        session = getSessionFactory().openSession();
        session.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        if (session != null) {
            LOGGER.debug("Conectado");
        }
    }

    public void salva(Object obj) {
        try {
            inicia();
            session.save(obj);
            transaction.commit();
            for (; session.isOpen(); session.close());
            LOGGER.debug((new StringBuilder()).append("Objeto ").append(obj).append(" salvo com sucesso!").toString());
        } catch (HibernateException ex) {
            LOGGER.error(ex);
            session.close();
        }
        for (; session.isOpen(); session.close());
    }

    public List pesquisa() {
        inicia();
        LOGGER.info("Pesquisando sem filtro");
        return session.createQuery("from EntidadeOverride ov order by ov.tempoAtivado DESC").list();
    }

    public List pesquisaOM() {
        inicia();
        LOGGER.info("Pesquisando OM");
        return session.createQuery("from EntidadeOverride ov where ov.tipo like '%Man%' order by ov.tempoAtivado DESC").list();
    }

    public List pesquisaOO() {
        inicia();
        LOGGER.info("Pesquisando OO");
        return session.createQuery("from EntidadeOverride ov where ov.tipo like '%Oper%' order by ov.tempoAtivado ASC").list();
    }

}
