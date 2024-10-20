package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.dtos.AutorDTO;
import com.desenvolvimento.repositories.AutorRepository;
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
}
