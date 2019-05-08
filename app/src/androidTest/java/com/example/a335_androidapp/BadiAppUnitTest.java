package com.example.a335_androidapp;

import com.example.a335_androidapp.dal.BadiDao;
import com.example.a335_androidapp.helper.WieWarmJsonParser;
import com.example.a335_androidapp.model.Badi;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BadiAppUnitTest {

    private String TEST_JSON_BADI ="{\n" +" \"badid\": \"9000\",\n" +" \"badname\": \"Schwimmbad\",\n" +" \"kanton\": \"BE\",\n" +" \"plz\": null,\n" +" \"ort\": \"Bümpliz\",\n" +" \"adresse1\": \"Bahnhöheweg 70\",\n" +" \"adresse2\": null,\n" +" \"email\": \"info@bbcag.ch\",\n" +" \"telefon\": null,\n" + " \"www\": \"www.berufsbildungscenter.ch\",\n" +" \"long\": null,\n" +" \"lat\": null,\n" +" \"zeiten\": null,\n" + " \"preise\": null,\n" +" \"info\": \"Keine speziellen Angaben\",\n" +" \"wetterort\": \"Bern\",\n" +" \"uv_station_name\": \"Bern\",\n" +"  \"uv_wert\": 1,\n" +" \"uv_date\": \"2017-11-28 00:00:00\",\n" +"\"uv_date_pretty\": \"28.11.\",\n" +"\"becken\": {\n" +"\"Schwimmbecken\": {\n" +"\"beckenid\": 185,\n" +"\"beckenname\": \"Schwimmbecken\",\n" +"\"temp\": \"22.0\",\n" +"\"date\": \"2017-07-04 08:02:00\",\n"+"\"typ\": \"Hallenbad\",\n"+"\"status\": \"geöffnet\",\n" +"\"smskeywords\": \";BEUMPLIZ;\",\n" +"\"smsname\": \"Bbc Schwimmbad\",\n" +"\"ismain\": \"T   \",\n"+"\"date_pretty\": \"04.07.\"\n"+"}\n"+"  },\n" +" \"bilder\": [],\n"+"\"wetter\": [\n"+"{\n"+"\"wetter_symbol\": 9,\n"+"\"wetter_temp\": \"3.0\",\n"+"\"wetter_date\": \"2017-11-29 00:00:00\",\n"+"\"wetter_date_pretty\": \"29.11.\"\n"+"},\n"+"{\n"+"\"wetter_symbol\": 15,\n"+"\"wetter_temp\": \"4.0\",\n" +"\"wetter_date\": \"2017-11-28 00:00:00\",\n"+"\"wetter_date_pretty\": \"28.11.\"\n"+"}\n"+"]\n"+"}";
    private String TEST_EMPTYSTRING = "";
    private String TEST_FAULTYJSON ="{\n" +" \"badname\": \"Schwimmbad\",\n" +" \"kanton\": \"BE\",\n" +" \"plz\": null,\n" +" \"ort\": \"Bümpliz\",\n" +" \"adresse1\": \"Bahnhöheweg 70\",\n" +" \"adresse2\": null,\n" +" \"email\": \"info@bbcag.ch\",\n" +" \"telefon\": null,\n" + " \"www\": \"www.berufsbildungscenter.ch\",\n" +" \"long\": null,\n" +" \"lat\": null,\n" +" \"zeiten\": null,\n" + " \"preise\": null,\n" +" \"info\": \"Keine speziellen Angaben\",\n" +" \"wetterort\": \"Bern\",\n" +" \"uv_station_name\": \"Bern\",\n" +"  \"uv_wert\": 1,\n" +" \"uv_date\": \"2017-11-28 00:00:00\",\n" +"\"uv_date_pretty\": \"28.11.\",\n" +"\"becken\": {\n" +"\"Schwimmbecken\": {\n" +"\"beckenid\": 185,\n" +"\"beckenname\": \"Schwimmbecken\",\n" +"\"temp\": \"22.0\",\n" +"\"date\": \"2017-07-04 08:02:00\",\n"+"\"typ\": \"Hallenbad\",\n"+"\"status\": \"geöffnet\",\n" +"\"smskeywords\": \";BEUMPLIZ;\",\n" +"\"smsname\": \"Bbc Schwimmbad\",\n" +"\"ismain\": \"T   \",\n"+"\"date_pretty\": \"04.07.\"\n"+"}\n"+"  },\n" +" \"bilder\": [],\n"+"\"wetter\": [\n"+"{\n"+"\"wetter_symbol\": 9,\n"+"\"wetter_temp\": \"3.0\",\n"+"\"wetter_date\": \"2017-11-29 00:00:00\",\n"+"\"wetter_date_pretty\": \"29.11.\"\n"+"},\n"+"{\n"+"\"wetter_symbol\": 15,\n"+"\"wetter_temp\": \"4.0\",\n" +"\"wetter_date\": \"2017-11-28 00:00:00\",\n"+"\"wetter_date_pretty\": \"28.11.\"\n"+"}\n"+"]\n"+"}";
    private String TEST_NotReallyAJSON = "ID = 1, Kanton = BE";

    @Test
    public void wieWarmJsonParser_CorrectParseSingleBadi() {
        Badi testBadi = null;
        try {
            testBadi = WieWarmJsonParser.createBadiFromJsonString(TEST_JSON_BADI);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        assertEquals(9000, ((Badi) testBadi).getId());
    }

    @Test
    public void WieWarmJSONParser_EmptyString() {

        Badi testBadi = null;
        try {
            testBadi = WieWarmJsonParser.createBadiFromJsonString(TEST_EMPTYSTRING);
        }catch (JSONException e) {
            assertEquals(null, testBadi);
        }
    }

    @Test
    public void WieWarmJSONParser_NOID() {

        Badi testBadi = null;
        try {
            testBadi = WieWarmJsonParser.createBadiFromJsonString(TEST_FAULTYJSON);
        }catch (JSONException e) {
            assertEquals(null, testBadi);
        }
    }

    @Test
    public void BadiDAO_SizeOfResponse() {

        List<Badi> badiList = new ArrayList<>();
        badiList = BadiDao.getAll();
        assertEquals(4, badiList.size());

    }

    @Test
    public void WieWarmJSONParser_PassedJSONIsNoJSON() {

        Badi testBadi = null;
        try {
            testBadi = WieWarmJsonParser.createBadiFromJsonString(TEST_NotReallyAJSON);
        }catch (JSONException e) {
            assertEquals(null, testBadi);
        }
    }





}