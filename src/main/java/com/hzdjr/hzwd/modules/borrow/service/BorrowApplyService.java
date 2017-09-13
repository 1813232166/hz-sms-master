/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.IdGen;
import com.hzdjr.hzwd.common.utils.PropertiesLoader;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.borrow.dao.BorrowApplyDao;
import com.hzdjr.hzwd.modules.borrow.dao.BorrowDao;
import com.hzdjr.hzwd.modules.borrow.entity.Borrow;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApply;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyDeatil;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyDetailEnum;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyNew;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyTrialQueryVo;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyTrialVo;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowContacts;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowPicVo;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowPicsVo;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;

/**
 * 借款申请列表Service
 * 
 * @author jqs
 * @version 2016-11-29
 */
@Service
@Transactional(readOnly = false)
public class BorrowApplyService extends CrudService<BorrowApplyDao, BorrowApply> {

	private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
	//上传路径
	private static final String onlineFilePath = property.getProperty("uploadimg_baseurl"); 
	// 后台进件ftp本地路径
	private static final String offlineLocalUrl = property.getProperty("offline_local_url"); 
	// 后台进件接口地址
	private static final String offlineUpdateStatusUrl = property.getProperty("offline_update_status_url"); 
	// 后台进件接口秘钥
	private static final String foreignPassword = property.getProperty("offline_foreign_password"); 
	@Autowired
	private BorrowApplyDao borrowdao;
	@Autowired
	private BorrowDao tborrowDao;

	public List<BorrowApply> findList(BorrowApply borrowApply) {
		return super.findList(borrowApply);
	}

	public Page<BorrowApply> findPage(Page<BorrowApply> page, BorrowApply borrowApply) {
		return super.findPage(page, borrowApply);
	}

	/*
	 * 查询借款列表
	 * 
	 */
	public List<BorrowApplyNew> findBorrowNewList(Map<String, Object> map) {
		return borrowdao.findBorrowNewList(map);

	}

	/*
	 * 查询借款列表
	 * 
	 */
	public List<BorrowApplyNew> findBorrowByCancleList(Map<String, Object> map) {
		return borrowdao.findBorrowByCancleList(map);

	}

	/*
	 * 查询借款申请详情
	 * 
	 */
	public BorrowApplyDeatil selectBorrowApplyDeatilById(String id) {
		List<BorrowApplyDeatil> list = borrowdao.selectBorrowApplyDeatilById(id);
		BorrowApplyDeatil borrowApplyDeatil = list.get(0);
		return borrowApplyDeatil;

	}

	public List<BorrowContacts> selectBorrowContactsById(String id) {
		List<BorrowContacts> list = borrowdao.selectBorrowContactsById(id);
		return list;
	}

	public List<BorrowApply> selectAllList(Map<String, Object> map) {
		List<BorrowApply> list = borrowdao.selectAllList(map);
		return list;
	}

	/*
	 * 
	 * 0-身份证1-工作证明2-住址证明3-房产证明4-征信报告5-收入证明6-经营地址证明7-其他8-手机通话记录详单
	 */
	public void updateBorrowStatus(HttpServletRequest request) {

		String[] other = request.getParameterValues("otherImg"); // 其他证明 7
		String[] workAndIncom = request.getParameterValues("workAndIncomImg"); // 工作和收入1
		String[] idcardImg = request.getParameterValues("idcardImg"); // 身份证明 0
		String[] creditImg = request.getParameterValues("creditImg"); // 征信证明 4
		String[] mobileImg = request.getParameterValues("mobileImg"); // 手机通话记录详单 8

		//String borrId = request.getParameter("borId");
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (idcardImg != null) {
			for (int i = 0; i < idcardImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "身份证");
				map.put("pictype", 0);
				map.put("picurl", idcardImg[i]);
				items.add(map);
			}
		}

