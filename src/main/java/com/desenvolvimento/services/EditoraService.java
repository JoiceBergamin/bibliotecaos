package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.Editora;
import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.dtos.EditoraDTO;
import com.desenvolvimento.repositories.EditoraRepository;
import com.desenvolvimento.services.exceptions.DataIntegrityViolationException;
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

    public Editora create (EditoraDTO dto){
        dto.setId(null);
        validaEditora(dto);
        Editora obj = new Editora(dto);
        return editoraRepository.save(obj);

    }
    public void validaEditora(EditoraDTO dto){
        Optional<Editora> obj = editoraRepository.findByCnpj(dto.getCnpj());
        if (obj.isPresent() && obj.get().getCnpj() != dto.getCnpj()){
            throw new DataIntegrityViolationException("CNPJ já cadastrado!");
        }
    }
    public Editora update(Integer id, EditoraDTO objDto){
        objDto.setId(id);
        Editora oldObj = findbyId(id);
        oldObj = new Editora(objDto);
        return editoraRepository.save(oldObj);
    }
    public void delete(Integer id){
        Editora obj = findbyId(id);
        if(obj.getLivros().size()>0){
            throw new DataIntegrityViolationException("Editora não pode ser deletada pois possui livros vinculados!");
        } editoraRepository.deleteById(id);
    }
}
