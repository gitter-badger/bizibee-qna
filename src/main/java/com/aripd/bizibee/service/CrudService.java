package com.aripd.bizibee.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public interface CrudService<T, PK extends Serializable> {

    public T create(T entity);

    public T update(T entity);

    public void delete(T entity);

    public boolean deleteItems(List<T> items);

    public T find(PK id);

    public List<T> findAll();

    public List<T> findAllUsingPagination(int pageNumber, int pageSize);

    public List<T> findRange(int[] range);

    public List findWithNamedQuery(String namedQueryName);

    public List findWithNamedQuery(String namedQueryName, Map parameters);

    public List findWithNamedQuery(String queryName, int resultLimit);

    public List<T> findByNativeQuery(String sql);

    public List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);

    public List<T> findWithNamedQuery(String namedQueryName, int start, int end);

    public int count();

    public int countTotalRecord(String namedQueryName);

    public Predicate getFilterCondition(CriteriaBuilder cb, Root<T> root, Map<String, Object> filters);

    public int count(Map<String, Object> filters);

    public List<T> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public List<T> getResultList(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

    //////////
    public BufferedImage resize(BufferedImage originalImage, int width, int height);

    public byte[] convert(BufferedImage originalImage, float quality);

    public byte[] resizeThenConvert(InputStream inputStream, int width, int height);

    /**
     *
     * @param filePath String
     * @param buf byte[]
     */
    public void writeBais(String filePath, byte[] buf);

    public String uploadToLocal(String directory, InputStream inputStream);

    public boolean removeFromLocal(String directory, String fileName);

    default public void doSomeOtherWork() {
        System.out.println("DoSomeOtherWork implementation in the interface");
    }

    default public Path getUploadPath(String directory) {
        Path path = Paths.get(System.getProperty("user.home"), "Developments", "backup", "filestack", directory);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            throw new FacesException(ex);
        }
        return path;
    }

}
