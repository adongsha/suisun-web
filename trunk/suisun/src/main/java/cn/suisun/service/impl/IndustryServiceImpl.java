package cn.suisun.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.beans.Industry;
import cn.suisun.dao.IndustryDao;
import cn.suisun.service.IndustryService;

@Service
public class IndustryServiceImpl implements IndustryService{

	@Resource
	IndustryDao industryDao;
	
	@Override
	public List<Industry> getIndustryPage(int currentPage, int pageSize) {
		
		return industryDao.getIndustryListByPage(currentPage, pageSize);
	}

	@Override
	public int getIndustryListByPageAmount() {
		return industryDao.getIndustryListByPageAmount();
	}

	@Override
	public Serializable add(Industry industry) {
		return industryDao.save(industry);
	}

	@Override
	public void update(Industry industry) {
		industryDao.update(industry);
	}

	@Override
	public void delete(Industry industry) {
		industryDao.delete(industry);
	}

	@Override
	public List<Industry> getIndustry() {
		return industryDao.getIndustry();
	}

	@Override
	public Industry getIndustryById(String id) {
		return industryDao.getIndustryById(id);
	}

	
}
