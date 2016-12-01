package com.aripd.bizibee.service;

import com.aripd.util.Resizer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.FacesException;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * @param <T> Entity
 * @param <PK> Primary Key
 */
public abstract class CrudServiceBean<T, PK extends Serializable> implements CrudService<T, PK> {

    private final Class<T> persistentClass;

    /**
     * Default constructor
     *
     * @param entityClass Entity Class
     */
    public CrudServiceBean(Class<T> entityClass) {
        this.persistentClass = entityClass;
    }

    /**
     * Entity Manager
     *
     * @return Entity Manager
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Creates an instance of the entity class in the database
     *
     * @param entity Entity
     * @return object that is created
     */
    @Override
    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity;
    }

    /**
     * Updates the entity instance
     *
     * @param entity Entity
     * @return object that is updated
     */
    @Override
    public T update(T entity) {
        T updated = getEntityManager().merge(entity);
        return updated;
    }

    /**
     * Removes the record that is associated with the entity instance
     *
     * @param entity Entity
     */
    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Removes the number of entries from a table
     *
     * @param entities Entities
     * @return boolean
     */
    @Override
    public boolean deleteItems(List<T> entities) {
        entities.stream().forEach((entity) -> {
            getEntityManager().remove(getEntityManager().merge(entity));
        });
        return true;
    }

    /**
     * Retrieves an entity instance that was previously persisted to the
     * database
     *
     * @param id Id
     * @return Entity
     */
    @Override
    public T find(PK id) {
        return getEntityManager().find(persistentClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(persistentClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<T> findAllUsingPagination(int pageNumber, int pageSize) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(persistentClass)));
        Long count = getEntityManager().createQuery(countQuery).getSingleResult();

        CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        Root<T> root = cq.from(persistentClass);

        CriteriaQuery<T> select = cq.select(root);

        TypedQuery<T> typedQuery = getEntityManager().createQuery(select);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        List<T> list = typedQuery.getResultList();
        return list;
    }

    @Override
    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(persistentClass));
        Query query = getEntityManager().createQuery(cq);
        query.setMaxResults(range[1] - range[0]);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }

    /**
     * Returns the number of records that meet the criteria
     *
     * @param namedQueryName Named Query Name
     * @return List
     */
    @Override
    public List findWithNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Returns the number of records that meet the criteria
     *
     * @param namedQueryName Named Query Name
     * @param parameters Parameters
     * @return List
     */
    @Override
    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Returns the number of records with result limit
     *
     * @param queryName Query Name
     * @param resultLimit Result Limit
     * @return List
     */
    @Override
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return getEntityManager().createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    /**
     * Returns the number of records that meet the criteria
     *
     * @param sql SQL
     * @return List
     */
    @Override
    public List<T> findByNativeQuery(String sql) {
        return getEntityManager().createNativeQuery(sql, persistentClass).getResultList();
    }

    /**
     * Returns the number of records that meet the criteria with parameter map
     * and result limit
     *
     * @param namedQueryName Named Query Name
     * @param parameters Parameters
     * @param resultLimit Result List
     * @return List
     */
    @Override
    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * Returns the number of records that will be used with lazy loading /
     * pagination
     *
     * @param namedQueryName Named Query Name
     * @param start Start
     * @param end End
     * @return List
     */
    @Override
    public List<T> findWithNamedQuery(String namedQueryName, int start, int end) {
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }

    @Override
    public int count() {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<T> root = cq.from(persistentClass);
            cq.select(cb.count(root));
            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    /**
     * Returns the number of total records
     *
     * @param namedQueryName Named Query Name
     * @return int
     */
    @Override
    public int countTotalRecord(String namedQueryName) {
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    /**
     * Returns filter condition
     *
     * @param cb Criteria Builder
     * @param root Root
     * @param filters Filters
     * @return Predicate
     */
    @Override
    public Predicate getFilterCondition(CriteriaBuilder cb, Root<T> root, Map<String, Object> filters) {
        Predicate filterCondition = cb.conjunction();
        if (filters != null) {
            String wildCard = "%";
            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                String value = wildCard + filter.getValue() + wildCard;
                if (!filter.getValue().equals("")) {
                    Path<String> path = root.get(filter.getKey());
                    filterCondition = cb.and(filterCondition, cb.like(path, value));
                }
            }
        }
        return filterCondition;
    }

    @Override
    public int count(Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<T> root = cq.from(persistentClass);
            cq.where(this.getFilterCondition(cb, root, filters));
            cq.select(cb.count(root));
            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    /**
     * Lazy loading list with sorting ability
     *
     * @param first First Record
     * @param pageSize Page Size
     * @param sortField Sort Field
     * @param sortOrder Sort Order
     * @param filters Filters
     * @return List
     */
    @Override
    public List<T> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(persistentClass);
        cq.select(root);
        cq.where(this.getFilterCondition(cb, root, filters));
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }
        Query query = getEntityManager().createQuery(cq);
        return query.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    /**
     * Lazy loading list with multi-sorting ability
     *
     * @param first First Record
     * @param pageSize Page Size
     * @param multiSortMeta Multi Sort Meta
     * @param filters Filters
     * @return List
     */
    @Override
    public List<T> getResultList(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(persistentClass);
        cq.select(root);
        cq.where(this.getFilterCondition(cb, root, filters));
        if (multiSortMeta != null) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta sortMeta : multiSortMeta) {
                String sortField = sortMeta.getSortField();
                SortOrder sortOrder = sortMeta.getSortOrder();
                if (sortField != null) {
                    Order order = null;
                    if (sortOrder == SortOrder.ASCENDING) {
                        order = cb.asc(root.get(sortField));
                    } else if (sortOrder == SortOrder.DESCENDING) {
                        order = cb.desc(root.get(sortField));
                    }
                    orders.add(order);
                }
            }
            cq.orderBy(orders);
        }
        Query query = getEntityManager().createQuery(cq);
        return query.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    //////////
    @Override
    public BufferedImage resize(BufferedImage bufferedImage, int width, int height) {
        return Resizer.PROGRESSIVE_BICUBIC.resize(bufferedImage, width, height);
    }

    @Override
    public byte[] convert(BufferedImage bufferedImage, float quality) {
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(quality);

        byte[] returnImage = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", baos);
            jpgWriter.setOutput(new MemoryCacheImageOutputStream(baos));
            baos.flush();
            returnImage = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            throw new FacesException(ex);
        } finally {
            jpgWriter.dispose();
        }
        return returnImage;
    }

    @Override
    public byte[] resizeThenConvert(InputStream inputStream, int width, int height) {
        try {
            BufferedImage original = ImageIO.read(inputStream);
            BufferedImage resized = resize(original, width, height);
            byte[] converted = convert(resized, 0.7f);
            return converted;
        } catch (IOException ex) {
            throw new FacesException(ex);
        }
    }

    @Override
    public void writeBais(String filePath, byte[] buf) {
        try {
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(buf));

            /**
             * Fix pink background image problem. Simply clone pixels from
             * source ARGB image to the output RGB images and pink hue is gone.
             */
            BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int x = 0; x < originalImage.getWidth(); x++) {
                for (int y = 0; y < originalImage.getHeight(); y++) {
                    newImage.setRGB(x, y, originalImage.getRGB(x, y));
                }
            }

            ImageIO.write(newImage, "jpeg", new File(filePath));
        } catch (IOException ex) {
            throw new FacesException("Error in writing file", ex);
        }
    }

    @Override
    public String uploadToLocal(String directory, InputStream inputStream) {
        try {
            java.nio.file.Path path = Files.createTempFile(getUploadPath(directory), "somefilename-", ".jpg");
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            String fileName = path.getFileName().toString();
            return fileName;
        } catch (IOException ex) {
            throw new FacesException(ex);
        }
    }

    @Override
    public boolean removeFromLocal(String directory, String fileName) {
        try {
            return Files.deleteIfExists(getUploadPath(directory).resolve(fileName));
        } catch (IOException ex) {
            throw new FacesException(ex);
        }
    }

}
