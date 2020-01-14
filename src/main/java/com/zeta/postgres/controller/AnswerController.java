package com.zeta.postgres.controller;

import com.zeta.postgres.exception.ResourceNotFoundException;
import com.zeta.postgres.model.Answer;
import com.zeta.postgres.repository.AnswerRepository;
import com.zeta.postgres.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;  

    @GetMapping( "/questions/{questionId}/answers" )
    public List < Answer > getAnswersByQuestionId( @PathVariable Long questionId ) {

        if( !questionRepository.existsById( questionId ) ) {
            throw new ResourceNotFoundException( "Não encontrado nenhuma questão com a id " + questionId );
        }
        return answerRepository.findByQuestionId( questionId );

    }

    @PostMapping( "/questions/{questionId}/answers" )
    public Answer addAnswer( @PathVariable Long questionId,
                             @Valid @RequestBody Answer answer ) {

        if( !questionRepository.existsById( questionId ) ) {
            throw new ResourceNotFoundException( "Não encontrado nenhuma questão com a id " + questionId );
        }

        return questionRepository.findById( questionId )
            .map( question -> {
                    answer.setQuestion( question );
                    return answerRepository.save( answer );
                }
            ).orElseThrow( ( ) -> new ResourceNotFoundException( "Não encontrado nenhuma questão com a id: " + questionId ) );

    }
    
    @PutMapping( "/questions/{questionId}/answers/{answerId}" )
    public Answer updateAnswer( @PathVariable Long questionId,
                                @PathVariable Long answerId,
                                @Valid @RequestBody Answer answerRequest ) {
    
        if( !questionRepository.existsById( questionId ) ) {
            throw new ResourceNotFoundException( "Não encontrado nenhuma questão com a id " + questionId );
        }
    
        return answerRepository.findById( answerId )
                .map( answer -> {
                        answer.setText( answerRequest.getText( ) );
                        return answerRepository.save( answer );
                    }
                ).orElseThrow( ( ) -> new ResourceNotFoundException( "Não encontrado nenhuma Respeosta com a id: " + answerId ) );
    
    }

    @DeleteMapping( "/questions/{questionId}/answers/{answerId}" )
    public ResponseEntity < ? > deleteAnswer( @PathVariable Long questionId,
                                              @PathVariable Long answerId ) {
        
        if( !questionRepository.existsById( questionId ) ) {
            throw new ResourceNotFoundException( "Não encontrado nenhuma questão com a id " + questionId );
        }

        return answerRepository.findById( answerId )
                .map( answer -> {
                        answerRepository.delete( answer );
                        return ResponseEntity.ok( ).build( );
                    }
                ).orElseThrow( ( ) -> new ResourceNotFoundException( "Não encontrado nenhuma Resposta com a id: " + answerId ) );

    }

}

