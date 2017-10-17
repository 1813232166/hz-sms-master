package com.hzwealth.sms.modules.sys.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.sys.service.PlaceService;

@Controller
@RequestMapping(value = "${adminPath}/sys/place")
public class PlaceController extends BaseController{
  @Autowired
  private PlaceService placeService;
  
  @RequestMapping(value="getProvince")
  @ResponseBody
  public List<Map<String, String>> getProvince (){
    return placeService.getProvince();
  }
  
  @RequestMapping(value="getCity")
  @ResponseBody
  public List<Map<String, String>> getCity (String pid){
    return placeService.getCity(pid);
  }
  
  @RequestMapping(value="getArea")
  @ResponseBody
  public List<Map<String, String>> getArea (String pid){
    return placeService.getArea(pid);
  }
}
