package com.example.web;

import com.example.entity.HateoasDemoEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * class RepresentationModel
 * class EntityModel
 * class CollectionModel
 * class PagedModel
 *
 * EntityModel -|> RepresentationModel
 * CollectionModel -|> RepresentationModel
 * PagedModel -|> CollectionModel
 */
@RestController
public class HateoasDemoController {

    @GetMapping("/{context}")
    public HateoasDemoEntity test(@PathVariable String context){
        HateoasDemoEntity hateoasDemoEntity = new HateoasDemoEntity(context);
        hateoasDemoEntity.add(new Link("https://myhost/people/42"));
        return hateoasDemoEntity;
    }

    @GetMapping("/entity/model/{context}")
    public EntityModel<HateoasDemoEntity> entityModelTest(@PathVariable String context){
        HateoasDemoEntity hateoasDemoEntity = new HateoasDemoEntity(context);
        EntityModel<HateoasDemoEntity> entityModel = new EntityModel<>(hateoasDemoEntity);
        return entityModel;
    }

    @GetMapping("/collection/model/{context}")
    public CollectionModel<HateoasDemoEntity> collectionModelTest(@PathVariable String context){
        Collection<HateoasDemoEntity> entities = Collections.singleton(new HateoasDemoEntity(context));
        CollectionModel<HateoasDemoEntity> modes = new CollectionModel<>(entities);
        return modes;
    }

    @GetMapping("/page/model/{context}")
    public PagedModel<HateoasDemoEntity> pagedModelTest(@PathVariable String context){
        Collection<HateoasDemoEntity> entities = Collections.singleton(new HateoasDemoEntity(context));
        PagedModel<HateoasDemoEntity> pagedModel = new PagedModel<>(entities,null,new Link("https://myhost/people/42"));
        return pagedModel;
    }
}