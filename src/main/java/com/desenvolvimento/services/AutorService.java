package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.dtos.AutorDTO;
import com.desenvolvimento.repositories.AutorRepository;
import com.desenvolvimento.services.exceptions.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> findAll(){
        //retorna uma lista de AutorDTO
        return autorRepository.findAll().stream().map(obj -> new AutorDTO(obj)).
                collect(Collectors.toList());

    }
    public Autor findbyId(int id){
        Optional<Autor> obj = autorRepository.findById(id);
        return obj.orElse(null);
    }

    public Autor create(AutorDTO dto){
        dto.setId(null);
        Autor obj = new Autor(dto);
        return autorRepository.save(obj);
    }
    public Autor update(Integer id, AutorDTO objDto){
        objDto.setId(id);
        Autor oldObj = findbyId(id);
        oldObj = new Autor(objDto);
        return autorRepository.save(oldObj);
    }
    public void delete(Integer id){
        Autor obj = findbyId(id);
        if(obj.getLivros().size()>0){
            throw new DataIntegrityViolationException("Autor não pode ser deletado pois possui livros vinculados!");
        }
        autorRepository.deleteById(id);
    }

}
