package com.example.LoadSource;

import com.example.LoadSource.SourceBase.SourceBase;
import com.example.LoadSource.SourceBase.SourceBaseRepository;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    SourceBaseRepository sourceBaseRepository;

    public SourceService(SourceBaseRepository sourceBaseRepository) {
        this.sourceBaseRepository = sourceBaseRepository;
    }

    public String httpRequest(String date, String request) throws IOException {
        URL url = new URL(request + date);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
    }

    public String getDate() {
        String date;
        try {
            date = sourceBaseRepository.findFirstByOrderByStartTimestampDesc().getStartTimestamp();
            date = date.replaceAll(" ", "T");
        } catch (Exception e) {
            LocalDate localDate = LocalDate.now();
            date = localDate + "T00:00:00";
        }
        return date;
    }

    public void saveData(List<SourceBase> data) {
        sourceBaseRepository.saveAll(data);
    }
}
