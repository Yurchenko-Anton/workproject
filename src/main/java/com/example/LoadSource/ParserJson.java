package com.example.LoadSource;

import com.example.LoadSource.SourceBase.SourceBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParserJson {

    public List<SourceBase> parser(String resault) throws ParseException {
        JSONParser parser =new JSONParser();
        JSONObject mainObject = (JSONObject) parser.parse(resault);
        JSONArray jsonArray = (JSONArray)mainObject.get("calls");
        List<SourceBase> bases = new ArrayList<>();
        jsonArray.forEach(object -> {
            SourceBase base = new SourceBase();
            JSONObject jsonObject = (JSONObject) object;
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
        });
        Collections.sort(bases);
        return bases;
    }
}
