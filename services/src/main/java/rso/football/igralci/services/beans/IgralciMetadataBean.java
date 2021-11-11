package rso.football.igralci.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import rso.football.igralci.lib.IgralciMetadata;
import rso.football.igralci.models.converters.IgralciMetadataConverter;
import rso.football.igralci.models.entities.IgralciMetadataEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class IgralciMetadataBean {

    private Logger log = Logger.getLogger(IgralciMetadataBean.class.getName());

    @Inject
    private EntityManager em;

    public List<IgralciMetadata> getIgralciMetadata() {

        TypedQuery<IgralciMetadataEntity> query = em.createNamedQuery(
                "IgralciMetadataEntity.getAll", IgralciMetadataEntity.class);

        List<IgralciMetadataEntity> resultList = query.getResultList();

        return resultList.stream().map(IgralciMetadataConverter::toDto).collect(Collectors.toList());

    }

    public List<IgralciMetadata> getIgralciMetadataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, IgralciMetadataEntity.class, queryParameters).stream()
                .map(IgralciMetadataConverter::toDto).collect(Collectors.toList());
    }

    public IgralciMetadata getIgralciMetadata(Integer id) {

        IgralciMetadataEntity igralciMetadataEntity = em.find(IgralciMetadataEntity.class, id);

        if (igralciMetadataEntity == null) {
            throw new NotFoundException();
        }

        IgralciMetadata igralciMetadata = IgralciMetadataConverter.toDto(igralciMetadataEntity);

        return igralciMetadata;
    }

    public IgralciMetadata createIgralciMetadata(IgralciMetadata igralciMetadata) {

        IgralciMetadataEntity igralciMetadataEntity = IgralciMetadataConverter.toEntity(igralciMetadata);

        try {
            beginTx();
            em.persist(igralciMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (igralciMetadataEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return IgralciMetadataConverter.toDto(igralciMetadataEntity);
    }

    public IgralciMetadata putIgralciMetadata(Integer id, IgralciMetadata igralciMetadata) {

        IgralciMetadataEntity c = em.find(IgralciMetadataEntity.class, id);

        if (c == null) {
            return null;
        }

        IgralciMetadataEntity updatedIgralciMetadataEntity = IgralciMetadataConverter.toEntity(igralciMetadata);

        try {
            beginTx();
            updatedIgralciMetadataEntity.setId(c.getId());
            updatedIgralciMetadataEntity = em.merge(updatedIgralciMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return IgralciMetadataConverter.toDto(updatedIgralciMetadataEntity);
    }

    public boolean deleteIgralciMetadata(Integer id) {

        IgralciMetadataEntity igralciMetadata = em.find(IgralciMetadataEntity.class, id);

        if (igralciMetadata != null) {
            try {
                beginTx();
                em.remove(igralciMetadata);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}