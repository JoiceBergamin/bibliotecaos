package com.desenvolvimento.services;

import com.desenvolvimento.domains.Editora;
import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.dtos.EditoraDTO;
import com.desenvolvimento.repositories.EditoraRepository;
import com.desenvolvimento.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public List<EditoraDTO> findAll(){
        //retorna uma lista de EditoraDTO
        return editoraRepository.findAll().stream().map(obj -> new EditoraDTO(obj)).
                collect(Collectors.toList());

    }
    public Editora findbyId (int id){
        Optional<Editora> obj = editoraRepository.findById(id);
                return obj.orElseThrow(()-> new ObjectNotFoundException("Editora não encontrada! Id: " + id));
    }
    public Editora findbyCnpj(String cnpj){
        Optional<Editora> obj = editoraRepository.findByCnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Editora não encontrada! CNPJ: " + cnpj));
    }
}
