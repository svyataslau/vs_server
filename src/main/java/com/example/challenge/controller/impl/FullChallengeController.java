package com.example.challenge.controller.impl;

import com.example.challenge.controller.CrudController;
import com.example.challenge.model.FullChallenge;
import com.example.challenge.service.CommonService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/challenges")
public class FullChallengeController implements CrudController<FullChallenge> {

    private final CommonService<FullChallenge> fullChallengeService;

    @GetMapping
    @Override
    public List<FullChallenge> getAll() {
        return fullChallengeService.getAll();
    }

    @PostMapping
    @Override
    public int save(@RequestBody FullChallenge fullChallenge) {
        return fullChallengeService.create(fullChallenge);
    }

    @PutMapping("/{id}")
    @Override
    public FullChallenge update(@PathVariable("id") int id, @RequestBody FullChallenge fullChallenge) {
        return fullChallengeService.updateById(id, fullChallenge);
    }

    @DeleteMapping("/{id}")
    @Override
    public int delete(@PathVariable("id") int id) {
        return fullChallengeService.deleteById(id);
    }

}
