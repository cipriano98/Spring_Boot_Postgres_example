package com.zeta.postgres.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table( name = "answers")
public class Answer extends AuditModel {
    @Id
    @GeneratedValue( generator = "answer_generator" )
    @SequenceGenerator(
        name = "answer_generator",
        sequenceName = "answer_sequence",
        initialValue = 1000
    )
    private Long id;

    @Column( columnDefinition = "text" )
    private String text;


    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "question_id", nullable = false )
    @OnDelete( action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private Question question;

    //getters and setters
    public Long getId() {
        return this.id;
    }

    public void setID( Long id ) {
        this.id = id;
    }
    
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


}