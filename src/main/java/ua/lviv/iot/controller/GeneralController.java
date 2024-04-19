package ua.lviv.iot.controller;

import java.util.Collection;

import org.springframework.ui.Model;

import ua.lviv.iot.model.Conversation;

public interface GeneralController<T, ID> {

    String findAll(Model model);


    String findById(ID id, Model model);


    String deleteById(ID id, Model model);


    String update(Long id, Conversation conversation);

    String save(Conversation model);

}
