package com.patrik.blogg.service;

import com.patrik.blogg.exception.AuthorNotFoundException;
import com.patrik.blogg.model.Author;
import com.patrik.blogg.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author newAuthor(Author author){return authorRepository.save(author);}

    public List<Author> getAllAuthors(){return authorRepository.findAll();}

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author updateAuthor(Long id, Author editAuthor){
        return authorRepository.findById(id)
                .map(author -> {
                    author.setBloggerName(editAuthor.getBloggerName());
                    author.setFirstName(editAuthor.getFirstName());
                    author.setLastName(editAuthor.getLastName());
                    author.setPassword(editAuthor.getPassword());

                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public void deleteAuthor(Long id){
        authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.deleteById(id);
    }


}
