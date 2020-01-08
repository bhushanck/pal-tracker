package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

            return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read( @PathVariable() Long id) {

        TimeEntry entity = timeEntryRepository.find(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @GetMapping
        public ResponseEntity<List<TimeEntry>> list( ){
            List<TimeEntry> list = timeEntryRepository.list();
            return  new ResponseEntity<>(list,HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update( @PathVariable() Long id, @RequestBody TimeEntry timeEntry) {

        TimeEntry entity = timeEntryRepository.update(id, timeEntry);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @DeleteMapping("{id}")
        public ResponseEntity<Void> delete( @PathVariable() Long id){
        timeEntryRepository.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
