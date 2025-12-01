package unisul.estoquebackend.category.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisul.estoquebackend.category.repository.entity.SizeEntity;
import unisul.estoquebackend.category.repository.jpa.SizeRepositoryJpa;

@Service
public class SizeService {

    @Autowired
    private SizeRepositoryJpa repository;

    @Transactional(readOnly = true)
    public List<SizeEntity> findAll() {
        return repository.findAll();
    }

    @Transactional
    public SizeEntity create(SizeEntity size) {
        return repository.save(size);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    @Transactional
    public SizeEntity update(Long id, String name) {
        SizeEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));
        entity.setName(name);
        return repository.save(entity);
    }
}
