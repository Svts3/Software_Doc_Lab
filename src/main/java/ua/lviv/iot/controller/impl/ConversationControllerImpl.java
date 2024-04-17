package ua.lviv.iot.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.iot.controller.GeneralController;
import ua.lviv.iot.model.Conversation;
import ua.lviv.iot.model.Person;
import ua.lviv.iot.repository.PersonRepository;
import ua.lviv.iot.service.ConversationService;

@Controller
public class ConversationControllerImpl implements GeneralController<Conversation, Long> {

    @Autowired
    private ConversationService conversationService;
    
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    @Override
    public String findAll(Model model) {
        model.addAttribute("conversations", conversationService.findAll());
        return "index";
    }

    @GetMapping("/conversation/save")
    public String savePage(Model model) {
        model.addAttribute("conversation", new Conversation());
        return "save";
    }

    @PostMapping("/")
    @Override
    public String save(Conversation conversation) {
        conversationService.save(conversation);
        return "redirect:/";
    }

    @Override
    public String findById(Long id, Model model) {
        // TODO Auto-generated method stub
        return null;
    }

    @GetMapping("/{id}/conversation/update")
    public String updatePage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("id", id); // Add this line to set the id attribute in the model
        model.addAttribute("conversation", conversationService.findById(id));
        model.addAttribute("people", personRepository.findAll());
        
        return "update.html";
    }

    @PostMapping("/{id}/update")
    @Override
    public String update(@PathVariable(name = "id") Long id, Conversation conversation) {
        conversationService.update(id, conversation);
        return "redirect:/";
    }

    @PostMapping("/{id}/user/{username}")
    public String addUsers(@PathVariable(name = "id") Long id,
                           @PathVariable(name = "username") String username) {
        conversationService.addMember(username, id);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    @Override
    public String deleteById(@PathVariable(name = "id") Long id, Model model) {
        conversationService.deleteById(id);
        return "redirect:/";
    }

}
