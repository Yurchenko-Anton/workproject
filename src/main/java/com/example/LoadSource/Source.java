package com.example.LoadSource;

import com.example.LoadSource.SourceBase.SourceBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Logger;

@Component
public class Source implements CommandLineRunner {
    private String data;

   private final SourceBaseRepository sourceBaseRepository;
@Autowired
    public Source(SourceBaseRepository sourceBaseRepository) {
        this.sourceBaseRepository = sourceBaseRepository;
    }

    public SourceBaseRepository getSourceBaseRepository() {
        return sourceBaseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SourceService sourceService = new SourceService(sourceBaseRepository);
    try {
        data = sourceService.gett(sourceBaseRepository.findFirstByOrderByStartTimestampDesc());
        data = data.replaceAll(" ","T");
        System.out.println(data);
    }
    catch (Exception e){
        LocalDate localDate = LocalDate.now();
        data = localDate.toString() + "T00:00:00";
        System.out.println(data);
    }
        sourceService.parseAndSave(sourceService.getJson(data));
        Logger.getLogger(Source.class.getName()).info("Made by@ Yurchenko Anton");
    }

}
