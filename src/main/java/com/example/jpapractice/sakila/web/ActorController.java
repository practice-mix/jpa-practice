package com.example.jpapractice.sakila.web;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorRepository actorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Actor> list(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return actorRepository.findAll(pageable);
    }

    /**
     * query by example
     */
    @RequestMapping(path = "/qbe", method = RequestMethod.GET)
    public Page<Actor> qbe(Actor actor, Pageable pageable) {
        Example<Actor> example = Example.of(actor);
        return actorRepository.findAll(example, pageable);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Actor getOne(@PathVariable("id") Actor actor) {
        return actor;
    }

//    @RequestMapping(path = "/hal", method = RequestMethod.GET)
//    public HttpEntity<PagedModel<EntityModel<Actor>>> hal(@PageableDefault(page = 0, size = 10) Pageable pageable, PagedResourcesAssembler<Actor> assembler) {
//        Page<Actor> page = actorRepository.findAll(pageable);
//
//        return new ResponseEntity<>(assembler.toModel(page), HttpStatus.OK);
//    }
//

}
