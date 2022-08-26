package com.example.LoadSource;

import com.example.LoadSource.SourceBase.SourceBaseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Source implements CommandLineRunner {
   private  SourceBaseRepository sourceBaseRepository;
    public Source(SourceBaseRepository sourceBaseRepository) {
        this.sourceBaseRepository = sourceBaseRepository;
    }
    @Value("${my-properties.request}")
    private String request;

    @Override
    public void run(String... args) throws Exception {
        SourceService sourceService = new SourceService(sourceBaseRepository);
        String date = sourceService.getDate();
        ParserJson parserJson = new ParserJson();
        sourceService.saveData(parserJson.parser(sourceService.httpRequest(date, request)));
        Logger.getLogger(Source.class.getName()).info("Made by@ Yurchenko Anton");
    }
}
