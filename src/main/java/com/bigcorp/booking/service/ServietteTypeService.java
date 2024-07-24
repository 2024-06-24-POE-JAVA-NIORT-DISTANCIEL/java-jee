package com.bigcorp.booking.service;

import com.bigcorp.booking.dao.ServietteTypeDao;
import com.bigcorp.booking.model.ServietteType;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@Stateless
public class ServietteTypeService {
    private static final Logger LOGGER = Logger.getLogger(ServietteTypeService.class);

    @Inject
    private ServietteTypeDao servietteTypeDao;

    public ServietteType findById(Long id) {
        return this.servietteTypeDao.findById(id);
    }

    public ServietteType findWithRestaurantsById(Long id) {
        return this.servietteTypeDao.findWithServiettesById(id);
    }

    @TransactionAttribute
    public ServietteType save(ServietteType servietteType) {
        LOGGER.info("Sauvegarde de : " + servietteType);
        return this.servietteTypeDao.save(servietteType);
    }

    public List<ServietteType> findLikeName(String servietteTypeFilter) {
        return this.servietteTypeDao.findLikeName(servietteTypeFilter);
    }

    public List<ServietteType> findAll() {
        return this.servietteTypeDao.findAll();
    }

    public void deleteById(Long id) {
        this.servietteTypeDao.deleteById(id);
    }

}
