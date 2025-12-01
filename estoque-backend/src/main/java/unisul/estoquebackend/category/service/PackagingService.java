package unisul.estoquebackend.category.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisul.estoquebackend.category.repository.entity.PackagingEntity;
import unisul.estoquebackend.category.repository.jpa.PackagingRepositoryJpa;

@Service
public class PackagingService {

    @Autowired
    private PackagingRepositoryJpa repository;

    @Transactional(readOnly = true)
    public List<PackagingEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public PackagingEntity create(PackagingEntity packaging) {
        return repository.save(packaging);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    @Transactional
    public PackagingEntity update(Long id, String name) {
        PackagingEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Embalagem não encontrada"));
        entity.setName(name);
        return repository.save(entity);
    }
}
