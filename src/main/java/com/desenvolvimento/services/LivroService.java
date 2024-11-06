package com.desenvolvimento.services;

import com.desenvolvimento.domains.Autor;
import com.desenvolvimento.domains.Editora;
import com.desenvolvimento.repositories.AutorRepository;
import com.desenvolvimento.repositories.EditoraRepository;
import com.desenvolvimento.domains.Livro;
import com.desenvolvimento.domains.dtos.LivroDTO;
import com.desenvolvimento.repositories.LivroRepository;
import com.desenvolvimento.services.exceptions.DataIntegrityViolationException;
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

    @Autowired
    private EditoraRepository editoraRepository; //injetei editoraRepository aqui também

    @Autowired
    private AutorRepository autorRepository; //injetei autorRepository também

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

    public Livro create(LivroDTO dto){
        dto.setId(null);
        validaLivro(dto);
        Livro obj = new Livro(dto);
        return livroRepository.save(obj);
    }
private void validaLivro (LivroDTO dto){
        Optional<Livro> obj = livroRepository.findByIsbn(dto.getIsbn());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Código ISBN já cadastrado!");
        }

    Optional<Editora> editora = editoraRepository.findById(dto.getEditora());
    if (!editora.isPresent()){
        throw new DataIntegrityViolationException("Editora - " + dto.getEditora() + " não está cadastrada!");
    }
    Optional<Autor> autor = autorRepository.findById(dto.getAutor());
    if(!autor.isPresent()){
        throw new DataIntegrityViolationException("Autor - " + dto.getAutor() + " não está cadastrado!");
    }

    }
public Livro update(Long id, LivroDTO objDto){
        objDto.setId(id);
        Livro oldObj = findbyId(id);
        validaLivro(objDto);
        oldObj = new Livro(objDto);
        return livroRepository.save(oldObj);
}

public void delete(Long id){
        Livro obj = findbyId(id);
        livroRepository.deleteById(id);
}

}
