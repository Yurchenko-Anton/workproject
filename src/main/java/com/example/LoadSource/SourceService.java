package com.example.LoadSource;

import com.example.LoadSource.SourceBase.SourceBase;
import com.example.LoadSource.SourceBase.SourceBaseRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {
    SourceBaseRepository sourceBaseRepository;
    @Value("${url}")
    private String request;
    public SourceService(SourceBaseRepository sourceBaseRepository) {
        this.sourceBaseRepository = sourceBaseRepository;
    }

    public String getJson(String data) throws IOException {
    LocalDateTime localDateTime = LocalDateTime.now();
    String dateNow = localDateTime.toString().substring(0,localDateTime.toString().lastIndexOf("."));
    URL url = new URL(request + data);
    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Content-Type", "charset=utf-8");
    InputStream inputStream = connection.getInputStream();
    String resault = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
        System.out.println(resault);
    return resault;
}
public String gett(SourceBase sourceBase){
    return sourceBase.getStartTimestamp();
}
    public void parseAndSave(String resault) throws ParseException {
        JSONParser parser =new JSONParser();
        JSONObject mainObject = (JSONObject) parser.parse(resault);
        JSONArray jsonArray = (JSONArray)mainObject.get("calls");
        List<SourceBase> bases = new ArrayList<>();
        jsonArray.forEach(o -> {
            SourceBase base = new SourceBase();
            JSONObject jsonObject = (JSONObject) o;
            if (sourceBaseRepository.findFirstByOrderByStartTimestampDesc() == null || !sourceBaseRepository.findFirstByOrderByStartTimestampDesc().getStartTimestamp().equals((String) jsonObject.get("start_timestamp"))) {
                base.setDuration((Long) jsonObject.get("duration"));
                base.setCalledNumber((String) jsonObject.get("called_number"));
                base.setCallingNumber((String) jsonObject.get("calling_number"));
                base.setStartTimestamp((String) jsonObject.get("start_timestamp"));
                base.setEndTimestamp((String) jsonObject.get("end_timestamp"));
                base.setDirection((String) jsonObject.get("direction"));
                base.setCalledName((String) jsonObject.get("called_name"));
                base.setCallingName((String) jsonObject.get("calling_name"));
                base.setCalledDepartment((String) jsonObject.get("called_department"));
                base.setCallingDepartment((String) jsonObject.get("calling_department"));
                bases.add(base);
            }
        });
        Collections.sort(bases);
        sourceBaseRepository.saveAll(bases);
    }
}
