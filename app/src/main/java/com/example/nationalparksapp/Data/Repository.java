package com.example.nationalparksapp.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nationalparksapp.Controller.AppController;
import com.example.nationalparksapp.Model.Activities;
import com.example.nationalparksapp.Model.Addresses;
import com.example.nationalparksapp.Model.EntranceFees;
import com.example.nationalparksapp.Model.Images;
import com.example.nationalparksapp.Model.OperatingHours;
import com.example.nationalparksapp.Model.Park;
import com.example.nationalparksapp.Model.ParkViewModel;
import com.example.nationalparksapp.Model.StandardHours;
import com.example.nationalparksapp.Model.Topics;
import com.example.nationalparksapp.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//fetch the data
public class Repository {

    private ParkViewModel parkViewModel;

    static List<Park> parkList = new ArrayList<>();

    public static void getParks(final AsyncResponse callback, String stateCode){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Util.getParksUrl(stateCode), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parkList.clear();

                        try {
                            JSONArray dataJSONArray = response.getJSONArray("data");
                            for(int i = 0; i < dataJSONArray.length(); i++){

                                List<String> entrancePassesList = new ArrayList<>();
                                List<String> feeList = new ArrayList<>();

                                Park park = new Park();
                                JSONObject dataJSONObject = dataJSONArray.getJSONObject(i);

                                //Todo: Set up your Park Object then add it to the parkList ArrayList
                                park.setId(dataJSONObject.getString("id"));
                                park.setUrl(dataJSONObject.getString("url"));
                                park.setFullName(dataJSONObject.getString("fullName"));
                                park.setParkCode(dataJSONObject.getString("parkCode"));
                                park.setDescription(dataJSONObject.getString("description"));
                                park.setLatitude(dataJSONObject.getString("latitude"));
                                park.setLongitude(dataJSONObject.getString("longitude"));
                                park.setLatLong(dataJSONObject.getString("latLong"));
                                park.setStates(dataJSONObject.getString("states"));
                                park.setDirectionsInfo(dataJSONObject.getString("directionsInfo"));
                                park.setDirectionsUrl(dataJSONObject.getString("directionsUrl"));
                                park.setWeatherInfo(dataJSONObject.getString("weatherInfo"));
                                park.setName(dataJSONObject.getString("name"));
                                park.setDesignation(dataJSONObject.getString("designation"));

                                //Activities JSON Objects
                                List<Activities> activitiesList = new ArrayList<>();
                                JSONArray activitiesJSONArray = dataJSONObject.getJSONArray("activities");
                                for(int j = 0; j<activitiesJSONArray.length(); j++){
                                    JSONObject activitiesJSONObject = activitiesJSONArray.getJSONObject(j);

                                    Activities activities = new Activities();
                                    activities.setId(activitiesJSONObject.getString("id"));
                                    activities.setName(activitiesJSONObject.getString("name"));

                                    activitiesList.add(activities);
                                }
                                park.setActivities(activitiesList);

                                //Topics JSON Objects
                                List<Topics> topicsList = new ArrayList<>();
                                JSONArray topicsJSONArray = dataJSONObject.getJSONArray("topics");
                                for(int j =0; j<topicsJSONArray.length(); j++){
                                    JSONObject topicsJSONObject = topicsJSONArray.getJSONObject(j);

                                    Topics topics = new Topics();
                                    topics.setId(topicsJSONObject.getString("id"));
                                    topics.setName(topicsJSONObject.getString("name"));

                                    topicsList.add(topics);
                                }
                                park.setTopics(topicsList);

//                                park.setContacts();

                                //EntranceFees JSON Objects ****** pick the first ArrayList
                                List<EntranceFees> entranceFeesList = new ArrayList<>();
                                JSONArray entranceFeeJSONArray= dataJSONObject.getJSONArray("entranceFees");
                                for(int j = 0; j<entranceFeeJSONArray.length(); j++){
                                    JSONObject entranceFeeJSONObject = entranceFeeJSONArray.getJSONObject(j);

                                    EntranceFees entranceFees = new EntranceFees();
                                    entranceFees.setCost(entranceFeeJSONObject.getString("cost"));
                                    entranceFees.setDescription(entranceFeeJSONObject.getString("description"));
                                    entranceFees.setTitle(entranceFeeJSONObject.getString("title"));

                                    entranceFeesList.add(entranceFees);
                                }
                                park.setEntranceFees(entranceFeesList);

                                park.setEntrancePasses(entrancePassesList);
                                park.setFees(feeList);

                                //OperatingHours JSON Objects ****** pick the first ArrayList
                                List<OperatingHours> operatingHoursList = new ArrayList<>();
                                JSONArray operatingHoursJSONArray = dataJSONObject.getJSONArray("operatingHours");
                                for(int j = 0; j < operatingHoursJSONArray.length(); j++){
                                    JSONObject operatingHoursJSONObject = operatingHoursJSONArray.getJSONObject(j);

                                    //Operating Hours Object setup
                                    OperatingHours operatingHours = new OperatingHours();
                                    operatingHours.setDescription(operatingHoursJSONObject.getString("description"));

                                    //Standard Hours Object setup
                                    StandardHours standardHours = new StandardHours();
                                    JSONObject standardHoursJSONObject = operatingHoursJSONObject.getJSONObject("standardHours");
                                    standardHours.setMonday(standardHoursJSONObject.getString("monday"));
                                    standardHours.setTuesday(standardHoursJSONObject.getString("tuesday"));
                                    standardHours.setWednesday(standardHoursJSONObject.getString("wednesday"));
                                    standardHours.setThursday(standardHoursJSONObject.getString("thursday"));
                                    standardHours.setFriday(standardHoursJSONObject.getString("friday"));
                                    standardHours.setSaturday(standardHoursJSONObject.getString("saturday"));
                                    standardHours.setSunday(standardHoursJSONObject.getString("sunday"));
                                    operatingHours.setStandardHours(standardHours);

                                    //Add to ArrayList
                                    operatingHoursList.add(operatingHours);
                                }
                                park.setOperatingHours(operatingHoursList);


                                List<Addresses> addressesList = new ArrayList<>();
                                JSONArray addressesJSONArray = dataJSONObject.getJSONArray("addresses");
                                for(int j = 0; j< addressesJSONArray.length(); j++){
                                    JSONObject addressesJSONObject = addressesJSONArray.getJSONObject(j);
                                    Addresses address = new Addresses();

                                    address.setPostalCode(addressesJSONObject.getString("postalCode"));
                                    address.setCity(addressesJSONObject.getString("city"));
                                    address.setStateCode(addressesJSONObject.getString("stateCode"));
                                    address.setLine1(addressesJSONObject.getString("line1"));
                                    address.setType(addressesJSONObject.getString("type"));

                                    addressesList.add(address);
                                }
                                park.setAddresses(addressesList);

                                //Images JSON Objects
                                List<Images> imagesList = new ArrayList<>();
                                JSONArray imagesJSONArray = dataJSONObject.getJSONArray("images");
                                for(int j = 0; j<imagesJSONArray.length(); j++){
                                    JSONObject imageJSONObject = imagesJSONArray.getJSONObject(j);

                                    Images images = new Images();
                                    images.setCredit(imageJSONObject.getString("credit"));
                                    images.setTitle(imageJSONObject.getString("title"));
                                    images.setAltText(imageJSONObject.getString("altText"));
                                    images.setCaption(imageJSONObject.getString("caption"));
                                    images.setUrl(imageJSONObject.getString("url"));

                                    imagesList.add(images);
                                }
                                park.setImages(imagesList);


                                //Add Park to ArrayList
                                parkList.add(park);
                            }
                            if(callback != null){
                                callback.processPark(parkList);
                            }

                        } catch (JSONException e) {
                            Log.d("Error", "Something went wrong in Response Volley");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Something went wrong in Volley");
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }




}
