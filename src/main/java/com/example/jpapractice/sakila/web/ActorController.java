package com.example.jpapractice.sakila.web;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.model.projection.ActorPayload;
import com.example.jpapractice.sakila.repository.ActorRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorRepository actorRepository;

    @GetMapping("/{id}")
    public Actor showActor(@PathVariable("id") Actor actor) {
        return actor;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Actor> list(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return actorRepository.findAll(pageable);
    }

    @GetMapping("/webDataBinding")
    public Collection<ActorPayload> webDataBinding(ActorPayload actorPayload) {
        return actorRepository.findByFirstNameEquals(actorPayload.getFirstName(), ActorPayload.class);
    }

    @GetMapping("/querydsl")
    public Page<Actor> querydsl(@QuerydslPredicate(root = Actor.class) Predicate predicate, @PageableDefault Pageable pageable) {
        return actorRepository.findAll(predicate, pageable);
    }


    /**
     * query by example
     */
    @RequestMapping(path = "/qbe", method = RequestMethod.GET)
    public Page<Actor> qbe(Actor actor, Pageable pageable) {
        Example<Actor> example = Example.of(actor);
        return actorRepository.findAll(example, pageable);
    }


//    @RequestMapping(path = "/hal", method = RequestMethod.GET)
//    public HttpEntity<PagedModel<EntityModel<Actor>>> hal(@PageableDefault(page = 0, size = 10) Pageable pageable, PagedResourcesAssembler<Actor> assembler) {
//        Page<Actor> page = actorRepository.findAll(pageable);
//
//        return new ResponseEntity<>(assembler.toModel(page), HttpStatus.OK);
//    }
//

}
