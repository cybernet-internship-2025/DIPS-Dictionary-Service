package az.cybernet.internship.dictionary;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
public class dictionaryController {
    private final dictionaryRep dictionaryRep;
    public dictionaryController(dictionaryRep dictionaryRep) {
        this.dictionaryRep = dictionaryRep;
    }
    @RequestMapping("/api/v1/dictionaries")

        @GetMapping("")
        public List<dictionaries> getDictionaries() {
            List<dictionaries> dict = dictionaryRep.findAll();
            for (dictionaries d : dict) {
                if (!d.isActive()) {
                    dict.remove(d);
                }
            }
            return dict;
        }

        @PutMapping("")
        public void updateDictionary(@RequestBody @Valid dictionaries dictionary) {
            if(dictionaryRep.existsById(dictionary.getId())) {
                dictionaryRep.save(dictionary);
                return;
            }
            if(dictionary.getId() == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary ID cannot be null.");
            }
            dictionaryRep.save(dictionary);
        }
        @DeleteMapping("/{id}")
            public void deleteDictionary(@PathVariable int id) {
                  dictionaries dict = dictionaryRep.findById(id)
                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with ID " + id + " not found."));
                dict.setActive(false);
                dictionaryRep.save(dict);
        }
        @PostMapping("/v1/dictionaries/{id}/restore")
        public void restoreDictionary(@PathVariable int id) {
            List<dictionaries> dictionariesList = dictionaryRep.findAll();
            for (dictionaries dict : dictionariesList) {
                if (dict.getId() == id) {
                    if (dict.isActive()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dictionary with ID " + id + " is already active.");
                    }
                    dict.setActive(true);
                    dictionaryRep.save(dict);
                    return;
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with ID " + id + " not found.");
        }
}
