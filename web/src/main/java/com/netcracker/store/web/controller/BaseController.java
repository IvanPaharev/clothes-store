package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
public abstract class BaseController<E, ID extends Serializable> {
    private final BaseService<E, ID> baseService;

    public BaseController(BaseService<E, ID> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<E> get(@PathVariable("id") ID id) {
        return new ResponseEntity<>(baseService.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<E>> getAll() {
        List<E> entities = baseService.getAll();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<E> add(@RequestBody E e) {
        //TODO check out persist to return e, becouse now we just returning same e without id
        baseService.add(e);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<E>> addAll(@RequestBody List<E> entities) {
        //TODO check out persist to return e, becouse now we just returning same e without id
        baseService.addAll(entities);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<E> update(@RequestBody E e) {
        baseService.update(e);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<E> delete(@PathVariable("id") ID id) {
        baseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