		if (creditImg != null) {
			for (int i = 0; i < creditImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "征信报告");
				map.put("pictype", 4);
				map.put("picurl", creditImg[i]);
				items.add(map);
			}
		}

		if (workAndIncom != null) {
			for (int i = 0; i < workAndIncom.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "工作及收入证明");
				map.put("pictype", 1);
				map.put("picurl", workAndIncom[i]);
				items.add(map);
			}

		}

		if (other != null) {
			for (int i = 0; i < other.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "其他证明");
				map.put("pictype", 7);
				map.put("picurl", other[i]);
				items.add(map);
			}

		}
		
		if (mobileImg != null) {
			for (int i = 0; i < mobileImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "手机通话记录详单");
				map.put("pictype", 8);
				map.put("picurl", mobileImg[i]);
				items.add(map);
			}
			
		}

		// 点击已完成的时候 修改t_borrow状态 并保存图片
		String borrowId = request.getParameter("borId");
		String type = (String) request.getParameter("type");
		if ("2".equals(type)) {
			borrowdao.updateBorrowStatus(borrowId);
		}
		if (null != items && items.size() > 0) {
			borrowdao.saveBorrowImage(items);
		}

	}

	/*
	 * 回显图片 0-身份证1-工作证明2-住址证明3-房产证明4-征信报告5-收入证明6-经营地址证明7-其他8-手机通话记录详单
	 */
	public Map<String, List<BorrowPicVo>> getshenhePic(String borrowId) {
		List<BorrowPicVo> borrowPic = borrowdao.getBorrowPic(borrowId);
		Map<String, List<BorrowPicVo>> picMap = new HashMap<String, List<BorrowPicVo>>();
		List<BorrowPicVo> idCardList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> workAndincomeList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> creditList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> otherList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> mobileList = new ArrayList<BorrowPicVo>();
		//XJin update 2017-05-08
		for (BorrowPicVo borrowPicVo : borrowPic) {
			// 0 身份证明
			if ("B".equals(borrowPicVo.getPictype())) {
				idCardList.add(borrowPicVo);
			}

			// 工作及收入证明
			if ("C".equals(borrowPicVo.getPictype())) {
				workAndincomeList.add(borrowPicVo);
			}
			// 工作及收入证明
			if ("D".equals(borrowPicVo.getPictype())) {
				workAndincomeList.add(borrowPicVo);
			}
			// 个人征信报告证明
			if ("E".equals(borrowPicVo.getPictype())) {
				creditList.add(borrowPicVo);
			}

/*			// 其他证明
			if ("7".equals(borrowPicVo.getPictype())) {
				otherList.add(borrowPicVo);
			}
			// 手机通话记录详单
			if ("8".equals(borrowPicVo.getPictype())) {
				mobileList.add(borrowPicVo);
			}
*/
		}
		picMap.put("idCardList", idCardList);
		picMap.put("workAndincomeList", workAndincomeList);
		picMap.put("creditList", creditList);
/*		picMap.put("otherList", otherList);
		picMap.put("mobileList", mobileList);*/
		return picMap;

	}

	/*
	 * 查询省市区
	 */
	public HashMap<String, String> selectAddress(String provinceCode, String cityCode, String quCode) {

		HashMap<String, String> map = new HashMap<String, String>();

		String pro = borrowdao.selectProvince(provinceCode);
		String cit = borrowdao.selectCity(cityCode);
		String qu = borrowdao.selectQu(quCode);
		map.put("pro", pro);
		map.put("cit", cit);
		map.put("qu", qu);

		return map;

	}
	//处理结果
	public String findReasonById(String borrowId) {
		return borrowdao.findReasonById(borrowId);

	}

	//删除图片
	public int deleteImage(String picUrl, String borrowId) {

		return borrowdao.deleteImage(picUrl, borrowId);

	}

	/*
	 * 查询后台的图片
	 */
	public Map<String, List<BorrowPicVo>> selectBorrowImageByFlag(String id) {

		Map<String, List<BorrowPicVo>> picMap = new HashMap<String, List<BorrowPicVo>>();
		List<BorrowPicVo> picList = borrowdao.selectBorrowImageByFlag(id);

		List<BorrowPicVo> idCardList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> workAndincomeList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> creditList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> otherList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> mobileList = new ArrayList<BorrowPicVo>();

		for (BorrowPicVo borrowPicVo : picList) {
			// 0 身份证明
			if ("B1".equals(borrowPicVo.getPictype())) {
				idCardList.add(borrowPicVo);
			}

			// 工作及收入证明
			if ("C1".equals(borrowPicVo.getPictype())) {
				workAndincomeList.add(borrowPicVo);
			}
			// 个人征信报告证明
			if ("E1".equals(borrowPicVo.getPictype())) {
				creditList.add(borrowPicVo);
			}

			// 其他证明
			if ("7".equals(borrowPicVo.getPictype())) {
				otherList.add(borrowPicVo);
			}
			// 手机通话记录详单
			if ("8".equals(borrowPicVo.getPictype())) {
				mobileList.add(borrowPicVo);
			}

		}
		picMap.put("IdCardList", idCardList);
		picMap.put("WorkAndincomeList", workAndincomeList);
		picMap.put("CreditList", creditList);
		picMap.put("OtherList", otherList);
		picMap.put("MobileList", mobileList);
		return picMap;
	}
	
	//=========================================线下进件==========================================
	/**
	 * 
	 * @Title: findBorrowApplyTrials   
	 * @Description: 初审线下进件标的
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Apr 24, 2017 5:55:57 PM
	 */
	public List<BorrowApplyTrialVo> borrowApplyTrials(BorrowApplyTrialQueryVo queryVo){
		List<BorrowApplyTrialVo> borrowApplyTrials = tborrowDao.findBorrowApplyTrials(queryVo);
		return borrowApplyTrials;
	}
	
	public int countBorrowApplyTrials(BorrowApplyTrialQueryVo queryVo) {
		int countBorrowApplyTrials = tborrowDao.countBorrowApplyTrials(queryVo);
		return countBorrowApplyTrials;
	}
	
	/**
	 * 
	 * @Title: parseBorrowApplyTrialsForExcel   
	 * @Description: 根据字典转换显示标的（为excel显示）  
	 * @param borrowApplyTrials
	 * @param borrowStatusMap
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 3, 2017 1:54:37 PM
	 */
	public void parseBorrowApplyTrialsForExcel(List<BorrowApplyTrialVo> borrowApplyTrials, Map<String, String> borrowStatusMap){
		for(BorrowApplyTrialVo borrowApplyTrial: borrowApplyTrials){
			switch(borrowApplyTrial.getBorrowStatus()){
			case("3"):
				borrowApplyTrial.setFirstNopassSuggest("用户拒绝签约");
			break;
			case("17"):
				borrowApplyTrial.setFirstNopassSuggest("拒绝签约，用户超过5天未签约，自动流标，手动撤标");
			break;
			case("19"):
				borrowApplyTrial.setFirstNopassSuggest("初审超过7天未处理");
			break;
			default:
				break;
			}
			borrowApplyTrial.setBorrowStatus(borrowStatusMap.get(borrowApplyTrial.getBorrowStatus()));
			if("1".equals(borrowApplyTrial.getBorrowingSources())){
				borrowApplyTrial.setBorrowingSources("线下推送");
			}else{
				borrowApplyTrial.setBorrowingSources(null);
			}
		}
	}
	
	/**
	 * 
	 * @Title: productTypeList   
	 * @Description: 产品类型字典
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Apr 25, 2017 3:51:54 PM
	 */
	public List<Map<String, String>> productTypeList(){
		List<Map<String, String>> productTypeList = tborrowDao.findProductTypeList();
		return productTypeList;
	}
	
	/**
	 * 
	 * @Title: getBorrowDetail   
	 * @Description: 标的详情  
	 * @param borrowId
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 5:57:45 PM
	 */
	public BorrowApplyDeatil getBorrowDetail(String borrowId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("borrowId", borrowId);
		BorrowApplyDeatil borrowDetail = tborrowDao.getBorrowDetailByBorrowId(paramMap);
		List<BorrowContacts> borrowContactsList = borrowdao.selectBorrowContactsById(borrowId);
		borrowDetail.setList(borrowContactsList);
		List<BorrowPicVo> borrowPicList = tborrowDao.findBorrowPicList(paramMap);
		List<BorrowPicsVo> borrowPicsVoList = parseBorrowPicsVoList(borrowPicList);
		borrowDetail.setBorrowPicsVoList(borrowPicsVoList);
		return borrowDetail;
	}
	
	/**
	 * 
	 * @Title: parseBorrowPicsVoList   
	 * @Description: 标的详情图片显示转换
	 * @param borrowPicList
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 5:56:25 PM
	 */
	private List<BorrowPicsVo> parseBorrowPicsVoList(List<BorrowPicVo> borrowPicList){
		BorrowPicsVo idCardPics = new BorrowPicsVo("B");// 0身份证明
		BorrowPicsVo workAndincomePics = new BorrowPicsVo("C");// 1工作及收入证明
		BorrowPicsVo creditPics = new BorrowPicsVo("E");// 4个人征信报告证明
		//BorrowPicsVo mobilePics = new BorrowPicsVo("8");// 8手机通话记录详单
		//BorrowPicsVo otherPics = new BorrowPicsVo("7");// 7其他证明
		for(BorrowPicVo borrowPicVo: borrowPicList){
			switch (borrowPicVo.getStatus()) {
			case "0":
				switch (borrowPicVo.getPictype()) {
				case "B":
					idCardPics.getFrontPicList().add(borrowPicVo);
					break;
				case "C":
					workAndincomePics.getFrontPicList().add(borrowPicVo);
					break;
				case "D":
					workAndincomePics.getFrontPicList().add(borrowPicVo);
					break;
				case "E":
					creditPics.getFrontPicList().add(borrowPicVo);
					break;
				/*case "7":
					otherPics.getFrontPicList().add(borrowPicVo);
					break;
				case "8":
					mobilePics.getFrontPicList().add(borrowPicVo);
					break;*/
				default:
					break;
				}
				break;
			case "1":
				switch (borrowPicVo.getPictype()) {
				case "B":
					idCardPics.getBackPicList().add(borrowPicVo);
					break;
				case "C":
					workAndincomePics.getBackPicList().add(borrowPicVo);
					break;
				case "D":
					workAndincomePics.getBackPicList().add(borrowPicVo);
					break;
				case "E":
					creditPics.getBackPicList().add(borrowPicVo);
					break;
/*				case "7":
					otherPics.getBackPicList().add(borrowPicVo);
					break;
				case "8":
					mobilePics.getBackPicList().add(borrowPicVo);
					break;*/
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
		List<BorrowPicsVo> borrowPicsVoList = new ArrayList<BorrowPicsVo>();
		borrowPicsVoList.add(idCardPics);
		borrowPicsVoList.add(workAndincomePics);
		borrowPicsVoList.add(creditPics);
		//borrowPicsVoList.add(mobilePics);
		//borrowPicsVoList.add(otherPics);
		return borrowPicsVoList;
	}
	
	/**
	 * 
	 * @Title: findDictionaryMap   
	 * @Description: 标的详情字典  
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 5:55:59 PM
	 */
	public Map<String, Object> findDictionaryMap(){
		List<Map<String, String>> provinceList = tborrowDao.findProvinceList();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, String>> cityList = tborrowDao.findCityList(paramMap);
		List<Map<String, String>> areaList = tborrowDao.findAreaList(paramMap);
		
		Map<String, String> borrowTypeEnumMap = BorrowApplyDetailEnum.getEnumMapByType(1);
		Map<String, String> criticalIdEnumMap = BorrowApplyDetailEnum.getEnumMapByType(2);
		Map<String, String> sexEnumMap = BorrowApplyDetailEnum.getEnumMapByType(3);
		Map<String, String> heducationEnumMap = BorrowApplyDetailEnum.getEnumMapByType(4);
		Map<String, String> maritalStautsEnumMap = BorrowApplyDetailEnum.getEnumMapByType(5);
		Map<String, String> hasChildrenEnumMap = BorrowApplyDetailEnum.getEnumMapByType(6);
		Map<String, String> realeStateSituationEnumMap = BorrowApplyDetailEnum.getEnumMapByType(7);
		Map<String, String> coResidentEnumMap = BorrowApplyDetailEnum.getEnumMapByType(8);
		Map<String, String> companyStyleEnumMap = BorrowApplyDetailEnum.getEnumMapByType(9);
		Map<String, String> companyAssumeEnumMap = BorrowApplyDetailEnum.getEnumMapByType(10);
		Map<String, String> contactsTypeEnumMap = BorrowApplyDetailEnum.getEnumMapByType(11);
		Map<String, String> accountTypeEnumMap = BorrowApplyDetailEnum.getEnumMapByType(12);
		Map<String, String> picTypeEnumMap = BorrowApplyDetailEnum.getEnumMapByType(13);
		
		Map<String, Object> dictionaryMap = new HashMap<String, Object>();
		dictionaryMap.put("provinceList", provinceList);
		dictionaryMap.put("cityList", cityList);
		dictionaryMap.put("areaList", areaList);
		dictionaryMap.put("borrowTypeEnumMap", borrowTypeEnumMap);
		dictionaryMap.put("criticalIdEnumMap", criticalIdEnumMap);
		dictionaryMap.put("sexEnumMap", sexEnumMap);
		dictionaryMap.put("heducationEnumMap", heducationEnumMap);
		dictionaryMap.put("maritalStautsEnumMap", maritalStautsEnumMap);
		dictionaryMap.put("hasChildrenEnumMap", hasChildrenEnumMap);
		dictionaryMap.put("realeStateSituationEnumMap", realeStateSituationEnumMap);
		dictionaryMap.put("coResidentEnumMap", coResidentEnumMap);
		dictionaryMap.put("companyStyleEnumMap", companyStyleEnumMap);
		dictionaryMap.put("companyAssumeEnumMap", companyAssumeEnumMap);
		dictionaryMap.put("contactsTypeEnumMap", contactsTypeEnumMap);
		dictionaryMap.put("accountTypeEnumMap", accountTypeEnumMap);
		dictionaryMap.put("picTypeEnumMap", picTypeEnumMap);
		return dictionaryMap;
	}
	
	public String getPicZip(String borrowId, String picType, String timeName){
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			paramMap.put("status", "0");
			paramMap.put("picType", picType);
			List<BorrowPicVo> borrowPicList = tborrowDao.findBorrowPicList(paramMap);
			
			if ("C".equalsIgnoreCase(picType)) {
				paramMap.put("picType", "D");
				List<BorrowPicVo> typeDBorrowPicList = tborrowDao.findBorrowPicList(paramMap);
				borrowPicList.addAll(typeDBorrowPicList);
			}
			
			String zipPath = onlineFilePath +"//"+ borrowId + timeName;
			OutputStream out = new FileOutputStream(zipPath);
			ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(out));
			byte[] buffer = new byte[1024];
			
			for(BorrowPicVo borrowPic: borrowPicList){
				InputStream in = new URL(offlineLocalUrl + borrowPic.getPicurl()).openConnection().getInputStream();
				BufferedInputStream bis = new BufferedInputStream(in);
				zipOut.putNextEntry(new ZipEntry(borrowPic.getPicurl()));
				//设置压缩文件内的字符编码，不然会变成乱码  
				zipOut.setEncoding("GBK");
				int len;
				while ((len = bis.read(buffer)) != -1) {  
					zipOut.write(buffer, 0, len);  
				} 
				zipOut.flush();
				zipOut.closeEntry();  
				bis.close();
			}
			zipOut.close();
			return zipPath;
		} catch (Exception e) {
			logger.error("图片打包异常：", e);  
			throw new RuntimeException("图片打包异常：", e);
		}
	}
	
	public void downFile(InputStream in, OutputStream out){
		try{
			BufferedOutputStream bos = new BufferedOutputStream(out);
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];  
            int len = 0;  
            while ((len = bis.read(buffer)) != -1) {  
            	bos.write(buffer, 0, len);  
            }  
            bos.flush();
            bos.close();
            bis.close();  
		}catch(Exception e){
			logger.error("文件下载异常：", e);  
			throw new RuntimeException("文件下载异常：", e);
		}
	}
	
	/**
	 * 
	 * @Title: saveImgs   
	 * @Description: 保存图片
	 * @param idcardImg
	 * @param creditImg
	 * @param workAndIncom
	 * @param other
	 * @param mobileImg
	 * @param borrowId        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:00:39 PM
	 */
	public void saveImgs(String[] idcardImg, String creditImg[], String[] workAndIncom,
			String[] other, String[] mobileImg, String borrowId){
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		//XJin update 2017-05-08
		if (idcardImg != null) {
			for (int i = 0; i < idcardImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", borrowId);
				map.put("id", IdGen.uuid());
				map.put("picdesc", "身份证");
				map.put("pictype", "B1");
				map.put("largePictype","B");
				map.put("picurl", idcardImg[i]);
				items.add(map);
			}
		}
		if (creditImg != null) {
			for (int i = 0; i < creditImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", borrowId);
				map.put("id", IdGen.uuid());
				map.put("picdesc", "征信报告");
				map.put("pictype", "E1");
				map.put("largePictype","E");
				map.put("picurl", creditImg[i]);
				items.add(map);
			}
		}
		if (workAndIncom != null) {
			for (int i = 0; i < workAndIncom.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", borrowId);
				map.put("id", IdGen.uuid());
				map.put("picdesc", "工作及收入证明");
				map.put("pictype", "C1");
				map.put("largePictype","C");
				map.put("picurl", workAndIncom[i]);
				items.add(map);
			}
		}
/*		if (other != null) {
			for (int i = 0; i < other.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", borrowId);
				map.put("id", IdGen.uuid());
				map.put("picdesc", "其他证明");
				map.put("pictype", 7);
				map.put("picurl", other[i]);
				items.add(map);
			}
		}
		if (mobileImg != null) {
			for (int i = 0; i < mobileImg.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", borrowId);
				map.put("id", IdGen.uuid());
				map.put("picdesc", "手机通话记录详单");
				map.put("pictype", 8);
				map.put("picurl", mobileImg[i]);
				items.add(map);
			}
		}*/
		if (items.size() > 0) {
			borrowdao.saveBorrowImage(items);
		}
	}
	
	/**
	 * 
	 * @Title: saveSuggest   
	 * @Description: 保存审核意见 
	 * @param borrowId
	 * @param suggest
	 * @param auditType        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:00:52 PM
	 */
	public void saveOrUpdateSuggest(String borrowId, String auditSuggest, String auditType, String auditStatus){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditType", auditType);
		paramMap.put("auditBid", borrowId);
		paramMap.put("auditor", UserUtils.getUser().getLoginName());
		paramMap.put("auditDate", new Date());
		paramMap.put("auditSuggest", auditSuggest);
		paramMap.put("auditStatus", auditStatus);
		Map<String, Object> suggest = tborrowDao.getSuggest(paramMap);
		if(suggest == null){
			paramMap.put("id", UUIDUtil.genUUIDString());
			tborrowDao.insertSuggest(paramMap);
		}else{
			tborrowDao.updateSuggest(paramMap);
		}
	}
	
	/**
	 * 
	 * @Title: updateBorrowStatus   
	 * @Description: 更新标的状态
	 * @param status
	 * @param borrowId        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:01:35 PM
	 */
	public void updateBorrowStatus(String status, String borrowId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("borrowId", borrowId);
		tborrowDao.updateBorrowWithStatus(paramMap);
	}
	
	/**
	 * 
	 * @Title: noticeOffLine   
	 * @Description: 调用线下接口
	 * @param borrowId
	 * @param msgText
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 17, 2017 3:28:02 PM
	 */
	public boolean noticeOffLine(String borrowId, String msgText, String type, String borrowStatus){
		Borrow borrow = tborrowDao.get(borrowId);
		String cancelUrl = offlineUpdateStatusUrl + "compensation/notice";
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("type", type);
		paramMap.put("remark", msgText);
		paramMap.put("applyId", borrow.getBorrowcode());
		paramMap.put("borrowStatus", borrowStatus);
		paramMap.put("operatorName", UserUtils.getUser().getLoginName());
		paramMap.put("foreign_password", foreignPassword);
		String paramJson = JSONArray.toJSONString(paramMap);
		logger.info("后台进件审核调用线下参数===============>{}", paramJson);  
		try {
			String resString = DataHttpClient.postData(cancelUrl, paramJson, "POST");
			logger.info("后台进件审核调用线下结果===============>{}", resString);  
			Map<String, Object> resMap = JSONObject.parseObject(resString, new TypeReference<Map<String, Object>>(){});
			if((boolean)resMap.get("success")){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error("后台进件审核调用线下异常：", e);  
			throw new RuntimeException("后台进件审核调用线下异常：", e);
		}
	}
	
	/**
	 * 
	 * @Title: firstTrialPass   
	 * @Description: 初审通过
	 * @param idcardImg
	 * @param creditImg
	 * @param workAndIncom
	 * @param other
	 * @param mobileImg
	 * @param borrowId
	 * @param msgText        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:01:57 PM
	 */
	public void firstTrialPass(String[] idcardImg, String creditImg[], String[] workAndIncom,
			String[] other, String[] mobileImg, String borrowId, String msgText){
		boolean noticeResult = noticeOffLine(borrowId, msgText, "4", "4");
		if(noticeResult){
			saveImgs(idcardImg, creditImg, workAndIncom, other, mobileImg, borrowId);
			saveOrUpdateSuggest(borrowId, msgText, "1", "pass");
			updateBorrowStatus("4", borrowId);
		}
	}
	
	/**
	 * 
	 * @Title: firstTrialReturn   
	 * @Description: 初审打回
	 * @param borrowId
	 * @param msgText        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:02:27 PM
	 */
	public void firstTrialReturn(String borrowId, String msgText){
		boolean noticeResult = noticeOffLine(borrowId, msgText, "3", "3");
		if(noticeResult){
			saveOrUpdateSuggest(borrowId, msgText, "1", "nopass");
			updateBorrowStatus("5", borrowId);
		}
	}
	
	/**
	 * 
	 * @Title: reviewTrialUpdate   
	 * @Description: 终审通过
	 * @param borrowId
	 * @param msgText        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:02:43 PM
	 */
	public void reviewTrialUpdate(String borrowId, String msgText){
		boolean noticeResult = noticeOffLine(borrowId, msgText, "6", "6");
		if(noticeResult){
			saveOrUpdateSuggest(borrowId, msgText, "2", "pass");
			updateBorrowStatus("6", borrowId);
		}
	}
	
	/**
	 * 
	 * @Title: reviewTrialReturn   
	 * @Description: 终审打回 
	 * @param borrowId
	 * @param msgText        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:02:55 PM
	 */
	public void reviewTrialReturn(String borrowId, String msgText){
		boolean noticeResult = noticeOffLine(borrowId, msgText, "7", "7");
		if(noticeResult){
			saveOrUpdateSuggest(borrowId, msgText, "2", "nopass");
			updateBorrowStatus("7", borrowId);
		}
	}
}
