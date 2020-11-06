package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.daoimpl.ProductDaoImp;
import ua.lviv.lgs.service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private ProductDao productDao ;
    private static ProductService productServiceImpl;

    private ProductServiceImpl() {
        try {
            productDao= new ProductDaoImp();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException| SQLException e) {
            LOGGER.error(e);
        }

    }


    public  static  ProductService getProductService(){
        if(productServiceImpl==null){
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }


    @Override
    public Product create(Product product) throws SQLException {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) throws SQLException {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) throws SQLException {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) throws SQLException {
    productDao.delete(id);
    }

    @Override
    public List<Product> readAll() throws SQLException {
        return productDao.readAll();
    }

    @Override
    public Map<Integer, Product> readAllMap() throws SQLException {
        return readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
