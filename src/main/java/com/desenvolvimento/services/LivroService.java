package com.desenvolvimento.services;

import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.dtos.LivroDTO;
import com.desenvolvimento.repositories.LivroRepository;
import com.desenvolvimento.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> findAll(){
        //retorna uma lista de LivroDTO
        return livroRepository.findAll().stream().map(obj -> new LivroDTO(obj)).
                collect(Collectors.toList());
    }
    public Livro findbyId(Long id){
        Optional<Livro> obj = livroRepository.findById(id); //por que ta dando problema nessa merdaaa
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Id: " + id));
    }
    public Livro findbyIsbn(String isbn){
        Optional<Livro> obj = livroRepository.findByIsbn(isbn);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado! Isbn: " + isbn));
    }
}
